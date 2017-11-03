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
 * Created on 16/10/2017
 */
public class GridTest extends AbstractTest {
    @Test
    public void testGetDensity() {
        Grid gridWithCreature = GridFactory.create(Terrain.LAND);
        gridWithCreature.updateDensity(Species.HARE, 666);

        Grid gridWithoutCreature = GridFactory.create(Terrain.LAND);

        Assert.assertEquals(Terrain.LAND, gridWithCreature.getTerrain());
        Assert.assertEquals(666, gridWithCreature.getDensity(Species.HARE), 0);
        Assert.assertEquals(0, gridWithoutCreature.getDensity(Species.HARE), 0);

        Assert.assertEquals(0, gridWithCreature.getLandNeighborCnt());
        gridWithCreature.setLandNeighborCnt(3);
        Assert.assertEquals(3, gridWithCreature.getLandNeighborCnt());
    }
}
