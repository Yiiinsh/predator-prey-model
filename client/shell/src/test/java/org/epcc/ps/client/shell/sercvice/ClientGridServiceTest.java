package org.epcc.ps.client.shell.sercvice;

import org.epcc.ps.client.service.ClientGridService;
import org.epcc.ps.client.service.DefaultClienGridService;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.Landscape;
import org.epcc.ps.core.entity.environment.Terrain;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jiahao.cao
 * Created on 19/10/2017
 */
public class ClientGridServiceTest {
	
	ClientGridService clienGridService =new DefaultClienGridService();
	
	@Test
	public void testLandscapeGenerate()
	{
		Landscape landscape=clienGridService.getLandScape("src/test/resources/file3.dat");
		Assert.assertEquals(3, landscape.getLength());
		Assert.assertEquals(3, landscape.getWidth());
		Grid[][] grid=landscape.getGrids();
		Assert.assertEquals(Terrain.LAND, grid[0][0].getTerrain());
		Assert.assertEquals(1, grid[0][0].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(1, grid[0][0].getCreatures().get(Species.PUMA).getDensity(),0);
		Assert.assertEquals(2, grid[0][0].getLandNeighborCnt());
		Assert.assertEquals(Terrain.LAND, grid[0][1].getTerrain());
		Assert.assertEquals(1, grid[0][1].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(2, grid[0][1].getCreatures().get(Species.PUMA).getDensity(),0);
		Assert.assertEquals(2, grid[0][1].getLandNeighborCnt());
		Assert.assertEquals(Terrain.LAND, grid[0][2].getTerrain());
		Assert.assertEquals(1, grid[0][2].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(3, grid[0][2].getCreatures().get(Species.PUMA).getDensity(),0);
		Assert.assertEquals(2, grid[0][2].getLandNeighborCnt());
		Assert.assertEquals(Terrain.LAND, grid[1][0].getTerrain());
		Assert.assertEquals(1, grid[1][0].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(4, grid[1][0].getCreatures().get(Species.PUMA).getDensity(),0);
		Assert.assertEquals(2, grid[1][0].getLandNeighborCnt());
		Assert.assertEquals(Terrain.WATER, grid[1][1].getTerrain());
		Assert.assertEquals(0, grid[1][1].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(0, grid[1][1].getCreatures().get(Species.PUMA).getDensity(),0);
		Assert.assertEquals(4, grid[1][1].getLandNeighborCnt());
		Assert.assertEquals(Terrain.LAND, grid[1][2].getTerrain());
		Assert.assertEquals(1, grid[1][2].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(5, grid[1][2].getCreatures().get(Species.PUMA).getDensity(),0);
		Assert.assertEquals(1, grid[1][2].getLandNeighborCnt());
		Assert.assertEquals(Terrain.LAND, grid[2][0].getTerrain());
		Assert.assertEquals(1, grid[2][0].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(6, grid[2][0].getCreatures().get(Species.PUMA).getDensity(),0);
		Assert.assertEquals(2, grid[2][0].getLandNeighborCnt());
		Assert.assertEquals(Terrain.LAND, grid[2][1].getTerrain());
		Assert.assertEquals(1, grid[2][1].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(7, grid[2][1].getCreatures().get(Species.PUMA).getDensity(),0);
		Assert.assertEquals(1, grid[2][1].getLandNeighborCnt());
		Assert.assertEquals(Terrain.WATER, grid[2][2].getTerrain());
		Assert.assertEquals(0, grid[2][2].getCreatures().get(Species.HARE).getDensity(),0);
		Assert.assertEquals(0, grid[2][2].getCreatures().get(Species.PUMA).getDensity(),0);	
		Assert.assertEquals(2, grid[2][2].getLandNeighborCnt());
	}
}
