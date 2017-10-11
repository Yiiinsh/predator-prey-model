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
        Assert.assertEquals(Species.PUMA.getBirthRate(), config.getPumaBirthRate(), 0);
        Assert.assertEquals(Species.PUMA.getDiffusionRate(), config.getPumaDiffusionRate(), 0);
        Assert.assertEquals(Species.PUMA.getMortalityRate(), config.getPumaMortalityRate(), 0);
        Assert.assertEquals(Species.PUMA.getPredationRate(), config.getPumaPredationRate(), 0);
        Assert.assertEquals(Species.PUMA.toString(), "Species[" + Species.PUMA.getSpeciesName() + "]");
    }

    @Test
    public void testHare() {
        Assert.assertEquals(Species.HARE.getBirthRate(), config.getHareBirthRate(), 0);
        Assert.assertEquals(Species.HARE.getDiffusionRate(), config.getHareDiffutionRate(), 0);
        Assert.assertEquals(Species.HARE.getPredationRate(), Species.NON_EXIST_DATA, 0);
        Assert.assertEquals(Species.HARE.getMortalityRate(), Species.NON_EXIST_DATA, 0);
        Assert.assertEquals(Species.HARE.toString(), "Species[" + Species.HARE.getSpeciesName() + "]");
    }
}
