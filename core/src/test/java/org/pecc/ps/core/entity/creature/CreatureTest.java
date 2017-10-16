package org.pecc.ps.core.entity.creature;

import org.epcc.ps.core.config.CoreConfig;
import org.epcc.ps.core.entity.creature.Creature;
import org.epcc.ps.core.entity.creature.CreatureFactory;
import org.epcc.ps.core.entity.creature.Species;
import org.junit.Assert;
import org.junit.Test;
import org.pecc.ps.core.AbstractTest;

/**
 * @author shaohan.yin
 * Created on 12/10/2017
 */
public class CreatureTest extends AbstractTest {
    private CoreConfig config = CoreConfig.DEFAULT;

    @Test
    public void testCreatureMembers() {
        Creature puma = CreatureFactory.create(Species.PUMA);
        Assert.assertEquals(Species.PUMA.getSpeciesName(), puma.getName());
        Assert.assertEquals(config.getPumaBirthRate(), puma.getBirthRate(), 0);
        Assert.assertEquals(config.getPumaDiffusionRate(), puma.getDiffusionRate(), 0);
        Assert.assertEquals(config.getPumaPredationRate(), puma.getPredationRate(), 0);
        Assert.assertEquals(config.getPumaMortalityRate(), puma.getMortalityRate(), 0);
        Assert.assertEquals(0.0, puma.getDensity(), 0);

        Creature hare = CreatureFactory.create(Species.HARE);
        Assert.assertEquals(Species.HARE.getSpeciesName(), hare.getName());
        Assert.assertEquals(config.getHareBirthRate(), hare.getBirthRate(), 0);
        Assert.assertEquals(config.getHareDiffutionRate(), hare.getDiffusionRate(), 0);
        Assert.assertEquals(Species.NON_EXIST_DATA, hare.getPredationRate(), 0);
        Assert.assertEquals(Species.NON_EXIST_DATA, hare.getMortalityRate(), 0);
    }
}
