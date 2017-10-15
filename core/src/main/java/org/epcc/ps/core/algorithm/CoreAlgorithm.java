package org.epcc.ps.core.algorithm;  
/**
 * @author jiahao.cao
 * Created on 11/10/2017
 */
public interface CoreAlgorithm {
	/***
	 * Get the new number of Hares in this grid
	 * 
	 * */
    double getHaresNum(double currentHareNum, double currentHareNumInLeftGrid,
                       double currentHareNumInRightGrid, double currentHareNumInAboveGrid,
                       double currentHareNumInFollowingGrid,
                       double hareBirthRate, double pumaPredationRate, double currentPumaNum,
                       double hareDifussionRate, double intervalTime, int dryLandNum);
		
	
	/***
	 * Get the new number of Pumas in this grid
	 * 
	 * */
    double getPumaNum(double currentPumaNum, double currentPumaNumInLeftGrid,
                      double currentPumaNumInRightGrid, double currentPumaNumInAboveGrid,
                      double currentPumaNumInFollowingGrid,
                      double pumaBirthRate, double currentHareNum,
                      double pumaDeathRate, double pumaDifussionRate,
                      double intervalTime, int dryLandNum);
}
 