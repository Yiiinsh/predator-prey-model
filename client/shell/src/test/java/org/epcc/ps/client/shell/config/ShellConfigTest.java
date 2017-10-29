package org.epcc.ps.client.shell.config;

import org.epcc.ps.client.shell.AbstractTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author shaohan.yin
 * Created on 23/10/2017
 */
public class ShellConfigTest extends AbstractTest {
    private ShellConfig config = ShellConfig.DEFAULT;

    @Test
    public void testShellConfig() {
        Assert.assertEquals(0.0, config.getDensityLowerBound(), 0);
        Assert.assertEquals(5.0, config.getDensityUpperBound(), 0);
        Assert.assertEquals(8, config.getHareDensityMaxVal());
        Assert.assertEquals(5, config.getPumaDensityMaxVal());
    }
}
