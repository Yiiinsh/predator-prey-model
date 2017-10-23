package org.epcc.ps.client.shell.util;

import org.epcc.ps.client.shell.config.ShellConfig;

import java.util.Random;

/**
 * @author shaohan.yin
 * Created on 23/10/2017
 */
public class SpeciesDensityGenerator {
    private static ShellConfig config = ShellConfig.DEFAULT;

    private SpeciesDensityGenerator() {

    }

    public static double generateDensity() {
        double lower = config.getDensityLowerBound();
        double upper = config.getDensityUpperBound();

        Random random = new Random();
        return lower + (upper - lower) * random.nextDouble();
    }
}
