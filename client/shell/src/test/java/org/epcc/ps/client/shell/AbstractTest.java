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

package org.epcc.ps.client.shell;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shaohan.yin
 * Created on 23/10/2017
 */
public class AbstractTest {
    @Rule
    public TestName testName = new TestName();
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Before
    public void beforeAbstractTest() {
        logger.info(remarkableMessage("[begin test][{}]"), testName.getMethodName());
    }

    @After
    public void afterAbstractTest() {
        logger.info(remarkableMessage("[end   test][{}]"), testName.getMethodName());
    }

    protected String remarkableMessage(String msg) {
        return String.format("------------%s------------\n", msg);
    }
}
