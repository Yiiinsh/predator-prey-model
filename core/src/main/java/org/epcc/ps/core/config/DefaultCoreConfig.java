package org.epcc.ps.core.config;

import org.epcc.ps.common.config.Config;

/**
 * <p>Default core configuration class.</p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 10/10/2017
 */
public class DefaultCoreConfig implements CoreConfig {
    private final static String KEY_EVOLUTION_TIME_STEP = "evolution.time.step";
    private final static String KEY_EVOLUTION_TIME_START = "evolution.time.start";
    private final static String KEY_EVOLUTION_TIME_END = "evolution.time.end";
    private final static String KEY_EVOLUTION_TIME_OUTPUT = "evolution.time.output";
    private final static String KEY_LANDSCAPE_LENGTH_LIMIT = "landscape.length.limit";
    private final static String KEY_LANDSCAPE_WIDTH_LIMIT = "landscape.width.limit";
    private final static String KEY_PUMA_BIRTH_RATE = "puma.birth_rate";
    private final static String KEY_PUMA_PREDATION_RATE = "puma.predation_rate";
    private final static String KEY_PUMA_MORTALITY_RATE = "puma.mortality_rate";
    private final static String KEY_PUMA_DIFFUSION_RATE = "puma.diffusion_rate";
    private final static String KEY_HARE_BIRTH_RATE = "hare.birth_rate";
    private final static String KEY_HARE_DIFFUSION_RATE = "hare.diffusion_rate";

    private Config config = Config.DEFAULT;

    @Override
    public double getLandscapeEvolutionTimeStep() {
        return config.getDouble(KEY_EVOLUTION_TIME_STEP);
    }

    @Override
    public double getLandscapeEvolutionTimeStart() {
        return config.getDouble(KEY_EVOLUTION_TIME_START);
    }

    @Override
    public double getLandscapeEvolutionTimeEnd() {
        return config.getDouble(KEY_EVOLUTION_TIME_END);
    }

    @Override
    public int getLandscapeEvolutionTimeOutput() {
        return config.getInt(KEY_EVOLUTION_TIME_OUTPUT);
    }

    @Override
    public int getLandscapeLengthLimit() {
        return config.getInt(KEY_LANDSCAPE_LENGTH_LIMIT);
    }

    @Override
    public int getLandscapeWidthLimit() {
        return config.getInt(KEY_LANDSCAPE_WIDTH_LIMIT);
    }

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
    public double getHareDiffusionRate() {
        return config.getDouble(KEY_HARE_DIFFUSION_RATE);
    }
}
