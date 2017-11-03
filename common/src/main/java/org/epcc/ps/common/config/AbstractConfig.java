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
