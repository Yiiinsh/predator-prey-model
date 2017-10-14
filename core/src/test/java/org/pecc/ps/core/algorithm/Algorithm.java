package org.pecc.ps.core.algorithm;

import org.epcc.ps.core.algorithm.CoreAlgorithm;
import org.epcc.ps.core.algorithm.implement.CoreAlgorithmImp;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.Terrain;
import org.epcc.ps.core.util.GridUtil;
import org.epcc.ps.core.util.implement.GridUtilImplement;
import org.junit.Assert;
import org.junit.Test;
import org.pecc.ps.core.AbstractTest;


/**
 * @author jiahao.cao
 * Created on 10/13/2017
 */

public class Algorithm extends AbstractTest {
	private CoreAlgorithm ca=new CoreAlgorithmImp();
	private GridUtil gridutil=new GridUtilImplement();
	@Test
	public void testGrid()
	{
		int land[][]=new int[3][3];
    	land[0][0]=1;
		land[0][1]=1;
		land[0][2]=1;
		land[1][0]=1;
		land[1][1]=0;
		land[1][2]=1;
		land[2][0]=1;
		land[2][1]=1;
		land[2][2]=0;
		
		Grid[][] grid=gridutil.generateGridWithHaloBoundary(3, 3, land);	
		int hnum=9;
		int pnum=1;
		for(int i=0;i<grid[0].length;i++)
		{
			for(int j=0;j<grid.length;j++)
			{
				if(grid[i][j].getTerrain()==Terrain.LAND){
				grid[i][j]=gridutil.setGridCreatureNum(grid[i][j],hnum,pnum);
				hnum--;
				pnum++;
				}
				else if(grid[i][j].getTerrain()==Terrain.WATER)
				{
					grid[i][j]=gridutil.setGridCreatureNum(grid[i][j],0,0);
					continue;
				}
			}
		}
		
		
		for(int i=1;i<grid[0].length-1;i++)
		{
			for(int j=1;j<grid.length-1;j++)
			{
				grid[i][j].setLandNeighborCnt(gridutil.getNeighborCntWithType(i,j,grid,grid[i][j].getTerrain()));
			}
		}
		
		
		
		double newHareDensity,newPumaDensity;
		for(int i=1;i<grid[0].length-1;i++)
		{
			for(int j=1;j<grid.length-1;j++)
			{
				newHareDensity=ca.getHaresNum(grid[i][j].getCreatures().get(Species.HARE).getDensity(), 
						grid[i][j-1].getCreatures().get(Species.HARE).getDensity(), 
						grid[i][j+1].getCreatures().get(Species.HARE).getDensity(), 
						grid[i-1][j].getCreatures().get(Species.HARE).getDensity(), 
						grid[i+1][j].getCreatures().get(Species.HARE).getDensity(),
						grid[i][j].getCreatures().get(Species.HARE).getBirthRate(),
						grid[i][j].getCreatures().get(Species.PUMA).getPredationRate(), 
						grid[i][j].getCreatures().get(Species.PUMA).getDensity(),
						grid[i][j].getCreatures().get(Species.HARE).getDiffusionRate(), 
						0.4, 
						grid[i][j].getLandNeighborCnt());
				
				newPumaDensity=ca.getPumaNum(grid[i][j].getCreatures().get(Species.PUMA).getDensity(), 
						grid[i][j-1].getCreatures().get(Species.PUMA).getDensity(),
						grid[i][j+1].getCreatures().get(Species.PUMA).getDensity(),
						grid[i-1][j].getCreatures().get(Species.PUMA).getDensity(), 
						grid[i+1][j].getCreatures().get(Species.PUMA).getDensity(), 
						grid[i][j].getCreatures().get(Species.PUMA).getBirthRate(), 
						grid[i][j].getCreatures().get(Species.HARE).getDensity(), 
						grid[i][j].getCreatures().get(Species.PUMA).getMortalityRate(), 
						grid[i][j].getCreatures().get(Species.PUMA).getDiffusionRate(), 
						0.4,
						grid[i][j].getLandNeighborCnt());
				grid[i][j].getCreatures().get(Species.HARE).updateDensity(newHareDensity);
				grid[i][j].getCreatures().get(Species.PUMA).updateDensity(newPumaDensity);
				
			}
		}
		
		Assert.assertEquals(grid[1][1].getCreatures().get(Species.HARE).getDensity(), 8.824,0);
		Assert.assertEquals(grid[1][1].getCreatures().get(Species.PUMA).getDensity(), 1.368,0);
	}
	
	
	
	
	

}
