package org.epcc.ps.client.shell.util;

import org.epcc.ps.client.shell.config.ShellConfig;

import java.util.Random;

/**
 * <p>Species densities generator.</p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 23/10/2017
 */
public class SpeciesDensityGenerator {
    private static ShellConfig config = ShellConfig.DEFAULT;

    private SpeciesDensityGenerator() {

    }

    /**
     * Generate random density for initialization.
     *
     * @return random density
     */
    public static double generateDensity() {
        double lower = config.getDensityLowerBound();
        double upper = config.getDensityUpperBound();

        Random random = new Random();
        return lower + (upper - lower) * random.nextDouble();
    }
}
