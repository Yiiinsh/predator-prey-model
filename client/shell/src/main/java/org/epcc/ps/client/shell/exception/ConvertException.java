package org.epcc.ps.client.shell.exception;

import org.epcc.ps.common.exception.AbstractException;

/**
 * @author shaohan.yin
 * Created on 23/10/2017
 */
public class ConvertException extends AbstractException {
    public ConvertException() {
    }

    public ConvertException(String message) {
        super(message);
    }

    public ConvertException(Throwable cause) {
        super(cause);
    }
}
