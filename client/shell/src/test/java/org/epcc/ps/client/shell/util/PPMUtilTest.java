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

import org.epcc.ps.client.shell.exception.PpmFileException;
import org.junit.After;
import org.junit.Test;

import java.io.File;

/**
 * @author shaohan.yin
 * Created on 28/10/2017
 */
public class PPMUtilTest {
    private static final String TEST_FILE_NAME = "test.ppm";

    @After
    public void tearDown() {
        File file = new File(TEST_FILE_NAME);
        file.delete();
    }

    @Test
    public void testPPMUtil() throws PpmFileException {
        double[][] grids = new double[][]{
                {3,5},
                {0,2}
        };

        PpmUtil.generateRedBasedPPMFileFromGrids(TEST_FILE_NAME, 2, 2, 5,
                grids);
    }
}
