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
    public double getHaresNum(Terrain terrain, double currentHareNum, double currentHareNumInLeftGrid,
                              double currentHareNumInRightGrid, double currentHareNumInAboveGrid,
                              double currentHareNumInFollowingGrid,
                              double hareBirthRate, double pumaPredationRate, double currentPumaNum,
                              double hareDifussionRate, double intervalTime, int dryLandNum) {
        if (terrain.equals(Terrain.WATER)) {
            return 0.0;
        }

        double newHareNum, neighborHareNum;
        neighborHareNum = currentHareNumInLeftGrid + currentHareNumInRightGrid
                + currentHareNumInAboveGrid + currentHareNumInFollowingGrid;
        newHareNum = currentHareNum
                + intervalTime * (hareBirthRate * currentHareNum
                - pumaPredationRate * currentHareNum * currentPumaNum
                + hareDifussionRate * (neighborHareNum - dryLandNum * currentHareNum));
        return newHareNum;
    }

    /***
     * Get the new density of Pumas in this grid
     *
     * */
    @Override
    public double getPumaNum(Terrain terrain, double currentPumaNum, double currentPumaNumInLeftGrid,
                             double currentPumaNumInRightGrid, double currentPumaNumInAboveGrid,
                             double currentPumaNumInFollowingGrid,
                             double pumaBirthRate, double currentHareNum,
                             double pumaDeathRate, double pumaDifussionRate,
                             double intervalTime, int dryLandNum) {

        if (Terrain.WATER.equals(terrain)) {
            return 0.0;
        }

        double newPumaNum, neighborPumaNum;
        neighborPumaNum = currentPumaNumInLeftGrid + currentPumaNumInRightGrid
                + currentPumaNumInAboveGrid + currentPumaNumInFollowingGrid;
        newPumaNum = currentPumaNum
                + intervalTime * (pumaBirthRate * currentHareNum * currentPumaNum
                - pumaDeathRate * currentPumaNum
                + pumaDifussionRate * (neighborPumaNum - dryLandNum * currentPumaNum));
        return newPumaNum;
    }
}
 