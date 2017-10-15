package org.pecc.ps.core.entity.creature;

import org.epcc.ps.core.entity.creature.CreatureFactory;
import org.epcc.ps.core.entity.creature.Species;
import org.junit.Assert;
import org.junit.Test;
import org.pecc.ps.core.AbstractTest;

/**
 * @author shaohan.yin
 * Created on 12/10/2017
 */
public class CreatureFactoryTest extends AbstractTest {
    @Test
    public void testFactoryCreate() {
        Assert.assertTrue(CreatureFactory.create(Species.HARE)
                .getName().equals(Species.HARE.getSpeciesName()));
        Assert.assertTrue(CreatureFactory.create(Species.PUMA)
                .getName().equals(Species.PUMA.getSpeciesName()));
    }
}
