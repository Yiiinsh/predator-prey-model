package org.epcc.ps.core.config;

import org.epcc.ps.common.config.Config;

/**
 * @author shaohan.yin
 * Created on 10/10/2017
 */
public class DefaultCoreConfig implements CoreConfig {
    private final static String KEY_PUMA_BIRTH_RATE = "puma.birth_rate";
    private final static String KEY_PUMA_PREDATION_RATE = "puma.predation_rate";
    private final static String KEY_PUMA_MORTALITY_RATE = "puma.mortality_rate";
    private final static String KEY_PUMA_DIFFUSION_RATE = "puma.diffusion_rate";
    private final static String KEY_HARE_BIRTH_RATE = "hare.birth_rate";
    private final static String KEY_HARE_DIFFUSION_RATE = "hare.diffusion_rate";

    private Config config = Config.DEFAULT;

    @Override
    public double getPumaBirthRate() {
        return config.getDouble(KEY_PUMA_BIRTH_RATE);
    }

    @Override
    public double getPumaPredationRate() {
        return config.getDouble(KEY_PUMA_PREDATION_RATE);
    }

    @Override
    public double getPumaMortalityRate() {
        return config.getDouble(KEY_PUMA_MORTALITY_RATE);
    }

    @Override
    public double getPumaDiffusionRate() {
        return config.getDouble(KEY_PUMA_DIFFUSION_RATE);
    }

    @Override
    public double getHareBirthRate() {
        return config.getDouble(KEY_HARE_BIRTH_RATE);
    }

    @Override
    public double getHareDiffutionRate() {
        return config.getDouble(KEY_HARE_DIFFUSION_RATE);
    }
}
