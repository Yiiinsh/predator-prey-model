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

package org.epcc.ps.client.shell.util;

import org.epcc.ps.client.shell.config.ShellConfig;

import java.util.Random;

/**
 * <p>Species densities generator.</p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 23/10/2017
 */
public class SpeciesDensityGenerator {
    private static ShellConfig config = ShellConfig.DEFAULT;

    private SpeciesDensityGenerator() {

    }

    /**
     * Generate random density for initialization.
     *
     * @return random density
     */
    public static double generateDensity() {
        double lower = config.getDensityLowerBound();
        double upper = config.getDensityUpperBound();

        Random random = new Random();
        return lower + (upper - lower) * random.nextDouble();
    }
}
