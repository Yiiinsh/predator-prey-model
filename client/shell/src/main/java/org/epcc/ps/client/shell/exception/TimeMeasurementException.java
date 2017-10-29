package org.epcc.ps.client.shell.exception;

import org.epcc.ps.common.exception.AbstractException;

/**
 * @author shaohan.yin
 * Created on 29/10/2017
 */
public class TimeMeasurementException extends AbstractException {
    public TimeMeasurementException() {
    }

    public TimeMeasurementException(String message) {
        super(message);
    }

    public TimeMeasurementException(Throwable cause) {
        super(cause);
    }
}
