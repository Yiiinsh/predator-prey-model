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
