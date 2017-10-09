package org.epcc.ps.config;

import org.apache.commons.configuration2.Configuration;

/**
 * @author shaohan.yin
 * Created on 09/10/2017
 */
public interface Config extends Configuration {
    Config DEFAULT = new DefaultConfig();
}
