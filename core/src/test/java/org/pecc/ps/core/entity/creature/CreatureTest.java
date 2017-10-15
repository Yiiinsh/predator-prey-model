package org.pecc.ps.core.entity.creature;

import org.epcc.ps.core.config.CoreConfig;
import org.epcc.ps.core.config.DefaultCoreConfig;
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
    private CoreConfig config = new DefaultCoreConfig();

    @Test
    public void testCreatureMembers() {
        Creature puma = CreatureFactory.create(Species.PUMA);
        Assert.assertEquals(puma.getName(), Species.PUMA.getSpeciesName());
        Assert.assertEquals(puma.getBirthRate(), config.getPumaBirthRate(), 0);
        Assert.assertEquals(puma.getDiffusionRate(), config.getPumaDiffusionRate(), 0);
        Assert.assertEquals(puma.getPredationRate(), config.getPumaPredationRate(), 0);
        Assert.assertEquals(puma.getMortalityRate(), config.getPumaMortalityRate(), 0);
        Assert.assertEquals(puma.getDensity(), 0.0, 0);

        Creature hare = CreatureFactory.create(Species.HARE);
        Assert.assertEquals(hare.getName(), Species.HARE.getSpeciesName());
        Assert.assertEquals(hare.getBirthRate(), config.getHareBirthRate(), 0);
        Assert.assertEquals(hare.getDiffusionRate(), config.getHareDiffutionRate(), 0);
        Assert.assertEquals(hare.getPredationRate(), Species.NON_EXIST_DATA, 0);
        Assert.assertEquals(hare.getMortalityRate(), Species.NON_EXIST_DATA, 0);
    }
}
