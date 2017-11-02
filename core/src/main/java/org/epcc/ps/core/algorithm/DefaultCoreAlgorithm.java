package org.epcc.ps.core.algorithm;

import org.epcc.ps.core.entity.environment.Terrain;

/**
 * @author jiahao.cao
 * Created on 11/10/2017
 */
public class DefaultCoreAlgorithm implements CoreAlgorithm {
    /***
     * Get the new number of Hares in this grid
     *
     * */
    @Override
    public double getHaresNum(Terrain terrain, double currentHareDensity,
                              double currentHareDensityInLeftGrid, double currentHareDensityInRightGrid,
                              double currentHareDensityInUpGrid, double currentHareDensityInDownGrid,
                              double hareBirthRate, double pumaPredationRate, double currentPumaDensity,
                              double hareDiffusionRate, double interval, int neighborLandCnt) {
        if (Terrain.WATER.equals(terrain)) {
            return 0.0;
        }

        return currentHareDensity + interval * (hareBirthRate * currentHareDensity
                    - pumaPredationRate * currentHareDensity * currentPumaDensity
                    + hareDiffusionRate * (currentHareDensityInLeftGrid + currentHareDensityInRightGrid
                        + currentHareDensityInUpGrid + currentHareDensityInDownGrid
                        - neighborLandCnt * currentHareDensity));
    }

    /***
     * Get the new density of Pumas in this grid
     *
     * */
    @Override
    public double getPumaNum(Terrain terrain, double currentPumaDensity, double currentPumaDensityInLeftGrid,
                             double currentPumaDensityInRightGrid, double currentPumaDensityInUpGrid,
                             double currentPumaDensityInDownGrid,
                             double pumaBirthRate, double currentHareDensity,
                             double pumaDeathRate, double pumaDiffusionRate,
                             double interval, int neighborLandCnt) {

        if (Terrain.WATER.equals(terrain)) {
            return 0.0;
        }

        return currentPumaDensity + interval * (pumaBirthRate * currentHareDensity * currentPumaDensity
                    - pumaDeathRate * currentPumaDensity
                    + pumaDiffusionRate * (currentPumaDensityInLeftGrid + currentPumaDensityInRightGrid
                        + currentPumaDensityInUpGrid + currentPumaDensityInDownGrid
                        - neighborLandCnt * currentPumaDensity));
    }
}
 