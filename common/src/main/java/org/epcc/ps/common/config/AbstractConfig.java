package org.epcc.ps.common.config;

import org.apache.commons.configuration2.BaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> Basic configuration class. </p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 09/10/2017
 */
public abstract class AbstractConfig extends BaseConfiguration {
    protected Logger logger = LoggerFactory.getLogger(getClass());
}
