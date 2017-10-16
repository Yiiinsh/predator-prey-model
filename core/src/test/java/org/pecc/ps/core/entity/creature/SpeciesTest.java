package org.pecc.ps.core.entity.creature;

import org.epcc.ps.core.config.CoreConfig;
import org.epcc.ps.core.config.DefaultCoreConfig;
import org.epcc.ps.core.entity.creature.Species;
import org.junit.Assert;
import org.junit.Test;
import org.pecc.ps.core.AbstractTest;

/**
 * @author shaohan.yin
 * Created on 11/10/2017
 */
public class SpeciesTest extends AbstractTest {
    private CoreConfig config = new DefaultCoreConfig();

    @Test
    public void testPuma() {
        Assert.assertEquals(config.getPumaBirthRate(), Species.PUMA.getBirthRate(), 0);
        Assert.assertEquals(config.getPumaDiffusionRate(), Species.PUMA.getDiffusionRate(), 0);
        Assert.assertEquals(config.getPumaMortalityRate(), Species.PUMA.getMortalityRate(), 0);
        Assert.assertEquals(config.getPumaPredationRate(), Species.PUMA.getPredationRate(), 0);
        Assert.assertEquals("Species[" + Species.PUMA.getSpeciesName() + "]", Species.PUMA.toString());
    }

    @Test
    public void testHare() {
        Assert.assertEquals(config.getHareBirthRate(), Species.HARE.getBirthRate(), 0);
        Assert.assertEquals(config.getHareDiffutionRate(), Species.HARE.getDiffusionRate(), 0);
        Assert.assertEquals(Species.NON_EXIST_DATA, Species.HARE.getPredationRate(), 0);
        Assert.assertEquals(Species.NON_EXIST_DATA, Species.HARE.getMortalityRate(), 0);
        Assert.assertEquals("Species[" + Species.HARE.getSpeciesName() + "]", Species.HARE.toString());
    }
}
