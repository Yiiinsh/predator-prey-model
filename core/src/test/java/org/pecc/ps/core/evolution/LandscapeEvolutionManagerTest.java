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

package org.pecc.ps.core.evolution;

import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.Landscape;
import org.epcc.ps.core.entity.environment.LandscapeFactory;
import org.epcc.ps.core.evolution.LandscapeEvolutionManager;
import org.junit.Assert;
import org.junit.Test;
import org.pecc.ps.core.AbstractGridTest;

/**
 * @author shaohan.yin
 * Created on 16/10/2017
 */
public class LandscapeEvolutionManagerTest extends AbstractGridTest {
    @Test
    public void testEvolutionPerTimeStep() {
        Grid[][] grids = initTestGrid();
        Grid[][] target = initTestGrid();

        target[0][0].updateDensity(Species.HARE, 4.76);
        target[0][0].updateDensity(Species.PUMA, 2.032);
        target[0][1].updateDensity(Species.HARE, 4.144);
        target[0][1].updateDensity(Species.PUMA, 1.088);
        target[1][0].updateDensity(Species.HARE, 3.112);
        target[1][0].updateDensity(Species.PUMA, 2.92);

        Landscape landscape = LandscapeFactory.create(2, 2, grids);
        LandscapeEvolutionManager landscapeEvolutionManager = LandscapeEvolutionManager.create(landscape);

        landscapeEvolutionManager.evolution(0.4);

        Landscape landscapeAfterEvolutionOneTime = landscapeEvolutionManager.getLandscape();
        for (int x = 0; x != 2; ++x) {
            for (int y = 0; y != 2; ++y) {
                Assert.assertEquals(target[x][y].getDensity(Species.PUMA),
                        landscapeAfterEvolutionOneTime.getGrids()[x][y].getDensity(Species.PUMA), 0);
                Assert.assertEquals(target[x][y].getDensity(Species.HARE),
                        landscapeAfterEvolutionOneTime.getGrids()[x][y].getDensity(Species.HARE), 0);
            }
        }
    }

    private Grid[][] initTestGrid() {
        Grid[][] grids = {
                {createGridWithLand(), createGridWithLand()},
                {createGridWithLand(), createGridWithWater()}
        };

        initGridDensity(grids[0][0], 5.0, 2.0);
        initGridDensity(grids[0][1], 4.0, 1.0);
        initGridDensity(grids[1][0], 3.0, 3.0);
        initGridDensity(grids[1][1], 0.0, 0.0);

        return grids;
    }
}
