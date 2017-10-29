package org.epcc.ps.client.shell.exception;

import org.epcc.ps.common.exception.AbstractException;

/**
 * @author shaohan.yin
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
