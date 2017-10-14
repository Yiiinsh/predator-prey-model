package org.epcc.ps.core.util;

import org.epcc.ps.core.entity.creature.Creature;
import org.epcc.ps.core.entity.creature.CreatureFactory;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.Terrain;

/**
 * @author jiahao.cao
 * Created on 14/10/2017
 */
public interface GridUtil {
	
	/***
	 * set grid's density number of each creature
	 * 
	 * */
	public Grid setGridCreatureNum(Grid grid,double hareDensity,double pumaDensity);
	
	
	/***
	 * get grid's dry land number 
	 * 
	 * */
	public int getNeighborCntWithType(int x, int y, Grid[][] gridsWithHaloBoundary, Terrain terrain);
	
	
	public Grid[][] generateGridWithHaloBoundary(int length, int width, int land[][]);

}
