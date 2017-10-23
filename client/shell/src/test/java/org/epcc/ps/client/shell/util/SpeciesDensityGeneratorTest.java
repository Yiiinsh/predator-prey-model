package org.epcc.ps.client.shell.util;

import org.epcc.ps.client.shell.AbstractTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author shaohan.yin
 * Created on 23/10/2017
 */
public class SpeciesDensityGeneratorTest extends AbstractTest {
    @Test
    public void testSpeciesDensityGenerator() {
        double density;
        for(int i = 0; i != 1000; ++i) {
            density = SpeciesDensityGenerator.generateDensity();
            Assert.assertTrue(density > 0.0 && density < 5.0);
        }
    }
}
