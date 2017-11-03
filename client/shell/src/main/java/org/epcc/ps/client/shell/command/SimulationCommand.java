package org.epcc.ps.client.shell.command;

import org.epcc.ps.core.config.DefaultCoreConfig;

/**
 * <p>Simulation command interface</p>
 * <p>This method provides a function to perform a simulations
 * A default implementation of the {@link DefaultCoreConfig} class is available via
 *     @code{ CoreConfig.DEFAULT} for convenience.</p>
 *
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 29/10/2017
 */
public interface SimulationCommand {
    SimulationCommand DEFAULT = new DefaultSimulationCommand();

    /**
     * <p>Trigger method for simulation.</p>
     *
     * @param args arguments for simulation
     */
    void simulate(String[] args);
}
