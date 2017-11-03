/*
 * Copyright (C) 2017 the predator-prey-model authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.epcc.ps.common.config;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * <p>Default configuration class.</p>
 * <p>This class maintains a list of configuration objects from a given properties file.
 * If no source properties file specified, params.properties will be used as configuration source.</p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 09/10/2017
 */
public class DefaultConfig extends AbstractConfig implements Config {

    private static final String DEFAULT_CONFIG_SOURCE = "params.properties";

    public DefaultConfig() {
        this(DEFAULT_CONFIG_SOURCE);
    }

    public DefaultConfig(String source) {
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties().setFileName(source));

        try {
            Configuration configSource = builder.getConfiguration();
            copy(configSource);
        } catch (ConfigurationException e) {
            logger.error("Load config failed.");
            logger.error(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
