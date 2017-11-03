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

import org.epcc.ps.common.config.AbstractConfig;
import org.epcc.ps.common.config.Config;

/**
 * <p>Default shell configuration class.</p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 23/10/2017
 */
public class DefaultShellConfig extends AbstractConfig implements ShellConfig {
    public static final String KEY_DENSITY_LOWER_BOUND = "density.lower.bound";
    public static final String KEY_DENSITY_UPPER_BOUND = "density.upper.bound";
    public static final String KEY_DENSITY_HARE_MAX_VAL = "density.hare.max.val";
    public static final String KEY_DENSITY_PUMA_MAX_VAL = "density.puma.max.val";

    private Config config = Config.DEFAULT;

    @Override
    public double getDensityLowerBound() {
        return config.getDouble(KEY_DENSITY_LOWER_BOUND, 0.0);
    }

    @Override
    public double getDensityUpperBound() {
        return config.getDouble(KEY_DENSITY_UPPER_BOUND, 5.0);
    }

    @Override
    public int getHareDensityMaxVal() {
        return config.getInt(KEY_DENSITY_HARE_MAX_VAL, 8);
    }

    @Override
    public int getPumaDensityMaxVal() {
        return config.getInt(KEY_DENSITY_PUMA_MAX_VAL, 5);
    }


}
