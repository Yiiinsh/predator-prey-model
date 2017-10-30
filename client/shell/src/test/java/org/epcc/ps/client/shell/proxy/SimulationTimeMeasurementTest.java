package org.epcc.ps.client.shell.proxy;

import org.epcc.ps.client.shell.command.SimulationCommand;

import java.lang.reflect.Proxy;

/**
 * @author shaohan.yin
 * Created on 29/10/2017
 */
public class SimulationTimeMeasurementTest {
    public static void main(String[] args) throws Exception {
        ((SimulationCommand) Proxy.newProxyInstance(SimulationCommand.class.getClassLoader(),
                new Class[]{SimulationCommand.class},
                new SimulationTimeMeasurementProxyHandler())).simulate(new String[]{
                "-f", "src/test/resources/file3.dat", "-i", "500"
        });
    }

}
