package org.epcc.ps.client.shell.service;

import org.epcc.ps.client.shell.exception.ConvertException;
import org.epcc.ps.client.shell.util.SpeciesDensityGenerator;
import org.epcc.ps.core.entity.creature.Creature;
import org.epcc.ps.core.entity.creature.CreatureFactory;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author jiahao.cao
 * Created on 18/10/2017
 */
public class DefaultConvertService extends AbstractService implements ConvertService {

	    @Override
		public Landscape convertLandscapeFromFile(String fileSource) throws ConvertException {
		    Grid[][] grids = generateGridByMap(readMapFromFile(fileSource));
	    	Landscape landscape = LandscapeFactory.create(grids.length, grids[0].length, grids);
			return landscape;
	    }
	    
		/***
		 * Get land by reading map file
		 * 
		 * */
		private int[][] readMapFromFile(String fileSource) throws ConvertException {
			File file = new File(fileSource);
	        Scanner scanner = null;

			try {
				scanner = new Scanner(file);
				int height = scanner.nextInt();
			    int width = scanner.nextInt();
			    int[][] land = new int[height][width];
			    for(int i = 0; i < height; ++i) {
			        for(int j = 0; j < width; ++j) {
			        	land[i][j]=scanner.nextInt();
			        }
			    }

			    return land;
			} catch (FileNotFoundException e) {
				logger.error("Load file failed.", e);
				throw new ConvertException(e.getMessage());
			} finally {
				if(null != scanner) {
					scanner.close();
				}
			}
		}

		/***
		 * Initialize grids by map
		 * 
		 * */
		private Grid[][] generateGridByMap(int[][] land) {
			Grid[][] grids = new Grid[land.length][land[0].length];
			for(int i = 0; i != land.length; ++i) {
				for(int j = 0; j != land[0].length; ++j) {
					switch (land[i][j]) {
					case 1:
						grids[i][j] = GridFactory.create(Terrain.LAND);
						initGridWithCreate(grids[i][j], Species.HARE, SpeciesDensityGenerator.generateDensity());
						initGridWithCreate(grids[i][j], Species.PUMA, SpeciesDensityGenerator.generateDensity());
						break;
					default:
						grids[i][j] = GridFactory.create(Terrain.WATER);
						initGridWithCreate(grids[i][j], Species.HARE, 0);
						initGridWithCreate(grids[i][j], Species.PUMA, 0);
	    			}
				}
			}
			return grids;
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
		
