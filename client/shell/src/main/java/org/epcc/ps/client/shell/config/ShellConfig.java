package org.epcc.ps.client.shell.config;

/**
 * @author shaohan.yin
 * Created on 23/10/2017
 */
public interface ShellConfig {
    ShellConfig DEFAULT = new DefaultShellConfig();

    double getDensityLowerBound();
    double getDensityUpperBound();

    int getHareDensityMaxVal();
    int getPumaDensityMaxVal();
}
