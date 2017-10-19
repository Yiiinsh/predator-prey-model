package org.epcc.ps.client.shell.util;

import org.epcc.ps.client.util.ClientGridUtil;
import org.epcc.ps.client.util.DefaultClienGridUtil;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.Terrain;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jiahao.cao
 * Created on 19/10/2017
 */
public class ClientGridUtilTest {
	ClientGridUtil clienGridUtil =new DefaultClienGridUtil();
	@Test
	public void testgeneratelandByMap()
	{
		int[][] land=clienGridUtil.generatelandByMap("F:/Programming skills/file3.dat");
		Assert.assertEquals(1, land[0][0]);
		Assert.assertEquals(1, land[0][1]);
		Assert.assertEquals(1, land[0][2]);
		Assert.assertEquals(1, land[1][0]);
		Assert.assertEquals(0, land[1][1]);
		Assert.assertEquals(1, land[1][2]);
		Assert.assertEquals(1, land[2][0]);
		Assert.assertEquals(1, land[2][1]);
		Assert.assertEquals(0, land[2][2]);	
	}
	
	@Test
	public void testgenerateGridByLand()
	{
		int[][] land=clienGridUtil.generatelandByMap("F:/Programming skills/file3.dat");
		Grid[][] grid=clienGridUtil.generateGridByLand(land);		
		Assert.assertEquals(Terrain.LAND, grid[0][0].getTerrain());
		Assert.assertEquals(1, grid[0][0].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(1, grid[0][0].getCreatures().get(Species.PUMA).getDensity(),0);
		Assert.assertEquals(Terrain.LAND, grid[0][1].getTerrain());
		Assert.assertEquals(1, grid[0][1].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(2, grid[0][1].getCreatures().get(Species.PUMA).getDensity(),0);
		Assert.assertEquals(Terrain.LAND, grid[0][2].getTerrain());
		Assert.assertEquals(1, grid[0][2].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(3, grid[0][2].getCreatures().get(Species.PUMA).getDensity(),0);
		Assert.assertEquals(Terrain.LAND, grid[1][0].getTerrain());
		Assert.assertEquals(1, grid[1][0].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(4, grid[1][0].getCreatures().get(Species.PUMA).getDensity(),0);
		Assert.assertEquals(Terrain.WATER, grid[1][1].getTerrain());
		Assert.assertEquals(0, grid[1][1].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(0, grid[1][1].getCreatures().get(Species.PUMA).getDensity(),0);
		Assert.assertEquals(Terrain.LAND, grid[1][2].getTerrain());
		Assert.assertEquals(1, grid[1][2].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(5, grid[1][2].getCreatures().get(Species.PUMA).getDensity(),0);
		Assert.assertEquals(Terrain.LAND, grid[2][0].getTerrain());
		Assert.assertEquals(1, grid[2][0].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(6, grid[2][0].getCreatures().get(Species.PUMA).getDensity(),0);
		Assert.assertEquals(Terrain.LAND, grid[2][1].getTerrain());
		Assert.assertEquals(1, grid[2][1].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(7, grid[2][1].getCreatures().get(Species.PUMA).getDensity(),0);
		Assert.assertEquals(Terrain.WATER, grid[2][2].getTerrain());
		Assert.assertEquals(0, grid[2][2].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(0, grid[2][2].getCreatures().get(Species.PUMA).getDensity(),0);
	}

}
