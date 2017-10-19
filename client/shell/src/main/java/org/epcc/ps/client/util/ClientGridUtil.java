package org.epcc.ps.client.util;

import org.epcc.ps.core.entity.environment.Grid;

/**
 * @author jiahao.cao
 * Created on 18/10/2017
 */
public interface ClientGridUtil {
	
	ClientGridUtil DEFAULT =new DefaultClienGridUtil();

	public int[][] generatelandByMap(String fileName);
	/***
	 * Initialize the grid by map
	 * 
	 * */
	public Grid[][] generateGridByLand(int[][] land);
}
