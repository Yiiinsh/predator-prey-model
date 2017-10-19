package org.epcc.ps.client.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.epcc.ps.core.entity.creature.Creature;
import org.epcc.ps.core.entity.creature.CreatureFactory;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.GridFactory;
import org.epcc.ps.core.entity.environment.Terrain;

/**
 * @author jiahao.cao
 * Created on 18/10/2017
 */
public class DefaultClienGridUtil implements ClientGridUtil{
		
		/***
		 * Get land by reading map file
		 * 
		 * */
		@Override
		public int[][] generatelandByMap(String fileName) {
			File file = new File(fileName);
	        Scanner s;
			try {
				s = new Scanner(file);
				int width=s.nextInt();
			    int height=s.nextInt();
			    int [][] land=new int[height][width];
			    for(int i=0;i<width;i++)
			    {
			        for(int j=0;j<height;j++)
			        {
			        	land[i][j]=s.nextInt();
			        }
			    }
			    s.close();
			    return land;
			} catch (FileNotFoundException e) {
				System.out.println("read file erro");
				e.printStackTrace();
				return null;
			}	 			
		}

		/***
		 * Initialize the grid by map
		 * 
		 * */
		@Override
		public Grid[][] generateGridByLand(int[][] land)
		{
			Grid[][] grid=new Grid[land[0].length][land.length];
			int pnum=1;
			for(int i=0;i!=land[0].length;++i)
			{
				for(int j=0;j!=land.length;++j)
				{
					switch (land[i][j]) {
					case 1:
						grid[i][j] = GridFactory.create(Terrain.LAND);						
						initGridWithCreate(grid[i][j], Species.HARE, 1);
						initGridWithCreate(grid[i][j], Species.PUMA, pnum);
						pnum++;
						break;
					case 0:
						grid[i][j] = GridFactory.create(Terrain.WATER);
						initGridWithCreate(grid[i][j], Species.HARE, 0);
						initGridWithCreate(grid[i][j], Species.PUMA, 0);
						break;
					default:
						grid[i][j] = GridFactory.create(Terrain.WATER);
						initGridWithCreate(grid[i][j], Species.HARE, 0);
						initGridWithCreate(grid[i][j], Species.PUMA, 0);
	    			}
				}
			}
			return grid;
		}
		
		/***
		 * add creature for the grid
		 * 
		 * */
		private void initGridWithCreate(Grid grid, Species species, double density) {
	        Creature creature = CreatureFactory.create(species);
	        creature.updateDensity(density);
	        grid.getCreatures().put(species, creature);
	    }
}
		
