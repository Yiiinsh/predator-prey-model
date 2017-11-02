package org.pecc.ps.core.entity.environment;

import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.GridFactory;
import org.epcc.ps.core.entity.environment.Terrain;
import org.junit.Assert;
import org.junit.Test;
import org.pecc.ps.core.AbstractTest;

/**
 * @author shaohan.yin
 * Created on 12/10/2017
 */
public class GridFactoryTest extends AbstractTest {
    @Test
    public void testGridCreate() {
        Grid grid = GridFactory.create(Terrain.LAND);
        Assert.assertEquals(Terrain.LAND, grid.getTerrain());

        double[] densities = new double[2];
        densities[Species.HARE.ordinal()] = 8;
        densities[Species.PUMA.ordinal()] = 1;
        grid = GridFactory.create(Terrain.LAND, densities);
        Assert.assertEquals(Terrain.LAND, grid.getTerrain());
        Assert.assertEquals(8, grid.getDensity(Species.HARE), 0);
        Assert.assertEquals(1, grid.getDensity(Species.PUMA), 0);

        grid = GridFactory.create(Terrain.LAND, densities, 2);
        Assert.assertEquals(2, grid.getLandNeighborCnt(), 0);
    }
}
