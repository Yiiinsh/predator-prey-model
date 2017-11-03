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
 * <p>A class for better management on landscape evolutions</p>
 * <p>This class provides method for single evolution on given interval.
 * It also maintains a list of average densities over evolutions for each {@link Species}.
 * If the landscape is within a specified size, snap shots for densities of {@link Species}
 * over evolutions will be recorded.</p>
 * <p>LandscapeEvolutionManager can be created using static method <code>create(Landscape)</code></p>
 * <pre>
 *     LandscapeEvolutionManager landscapeEvolutionManager = LandscapeEvolutionManager.create(landscape);
 * </pre>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 16/10/2017
 */
public class LandscapeEvolutionManager {
    private static CoreAlgorithm coreAlgorithm = CoreAlgorithm.DEFAULT;
    private static final int SNAPSHOT_THRESHOLD = 10;

    private Landscape landscape;
    private Grid[][] gridsWithHalo;
    private Map<Species, List<Double>> averages;
    private Map<Species, List<Double[][]>> snapShots;

    public static LandscapeEvolutionManager create(Landscape landscape) {
        return new LandscapeEvolutionManager(landscape);
    }

    private LandscapeEvolutionManager(Landscape landscape) {
        this.landscape = landscape;
        gridsWithHalo = GridUtil.generateGridWithHalo(
                landscape.getLength(),
                landscape.getWidth(),
                landscape.getGrids()
        );

        averages = new EnumMap<>(Species.class);
        for (Species species : Species.values()) {
            averages.put(species, new LinkedList<>());
        }
        updateAverageDensities();

        snapShots = new EnumMap<>(Species.class);
        for (Species species : Species.values()) {
            snapShots.put(species, new LinkedList<>());
            takeSnapShots(species);
        }
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

    public List<Double[][]> getSnapShots(Species species) {
        return snapShots.get(species);
    }

    /**
     * Evolution over a given time step.Densities will be updated on the maintained landscape.
     *
     * @param timeStep time step for iteration
     */
    public void evolution(double timeStep) {
        int xWithExtraOffset, yWithExtraOffset;
        double hareDensity, pumaDensity, hareDensitySum = 0.0, pumaDensitySum = 0.0;

        for (int x = 0; x != landscape.getLength(); ++x) {
            for (int y = 0; y != landscape.getWidth(); ++y) {
                xWithExtraOffset = x + EXTRA_BORDER_OFFSET;
                yWithExtraOffset = y + EXTRA_BORDER_OFFSET;

                hareDensity = coreAlgorithm.getHaresDensity(
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

                pumaDensity = coreAlgorithm.getPumaDensity(
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
        takeSnapShots(Species.PUMA);
        takeSnapShots(Species.HARE);
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

    private void takeSnapShots(Species species) {
        if (landscape.getLength() <= SNAPSHOT_THRESHOLD &&
                landscape.getWidth() <= SNAPSHOT_THRESHOLD) {
            Double[][] snapShot = new Double[landscape.getLength()][landscape.getWidth()];
            for (int x = 0; x != landscape.getLength(); ++x) {
                for (int y = 0; y != landscape.getWidth(); ++y) {
                    snapShot[x][y] = landscape.getGrids()[x][y].getDensity(species);
                }
            }
            snapShots.get(species).add(snapShot);
        }
    }
}
