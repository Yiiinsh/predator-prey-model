package org.epcc.ps.core.config;

/**
 * <p>Core configuration interface.</p>
 * <p>
 *     This interface allows accessing to configurations for core module.
 *     A default implementation of the {@link DefaultCoreConfig} class is available via
 *     {@code CoreConfig.DEFAULT} for convenience.
 * </p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 10/10/2017
 */
public interface CoreConfig {
    CoreConfig DEFAULT = new DefaultCoreConfig();

    /**
     * Get the configuration of evolution time step
     *
     * @return time step for evolution
     */
    double getLandscapeEvolutionTimeStep();

    /**
     * Get the configuration of evolution start time
     *
     * @return start time for evolution
     */
    double getLandscapeEvolutionTimeStart();

    /**
     * Get the configuration of evolution end time
     *
     * @return end time for evolution
     */
    double getLandscapeEvolutionTimeEnd();

    /**
     * Get the configuration of evolution output interval
     *
     * @return output interval for evolution
     */
    int getLandscapeEvolutionTimeOutput();

    /**
     * Get the configuration of landscape length limit
     *
     * @return limit of landscape length
     */
    int getLandscapeLengthLimit();

    /**
     * Get the configuration of landscape width limit
     *
     * @return limit of landscape width
     */
    int getLandscapeWidthLimit();

    /**
     * Get the configuration of puma birth rate
     *
     * @return birth rate of puma
     */
    double getPumaBirthRate();

    /**
     * Get the configuration of puma predation rate
     *
     * @return predation rate of puma
     */
    double getPumaPredationRate();

    /**
     * Get the configuration of puma morality rate
     *
     * @return mortality(death) rate of puma
     */
    double getPumaMortalityRate();

    /**
     * Get the configuration of puma diffusion rate
     *
     * @return diffusion rate of puma
     */
    double getPumaDiffusionRate();

    /**
     * Get the configuration of hare birth rate
     *
     * @return birth rate of hare
     */
    double getHareBirthRate();

    /**
     * Get the configuration of hare diffusion rate
     *
     * @return diffusion rate of hare
     */
    double getHareDiffusionRate();
}
