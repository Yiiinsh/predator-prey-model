package org.epcc.ps.core.algorithm;

import org.epcc.ps.core.entity.environment.Terrain;

/**
 * <p>Core algorithm interface.</p>
 * <p>This interface provides access to predator-prey model simulation algorithm.
 * Two methods defined in this interface deals with the evolution of hare and puma
 * and returns new density of specified species after a given interval.</p>
 * <p>A default implementation of the {@link DefaultCoreAlgorithm} class is available via
 * {@code CoreAlgorithm.DEFAULT} for convenience.</p>
 *
 * @author jiahao.cao
 * @since 0.0.1
 * Created on 11/10/2017
 */
public interface CoreAlgorithm {
    CoreAlgorithm DEFAULT = new DefaultCoreAlgorithm();

    /***
     * Calculate new hare density of a certain grid over a given interval.
     *
     * @param terrain terrain of the grid
     * @param currentHareDensity current hare density in the given grid
     * @param currentHareDensityInLeftGrid current hare density in left neighbor
     * @param currentHareDensityInRightGrid current hare density in right neighbor
     * @param currentHareDensityInUpGrid current hare density in up neighbor
     * @param currentHareDensityInDownGrid current hare density in down neighbor
     * @param hareBirthRate birth rate of hare
     * @param pumaPredationRate predation rate of puma
     * @param currentPumaDensity current puma density on this grid
     * @param hareDiffusionRate diffusion rate of hare
     * @param interval interval for evolution
     * @param landNeighborCnt count of land neighbor
     * @return new hare density over interval
     * */
    double getHaresDensity(Terrain terrain, double currentHareDensity, double currentHareDensityInLeftGrid,
                           double currentHareDensityInRightGrid, double currentHareDensityInUpGrid,
                           double currentHareDensityInDownGrid,
                           double hareBirthRate, double pumaPredationRate, double currentPumaDensity,
                           double hareDiffusionRate, double interval, int landNeighborCnt);


    /**
     * Calculate new puma density of a certain grid over a given interval.
     *
     * @param terrain terrain of grid
     * @param currentPumaDensity current puma density in the given grid
     * @param currentPumaDensityInLeftGrid current puma density in left grid
     * @param currentPumaDensityInRightGrid current puma density in right grid
     * @param currentPumaDensityInUpGrid current puma density in up grid
     * @param currentPumaDensityInDownGrid current puma density in down grid
     * @param pumaBirthRate birth rate of puma
     * @param currentHareDensity current hare density on this grid
     * @param pumaDeathRate mortality rate of puma
     * @param pumaDiffusionRate diffusion rate of puma
     * @param interval interval for evolution
     * @param landNeighborCnt count of land neighbor
     * @return new puma density over interval
     */
    double getPumaDensity(Terrain terrain, double currentPumaDensity, double currentPumaDensityInLeftGrid,
                          double currentPumaDensityInRightGrid, double currentPumaDensityInUpGrid,
                          double currentPumaDensityInDownGrid,
                          double pumaBirthRate, double currentHareDensity,
                          double pumaDeathRate, double pumaDiffusionRate,
                          double interval, int landNeighborCnt);
}
 