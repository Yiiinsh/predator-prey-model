package org.epcc.ps.core.util;

import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.Terrain;

/**
 * @author jiahao.cao
 * Created on 14/10/2017
 */
public interface GridUtil {
	
	/***
	 * Initialize grid with halo
	 * 
	 * */
	public Grid[][] generateGridWithHaloBoundary(int length, int width, int land[][]);
	/***
	 * set grid's density number of each creature
	 * 
	 * */
	
	public Grid setGridCreatureNum(Grid grid,double hareDensity,Species specie);
	
	/***
	 * get grid's dry land number 
	 * 
	 * */
	public int getNeighborCntWithType(int currentGridX, int currentGridY, 
			Grid[][] gridsWithHaloBoundary, Terrain terrain);
}
