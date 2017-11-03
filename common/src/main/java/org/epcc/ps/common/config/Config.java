package org.epcc.ps.common.config;

import org.apache.commons.configuration2.Configuration;

/**
 * <p>The main configuration interface</p>
 * <p>
 *     This interface allows accessing and manipulating a configuration object.
 *     A default implementation of the {@link DefaultConfig} class is available via
 *     {@code Config.DEFAULT} for convenience.
 * </p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 09/10/2017
 */
public interface Config extends Configuration {
    Config DEFAULT = new DefaultConfig();
}
