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

import org.epcc.ps.client.shell.AbstractTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author shaohan.yin
 * Created on 23/10/2017
 */
public class ShellConfigTest extends AbstractTest {
    private ShellConfig config = ShellConfig.DEFAULT;

    @Test
    public void testShellConfig() {
        Assert.assertEquals(0.0, config.getDensityLowerBound(), 0);
        Assert.assertEquals(5.0, config.getDensityUpperBound(), 0);
        Assert.assertEquals(5, config.getHareDensityMaxVal());
        Assert.assertEquals(5, config.getPumaDensityMaxVal());
    }
}
