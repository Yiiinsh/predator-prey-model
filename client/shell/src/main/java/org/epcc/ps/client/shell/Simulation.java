package org.epcc.ps.client.shell;

import org.epcc.ps.client.shell.command.SimulationCommand;

/**
 * @author shaohan.yin
 * Created on 29/10/2017
 */
public class Simulation {
    private static SimulationCommand simulationCommand = new SimulationCommand();

    public static void main(String[] args) throws Exception {
        simulationCommand.simulate(args);
    }
}
