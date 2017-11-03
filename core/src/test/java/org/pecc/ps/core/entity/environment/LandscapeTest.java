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

package org.pecc.ps.core.entity.environment;

import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.Landscape;
import org.junit.Assert;
import org.junit.Test;
import org.pecc.ps.core.AbstractGridTest;

/**
 * @author shaohan.yin
 * Created on 12/10/2017
 */
public class LandscapeTest extends AbstractGridTest {
    @Test
    public void testLandscapeArgumentChecking() {
        Landscape landscape;

        try {
            logger.info("Initialize with length -1");
            landscape = new Landscape(-1, 1, new Grid[1][1]);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals("Length of Landscape must between 0 to 2000!", e.getMessage());
            logger.error(e.getMessage());
        }

        try {
            logger.info("Initialize with width -1");
            landscape = new Landscape(1, -1, new Grid[1][1]);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals("Width of Landscape must between 0 to 2000!", e.getMessage());
            logger.error(e.getMessage());
        }

        try {
            logger.info("Initialize with null grids");
            landscape = new Landscape(1, 1, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals("Grids on the Landscape cannot be null", e.getMessage());
            logger.error(e.getMessage());
        }

        try {
            logger.info("Initialize with unmatched grids and length");
            landscape = new Landscape(1, 3, new Grid[3][3]);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals("Length of Grids doesn't match!", e.getMessage());
            logger.error(e.getMessage());
        }

        try {
            logger.info("Initialize with unmatched grids and width");
            landscape = new Landscape(3, 1, new Grid[3][3]);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals("Width of Grids doesn't match!", e.getMessage());
            logger.error(e.getMessage());
        }

    }

    @Test
    public void testLandscapeInit() {
        Grid[][] grids = {
                {createGridWithLand(), createGridWithWater(), createGridWithWater()},
                {createGridWithLand(), createGridWithLand(), createGridWithWater()},
                {createGridWithLand(), createGridWithWater(), createGridWithWater()}
        };

        int[][] landNeighborCnt = {
                {1, 2, 0},
                {3, 1, 1},
                {1, 2, 0}
        };

        Landscape landscape = new Landscape(3, 3, grids);

        for (int x = 0; x != 3; ++x) {
            for (int y = 0; y != 3; ++y) {
                Assert.assertEquals(landNeighborCnt[x][y], landscape.getGrids()[x][y].getLandNeighborCnt());
            }
        }

    }
}
