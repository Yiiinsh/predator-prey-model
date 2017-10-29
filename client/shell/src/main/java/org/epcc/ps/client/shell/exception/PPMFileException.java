package org.epcc.ps.client.shell.exception;

import org.epcc.ps.common.exception.AbstractException;

/**
 * @author shaohan.yin
 * Created on 28/10/2017
 */
public class PPMFileException extends AbstractException {
    public PPMFileException() {
    }

    public PPMFileException(String message) {
        super(message);
    }

    public PPMFileException(Throwable cause) {
        super(cause);
    }
}
