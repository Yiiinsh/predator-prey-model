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

package org.epcc.ps.client.shell.config;

/**
 * <p>Shell configuration interface.</p>
 * <p>
 *     This interface allows accessing to configurations for core module.
 *     A default implementation of the {@link DefaultShellConfig} class is available via
 *     {@code ShellConfig.DEFAULT} for convenience.
 * </p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 23/10/2017
 */
public interface ShellConfig {
    ShellConfig DEFAULT = new DefaultShellConfig();

    /**
     * Get the configuration of density lower bound
     *
     * @return density lower bound
     */
    double getDensityLowerBound();

    /**
     * Get the configuration of density upper bound
     *
     * @return density upper bound
     */
    double getDensityUpperBound();

    /**
     * Get the configuration of maximum hare density value
     *
     * @return hare density max value
     */
    int getHareDensityMaxVal();

    /**
     * Get the configuration of maximum puma density value
     *
     * @return puma density max value
     */
    int getPumaDensityMaxVal();
}
