package org.epcc.ps.client.shell.proxy;

import com.google.common.base.Stopwatch;
import org.epcc.ps.client.shell.command.SimulationCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * <p>Dynamic proxy handler for execution time measurement.</p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 29/10/2017
 */
public class SimulationTimeMeasurementProxyHandler implements InvocationHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private SimulationCommand simulationCommand = SimulationCommand.DEFAULT;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return invokeWithTimeMeasurement(simulationCommand, method, args);
    }

    private Object invokeWithTimeMeasurement(Object proxy, Method method, Object[] args) throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Object result = method.invoke(proxy, args);
        stopwatch.stop();

        logger.info("Execution Time: {}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return result;
    }
}
