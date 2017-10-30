package org.epcc.ps.core.algorithm;

import org.epcc.ps.core.entity.environment.Terrain;

/**
 * @author jiahao.cao
 * Created on 11/10/2017
 */
public interface CoreAlgorithm {
    CoreAlgorithm DEFAULT = new DefaultCoreAlgorithm();

    /***
     * Get the new number of Hares in this grid
     *
     * */
    double getHaresNum(Terrain terrain, double currentHareNum, double currentHareNumInLeftGrid,
                       double currentHareNumInRightGrid, double currentHareNumInAboveGrid,
                       double currentHareNumInFollowingGrid,
                       double hareBirthRate, double pumaPredationRate, double currentPumaNum,
                       double hareDifussionRate, double intervalTime, int dryLandNum);


    /***
     * Get the new number of Pumas in this grid
     *
     * */
    double getPumaNum(Terrain terrain, double currentPumaNum, double currentPumaNumInLeftGrid,
                      double currentPumaNumInRightGrid, double currentPumaNumInAboveGrid,
                      double currentPumaNumInFollowingGrid,
                      double pumaBirthRate, double currentHareNum,
                      double pumaDeathRate, double pumaDifussionRate,
                      double intervalTime, int dryLandNum);
}
 