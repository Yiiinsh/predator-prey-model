package org.epcc.ps.core.algorithm.impl;

import org.epcc.ps.core.algorithm.CoreAlgorithm;

/**
 * @author jiahao.cao
 * Created on 11/10/2017
 */
public class CoreAlgorithmImpl implements CoreAlgorithm{
	/***
	 * Get the new number of Hares in this grid
	 * 
	 * */
	@Override
	public double getHaresNum(double currentHareNum, double currentHareNumInLeftGrid,
		    double currentHareNumInRightGrid,double currentHareNumInAboveGrid,
		    double currentHareNumInFollowingGrid, 
		    double hareBirthRate, double pumaPredationRate, double currentPumaNum, 
		    double hareDifussionRate, double intervalTime, int dryLandNum){
		
			double newHareNum, neighborHareNum;
			neighborHareNum = currentHareNumInLeftGrid + currentHareNumInRightGrid
					+ currentHareNumInAboveGrid + currentHareNumInFollowingGrid;
			newHareNum = currentHareNum 
					     + intervalTime * ( hareBirthRate * currentHareNum
					     - pumaPredationRate * currentHareNum * currentPumaNum 
					     + hareDifussionRate * (neighborHareNum - dryLandNum * currentHareNum));
			return newHareNum;	
	}
	
	/***
	 * Get the new density of Pumas in this grid
	 * 
	 * */
	@Override
	public double getPumaNum(double currentPumaNum, double currentPumaNumInLeftGrid,
			double currentPumaNumInRightGrid, double currentPumaNumInAboveGrid,
			double currentPumaNumInFollowingGrid,
		    double pumaBirthRate, double currentHareNum, 
		    double pumaDeathRate, double pumaDifussionRate, 
		    double intervalTime, int dryLandNum) {
		
		    double newPumaNum, neighborPumaNum;
		    neighborPumaNum = currentPumaNumInLeftGrid + currentPumaNumInRightGrid
					+ currentPumaNumInAboveGrid + currentPumaNumInFollowingGrid;
		    newPumaNum = currentPumaNum
					+ intervalTime * ( pumaBirthRate * currentHareNum * currentPumaNum
					- pumaDeathRate * currentPumaNum
					+ pumaDifussionRate * (neighborPumaNum - dryLandNum * currentPumaNum));
		    return newPumaNum;
	}	
}
 