package org.epcc.ps.core.evolution;

import org.epcc.ps.core.algorithm.CoreAlgorithm;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.Landscape;
import org.epcc.ps.core.util.GridUtil;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.epcc.ps.core.util.GridUtil.EXTRA_BORDER_OFFSET;

/**
 * @author shaohan.yin
 * Created on 16/10/2017
 */
public class LandscapeEvolutionManager {
    private static CoreAlgorithm coreAlgorithm = CoreAlgorithm.DEFAULT;

    private Landscape landscape;
    private Grid[][] gridsWithHalo;
    private Map<Species, List<Double>> averages;

    private LandscapeEvolutionManager(Landscape landscape) {
        this.landscape = landscape;
        gridsWithHalo = GridUtil.generateGridWithHaloBoundary(
                landscape.getLength(),
                landscape.getWidth(),
                landscape.getGrids()
        );

        averages = new EnumMap<>(Species.class);
        for (Species species : Species.values()) {
            averages.put(species, new LinkedList<>());
        }

        updateAverageDensities();
    }

    public static LandscapeEvolutionManager create(Landscape landscape) {
        return new LandscapeEvolutionManager(landscape);
    }

    public Landscape getLandscape() {
        return landscape;
    }

    public void setLandscape(Landscape landscape) {
        this.landscape = landscape;
    }

    public List<Double> getAverages(Species species) {
        return averages.get(species);
    }

    public void evolution(double timeStep) {
        int xWithExtraOffset, yWithExtraOffset;
        double hareDensity, pumaDensity, hareDensitySum = 0.0, pumaDensitySum = 0.0;

        for (int x = 0; x != landscape.getLength(); ++x) {
            for (int y = 0; y != landscape.getWidth(); ++y) {
                xWithExtraOffset = x + EXTRA_BORDER_OFFSET;
                yWithExtraOffset = y + EXTRA_BORDER_OFFSET;

                hareDensity = coreAlgorithm.getHaresNum(
                        landscape.getGrids()[x][y].getTerrain(),
                        landscape.getGrids()[x][y].getDensity(Species.HARE),
                        gridsWithHalo[xWithExtraOffset][yWithExtraOffset - 1].getDensity(Species.HARE),
                        gridsWithHalo[xWithExtraOffset][yWithExtraOffset + 1].getDensity(Species.HARE),
                        gridsWithHalo[xWithExtraOffset - 1][yWithExtraOffset].getDensity(Species.HARE),
                        gridsWithHalo[xWithExtraOffset + 1][yWithExtraOffset].getDensity(Species.HARE),
                        Species.HARE.getBirthRate(),
                        Species.PUMA.getPredationRate(),
                        landscape.getGrids()[x][y].getDensity(Species.PUMA),
                        Species.HARE.getDiffusionRate(),
                        timeStep,
                        landscape.getGrids()[x][y].getLandNeighborCnt()
                );

                pumaDensity = coreAlgorithm.getPumaNum(
                        landscape.getGrids()[x][y].getTerrain(),
                        landscape.getGrids()[x][y].getDensity(Species.PUMA),
                        gridsWithHalo[xWithExtraOffset][yWithExtraOffset - 1].getDensity(Species.PUMA),
                        gridsWithHalo[xWithExtraOffset][yWithExtraOffset + 1].getDensity(Species.PUMA),
                        gridsWithHalo[xWithExtraOffset - 1][yWithExtraOffset].getDensity(Species.PUMA),
                        gridsWithHalo[xWithExtraOffset + 1][yWithExtraOffset].getDensity(Species.PUMA),
                        Species.PUMA.getBirthRate(),
                        landscape.getGrids()[x][y].getDensity(Species.HARE),
                        Species.PUMA.getMortalityRate(),
                        Species.PUMA.getDiffusionRate(),
                        timeStep,
                        landscape.getGrids()[x][y].getLandNeighborCnt()
                );

                landscape.getGrids()[x][y].updateDensity(Species.HARE, hareDensity);
                landscape.getGrids()[x][y].updateDensity(Species.PUMA, pumaDensity);
                hareDensitySum += hareDensity;
                pumaDensitySum += pumaDensity;
            }
        }

        syncGridWithHalo();
        updateAverageDensities(hareDensitySum, pumaDensitySum);
    }

    private void syncGridWithHalo() {
        for (int x = 0; x != landscape.getLength(); ++x) {
            for (int y = 0; y != landscape.getWidth(); ++y) {
                gridsWithHalo[x + GridUtil.EXTRA_BORDER_OFFSET][y + GridUtil.EXTRA_BORDER_OFFSET]
                        .updateDensity(Species.HARE, landscape.getGrids()[x][y].getDensity(Species.HARE));
                gridsWithHalo[x + GridUtil.EXTRA_BORDER_OFFSET][y + GridUtil.EXTRA_BORDER_OFFSET]
                        .updateDensity(Species.PUMA, landscape.getGrids()[x][y].getDensity(Species.PUMA));
            }
        }
    }

    private void updateAverageDensities() {
        double hareDensity = 0.0, pumaDensity = 0.0;
        for (int x = 0; x != landscape.getLength(); ++x) {
            for (int y = 0; y != landscape.getWidth(); ++y) {
                hareDensity += landscape.getGrids()[x][y].getDensity(Species.HARE);
                pumaDensity += landscape.getGrids()[x][y].getDensity(Species.PUMA);
            }
        }
        averages.get(Species.HARE).add(hareDensity / (landscape.getWidth() * landscape.getLength()));
        averages.get(Species.PUMA).add(pumaDensity / (landscape.getWidth() * landscape.getLength()));
    }

    private void updateAverageDensities(double hareSum, double pumaSum) {
        averages.get(Species.HARE).add(hareSum / (landscape.getWidth() * landscape.getLength()));
        averages.get(Species.PUMA).add(pumaSum / (landscape.getWidth() * landscape.getLength()));
    }

}
