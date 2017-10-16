package org.pecc.ps.core.entity.environment;

import org.epcc.ps.core.entity.creature.Creature;
import org.epcc.ps.core.entity.creature.CreatureFactory;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.GridFactory;
import org.epcc.ps.core.entity.environment.Terrain;
import org.junit.Assert;
import org.junit.Test;
import org.pecc.ps.core.AbstractTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shaohan.yin
 * Created on 12/10/2017
 */
public class GridFactoryTest extends AbstractTest {
    @Test
    public void testGridCreate() {
        Grid grid = GridFactory.create(Terrain.LAND);
        Assert.assertEquals(Terrain.LAND, grid.getTerrain());

        Map<Species, Creature> creatures = new HashMap<>();
        creatures.put(Species.HARE, CreatureFactory.create(Species.HARE));
        creatures.get(Species.HARE).updateDensity(8);
        creatures.put(Species.PUMA, CreatureFactory.create(Species.PUMA));
        creatures.get(Species.PUMA).updateDensity(1);
        grid = GridFactory.create(Terrain.LAND, creatures);
        Assert.assertEquals(Terrain.LAND, grid.getTerrain());
        Assert.assertEquals(8, grid.getDensity(Species.HARE), 0);
        Assert.assertEquals(1, grid.getDensity(Species.PUMA), 0);

        grid = GridFactory.create(Terrain.LAND, creatures, 2);
        Assert.assertEquals(2, grid.getLandNeighborCnt(), 0);
    }
}
