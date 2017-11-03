package org.epcc.ps.client.shell.exception;

import org.epcc.ps.common.exception.AbstractException;

/**
 * <p>Exception for simulation source file not found.</p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 29/10/2017
 */
public class SimulationSourceNotFoundException extends AbstractException {
    public SimulationSourceNotFoundException() {
    }

    public SimulationSourceNotFoundException(String message) {
        super(message);
    }

    public SimulationSourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
