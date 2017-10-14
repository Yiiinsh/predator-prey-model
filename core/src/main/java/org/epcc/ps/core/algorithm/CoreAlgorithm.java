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
	public double getHaresNum(double hareOldNum, double hareOldLeft,
			double hareOldRight,double hareOldAbove,double hareOldFollowing, 
			double hareBirthRate, double pumaPredationRate, double pumaOldNum, 
			double hareDifussionRate, double time, int dryNum);
		
	
	/***
	 * Get the new number of Pumas in this grid
	 * 
	 * */
	public double getPumaNum(double pumaOldNum, double pumaOldLeft,
			double pumaOldRight,double pumaOldAbove,double pumaOldFollowing,
		   double pumaBirthRate, double hareOldNum, double pumaDeathRate,
		   double pumaDifussionRate, double time, int dryNum);
}
 