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

package org.pecc.ps.core;

import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.GridFactory;
import org.epcc.ps.core.entity.environment.Terrain;

/**
 * @author shaohan.yin
 * Created on 16/10/2017
 */
public abstract class AbstractGridTest extends AbstractTest {
    protected Grid createGridWithWater() {
        return GridFactory.create(Terrain.WATER);
    }

    protected Grid createGridWithLand() {
        return GridFactory.create(Terrain.LAND);
    }

    protected void initGridDensity(Grid grid, double hareDensity, double pumaDensity) {
        grid.updateDensity(Species.HARE, hareDensity);
        grid.updateDensity(Species.PUMA, pumaDensity);
    }


}
