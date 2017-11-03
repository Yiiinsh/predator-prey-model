package org.epcc.ps.client.shell.exception;

import org.epcc.ps.common.exception.AbstractException;

/**
 * <p>Exception while processing PPM files.</p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 28/10/2017
 */
public class PpmFileException extends AbstractException {
    public PpmFileException() {
    }

    public PpmFileException(String message) {
        super(message);
    }

    public PpmFileException(Throwable cause) {
        super(cause);
    }
}
