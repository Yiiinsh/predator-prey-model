package org.epcc.ps.core.algorithm.implement;  

import org.epcc.ps.core.algorithm.CoreAlgorithm;

/**
 * @author jiahao.cao
 * Created on 11/10/2017
 */
public class CoreAlgorithmImp implements CoreAlgorithm{
	/***
	 * Get the new number of Hares in this grid
	 * 
	 * */
	@Override
	public double getHaresNum(double hareOldNum, double hareOldLeft,
		   double hareOldRight,double hareOldAbove,double hareOldFollowing, 
		   double hareBirthRate, double pumaPredationRate, double pumaOldNum, 
		   double hareDifussionRate, double time, int dryNum){
		
			double hareNewNum;
			hareNewNum = hareOldNum + time * ( hareBirthRate * hareOldNum - pumaPredationRate * hareOldNum * pumaOldNum + hareDifussionRate * ((hareOldLeft + hareOldRight + hareOldAbove + hareOldFollowing) - dryNum * hareOldNum));
			return hareNewNum;
			
		}
	/***
	 * Get the new density of Pumas in this grid
	 * 
	 * */
	@Override
	public double getPumaNum(double pumaOldNum, double pumaOldLeft,double pumaOldRight,
		   double pumaOldAbove,double pumaOldFollowing,
		   double pumaBirthRate, double hareOldNum, 
		   double pumaDeathRate, double pumaDifussionRate, 
		   double time, int dryNum) {
		
		double pumaNewNum;
		pumaNewNum = pumaOldNum + time * ( pumaBirthRate * hareOldNum * pumaOldNum - pumaDeathRate * pumaOldNum + pumaDifussionRate * ((pumaOldLeft + pumaOldRight + pumaOldAbove + pumaOldFollowing) - dryNum * pumaOldNum));
		return pumaNewNum;
	}
	
}
 