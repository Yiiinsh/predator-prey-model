package org.epcc.ps.core.util.implement;

import org.epcc.ps.core.entity.creature.Creature;
import org.epcc.ps.core.entity.creature.CreatureFactory;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.GridFactory;
import org.epcc.ps.core.entity.environment.Terrain;
import org.epcc.ps.core.util.GridUtil;


/**
 * @author jiahao.cao
 * Created on 14/10/2017
 */
public class GridUtilImpl implements GridUtil{
	
	private final static int EXTRA_BORDER = 2;
		
	/***
	 * Initialize grid with halo
	 * 
	 * */	
	public Grid[][] generateGridWithHaloBoundary(int length, int width, int land[][]) {
        Grid[][] grid = new Grid[length + EXTRA_BORDER][width + EXTRA_BORDER];
        for(int i=0;i<grid[0].length;i++)
		{
			grid[i][0]=GridFactory.getInstance().create(Terrain.WATER);	
		}
		for(int i=0;i<grid.length;i++)
		{
			grid[0][i]=GridFactory.getInstance().create(Terrain.WATER);
		}
		for(int i=grid[0].length-1;i>=0;i--)
		{
			grid[i][grid.length-1]=GridFactory.getInstance().create(Terrain.WATER);
		}
		for(int j=grid.length-1;j>=0;j--)
		{
			grid[grid[0].length-1][j]=GridFactory.getInstance().create(Terrain.WATER);
		}
		for(int i=0;i<land[0].length;i++)
		{
			for(int j=0;j<land.length;j++)
			{
				if(land[i][j]==1)
				{
					grid[i+1][j+1]=GridFactory.getInstance().create(Terrain.LAND);
				}
				else if(land[i][j]==0)
				{
					grid[i+1][j+1]=GridFactory.getInstance().create(Terrain.WATER);
				}
			}
		}
        return grid;
    }
	
	/***
	 * Set grid's density number of each creature
	 * 
	 * */
	public Grid setGridCreatureNum(Grid grid,double hareDensity,Species specie)
	{
		Creature creature= CreatureFactory.getInstance().create(specie);
		creature.updateDensity(hareDensity);;
		grid.getCreatures().put(specie, creature);
		return grid;
	}
	
	/***
	 * Get grid's dry land number 
	 * 
	 * */
	public int getNeighborCntWithType(int currentGridX, int currentGridY, Grid[][] gridsWithHaloBoundary, Terrain terrain) {
        int result = 0;
        result += gridsWithHaloBoundary[currentGridX - 1][currentGridY].getTerrain().equals(terrain) ? 1 : 0;
        result += gridsWithHaloBoundary[currentGridX + 1][currentGridY].getTerrain().equals(terrain) ? 1 : 0;
        result += gridsWithHaloBoundary[currentGridX][currentGridY - 1].getTerrain().equals(terrain) ? 1 : 0;
        result += gridsWithHaloBoundary[currentGridX][currentGridY + 1].getTerrain().equals(terrain) ? 1 : 0;
        return result;
    }
}
