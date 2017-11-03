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

import org.epcc.ps.common.AbstractTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author shaohan.yin
 * Created on 09/10/2017
 */
public class ConfigTest extends AbstractTest {

    private static final String TEST_SPECIFIED_CONFIG_FILE = "test.properties";
    private static final String TEST_KEY_DEFAULT_CONFIG_INT = "default.test.int";
    private static final int TEST_VALUE_DEFAULT_CONFIG_INT = 666;
    private static final String TEST_KEY_DEFAULT_CONFIG_STRING = "default.test.string";
    private static final String TEST_VALUE_DEFAULT_CONFIG_STRING = "this is a test string from default file";
    private static final String TEST_KEY_SPECIFIED_FILE_CONFIG_INT = "specified.test.int";
    private static final int TEST_VALUE_SPECIFIED_FILE_CONFIG_INT = 777;
    private static final String TEST_KEY_SPECIFIED_FILE_CONFIG_STRING = "specified.test.string";
    private static final String TEST_VALUE_SPECIFIED_FILE_CONFIG_STRING = "this is a test string from a specified file";
    private static final String TEST_KEY_NO_EXIST = "no.exist";

    private Config config = Config.DEFAULT;
    private Config specifiedConfig = new DefaultConfig(TEST_SPECIFIED_CONFIG_FILE);

    @Test
    public void testDefaultConfigGet() {
        Assert.assertEquals(TEST_VALUE_DEFAULT_CONFIG_INT, config.getInt(TEST_KEY_DEFAULT_CONFIG_INT));
        Assert.assertEquals(TEST_VALUE_DEFAULT_CONFIG_STRING, config.getString(TEST_KEY_DEFAULT_CONFIG_STRING));
        Assert.assertNull(config.getString(TEST_KEY_NO_EXIST));
        Assert.assertEquals("no", config.getString(TEST_KEY_NO_EXIST, "no"));
    }

    @Test
    public void testSpecifiedFileConfig() {
        Assert.assertEquals(TEST_VALUE_SPECIFIED_FILE_CONFIG_INT, specifiedConfig.getInt(TEST_KEY_SPECIFIED_FILE_CONFIG_INT));
        Assert.assertEquals(TEST_VALUE_SPECIFIED_FILE_CONFIG_STRING, specifiedConfig.getString(TEST_KEY_SPECIFIED_FILE_CONFIG_STRING));
        Assert.assertNull(specifiedConfig.getString(TEST_KEY_NO_EXIST));
        Assert.assertEquals("no", specifiedConfig.getString(TEST_KEY_NO_EXIST, "no"));
    }

    @Test
    public void testNonExistingConfig() {
        Config nonExistingConfig = new DefaultConfig("noexist.properties");
        Assert.assertTrue(nonExistingConfig.isEmpty());
    }
}
