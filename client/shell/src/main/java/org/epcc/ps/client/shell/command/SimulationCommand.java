package org.epcc.ps.client.shell.command;

/**
 * @author shaohan.yin
 * Created on 29/10/2017
 */
public interface SimulationCommand {
    SimulationCommand DEFAULT = new DefaultSimulationCommand();

    void simulate(String[] args) throws Exception;
}
