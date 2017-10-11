package org.epcc.ps.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shaohan.yin
 * Created on 10/10/2017
 */
public abstract class AbstractException extends Exception {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public AbstractException() {
        super();
    }

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(Throwable cause) {
        super(cause);
    }
}
