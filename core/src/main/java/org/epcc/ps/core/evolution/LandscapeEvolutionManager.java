package org.epcc.ps.core.evolution;

import org.apache.commons.lang3.SerializationUtils;
import org.epcc.ps.core.algorithm.CoreAlgorithm;
import org.epcc.ps.core.config.CoreConfig;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.Landscape;
import org.epcc.ps.core.util.GridUtil;

import java.util.ArrayList;

import static org.epcc.ps.core.util.GridUtil.EXTRA_BORDER_OFFSET;

/**
 * @author shaohan.yin
 * Created on 16/10/2017
 */
public class LandscapeEvolutionManager {
    private static CoreConfig config = CoreConfig.DEFAULT;
    private static CoreAlgorithm coreAlgorithm = CoreAlgorithm.DEFAULT;

    private Landscape landscape;
    private ArrayList<Landscape> snapshots;

    public LandscapeEvolutionManager(Landscape landscape) {
        this.landscape = landscape;
        snapshots = new ArrayList<>();
        takeSnapshot();
    }

    public Landscape getLandscape() {
        return landscape;
    }

    public void setLandscape(Landscape landscape) {
        this.landscape = landscape;
    }

    public ArrayList<Landscape> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(ArrayList<Landscape> snapshots) {
        this.snapshots = snapshots;
    }

    public void evolution() {
        double start = config.getLandscapeEvolutionTimeStart();
        double end = config.getLandscapeEvolutionTimeEnd();
        double timeStep = config.getLandscapeEvolutionTimeStep();

        for(double idx = start; idx <= end; idx += timeStep) {
            evolutionPerTimeStep();
            takeSnapshot();
        }
    }

    public void evolutionPerTimeStep() {
        int xIdxWithExtraOffset, yIdxWithExtraOffset;
        double newHareDensity, newPumaDensity;
        Landscape tmpLandscape = SerializationUtils.clone(landscape);
        Grid[][] gridsWithHaloBoundary = GridUtil.generateGridWithHaloBoundary(
                landscape.getLength(),
                landscape.getWidth(),
                landscape.getGrids()
        );


        for(int xIdx = 0; xIdx != landscape.getLength(); ++xIdx) {
            for(int yIdx = 0; yIdx != landscape.getWidth(); ++yIdx) {
                xIdxWithExtraOffset = xIdx + EXTRA_BORDER_OFFSET;
                yIdxWithExtraOffset = yIdx + EXTRA_BORDER_OFFSET;

                newHareDensity = coreAlgorithm.getHaresNum(
                        landscape.getGrids()[xIdx][yIdx].getTerrain(),
                        landscape.getGrids()[xIdx][yIdx].getDensity(Species.HARE),
                        gridsWithHaloBoundary[xIdxWithExtraOffset][yIdxWithExtraOffset - 1].getDensity(Species.HARE),
                        gridsWithHaloBoundary[xIdxWithExtraOffset][yIdxWithExtraOffset + 1].getDensity(Species.HARE),
                        gridsWithHaloBoundary[xIdxWithExtraOffset - 1][yIdxWithExtraOffset].getDensity(Species.HARE),
                        gridsWithHaloBoundary[xIdxWithExtraOffset + 1][yIdxWithExtraOffset].getDensity(Species.HARE),
                        Species.HARE.getBirthRate(),
                        Species.PUMA.getPredationRate(),
                        landscape.getGrids()[xIdx][yIdx].getDensity(Species.PUMA),
                        Species.HARE.getDiffusionRate(),
                        config.getLandscapeEvolutionTimeStep(),
                        landscape.getGrids()[xIdx][yIdx].getLandNeighborCnt()
                );

                newPumaDensity = coreAlgorithm.getPumaNum(
                        landscape.getGrids()[xIdx][yIdx].getTerrain(),
                        landscape.getGrids()[xIdx][yIdx].getDensity(Species.PUMA),
                        gridsWithHaloBoundary[xIdxWithExtraOffset][yIdxWithExtraOffset - 1].getDensity(Species.PUMA),
                        gridsWithHaloBoundary[xIdxWithExtraOffset][yIdxWithExtraOffset + 1].getDensity(Species.PUMA),
                        gridsWithHaloBoundary[xIdxWithExtraOffset - 1][yIdxWithExtraOffset].getDensity(Species.PUMA),
                        gridsWithHaloBoundary[xIdxWithExtraOffset + 1][yIdxWithExtraOffset].getDensity(Species.PUMA),
                        Species.PUMA.getBirthRate(),
                        landscape.getGrids()[xIdx][yIdx].getDensity(Species.HARE),
                        Species.PUMA.getMortalityRate(),
                        Species.PUMA.getDiffusionRate(),
                        config.getLandscapeEvolutionTimeStep(),
                        landscape.getGrids()[xIdx][yIdx].getLandNeighborCnt()
                );

                tmpLandscape.getGrids()[xIdx][yIdx].getCreatures().get(Species.HARE).updateDensity(newHareDensity);
                tmpLandscape.getGrids()[xIdx][yIdx].getCreatures().get(Species.PUMA).updateDensity(newPumaDensity);
            }
        }

        setLandscape(tmpLandscape);
    }

    private void takeSnapshot() {
        snapshots.add(SerializationUtils.clone(landscape));
    }

}
