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

/**
 * @author shaohan.yin
 * Created on 16/10/2017
 */
public class GridTest extends AbstractTest {
    @Test
    public void testGetDensity() {
        Grid gridWithCreature = GridFactory.create(Terrain.LAND);
        Creature creature = CreatureFactory.create(Species.HARE);
        creature.updateDensity(666);
        gridWithCreature.getCreatures().put(Species.HARE, creature);

        Grid gridWithoutCreature = GridFactory.create(Terrain.LAND);

        Assert.assertEquals(666, gridWithCreature.getDensity(Species.HARE), 0);
        Assert.assertEquals(0, gridWithoutCreature.getDensity(Species.HARE), 0);
    }
}
