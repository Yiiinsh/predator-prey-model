package org.pecc.ps.core.config;

import org.epcc.ps.core.config.CoreConfig;
import org.epcc.ps.core.config.DefaultCoreConfig;
import org.junit.Assert;
import org.junit.Test;
import org.pecc.ps.core.AbstractTest;

/**
 * @author shaohan.yin
 * Created on 11/10/2017
 */
public class CoreConfigTest extends AbstractTest {
    CoreConfig config = new DefaultCoreConfig();

    @Test
    public void testCoreConfig() {
        Assert.assertEquals(config.getHareBirthRate(), 0.08, 0);
        Assert.assertEquals(config.getHareDiffutionRate(), 0.2, 0);
        Assert.assertEquals(config.getPumaBirthRate(), 0.02, 0);
        Assert.assertEquals(config.getPumaDiffusionRate(), 0.2, 0);
        Assert.assertEquals(config.getPumaMortalityRate(), 0.06, 0);
        Assert.assertEquals(config.getPumaPredationRate(), 0.04, 0);
    }
}
