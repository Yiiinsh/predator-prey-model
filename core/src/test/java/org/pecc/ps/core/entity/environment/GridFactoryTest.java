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

import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.GridFactory;
import org.epcc.ps.core.entity.environment.Terrain;
import org.junit.Assert;
import org.junit.Test;
import org.pecc.ps.core.AbstractTest;

/**
 * @author shaohan.yin
 * Created on 12/10/2017
 */
public class GridFactoryTest extends AbstractTest {
    @Test
    public void testGridCreate() {
        Grid grid = GridFactory.create(Terrain.LAND);
        Assert.assertEquals(Terrain.LAND, grid.getTerrain());

        double[] densities = new double[2];
        densities[Species.HARE.ordinal()] = 8;
        densities[Species.PUMA.ordinal()] = 1;
        grid = GridFactory.create(Terrain.LAND, densities);
        Assert.assertEquals(Terrain.LAND, grid.getTerrain());
        Assert.assertEquals(8, grid.getDensity(Species.HARE), 0);
        Assert.assertEquals(1, grid.getDensity(Species.PUMA), 0);

        grid = GridFactory.create(Terrain.LAND, densities, 2);
        Assert.assertEquals(2, grid.getLandNeighborCnt(), 0);
    }
}
