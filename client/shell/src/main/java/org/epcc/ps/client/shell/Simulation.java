package org.epcc.ps.client.shell;

import org.epcc.ps.client.shell.command.SimulationCommand;
import org.epcc.ps.client.shell.proxy.SimulationTimeMeasurementProxyHandler;

import java.lang.reflect.Proxy;

/**
 * @author shaohan.yin
 * Created on 29/10/2017
 */
public class Simulation {

    public static void main(String[] args) {
        ((SimulationCommand) Proxy.newProxyInstance(SimulationCommand.class.getClassLoader(),
                new Class[]{SimulationCommand.class},
                new SimulationTimeMeasurementProxyHandler())).simulate(args);
    }
}
