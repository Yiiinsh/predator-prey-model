package org.epcc.ps.client.shell.config;

import org.epcc.ps.common.config.AbstractConfig;
import org.epcc.ps.common.config.Config;

/**
 * @author shaohan.yin
 * Created on 23/10/2017
 */
public class DefaultShellConfig extends AbstractConfig implements ShellConfig {
    public static final String KEY_DENSITY_LOWER_BOUND = "density.lower.bound";
    public static final String KEY_DENSITY_UPPER_BOUND = "density.upper.bound";

    private Config config = Config.DEFAULT;

    @Override
    public double getDensityLowerBound() {
        return config.getDouble(KEY_DENSITY_LOWER_BOUND, 0.0);
    }

    @Override
    public double getDensityUpperBound() {
        return config.getDouble(KEY_DENSITY_UPPER_BOUND, 5.0);
    }
}
