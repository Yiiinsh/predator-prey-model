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

package org.epcc.ps.core.entity.environment;

/**
 * <p>Factory methods for {@link Grid}.</p>
 * <p>This class contains three methods with different parameters to create a new grid.</p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 12/10/2017
 */
public class GridFactory {
    private GridFactory() {
    }

    public static Grid create(Terrain terrain) {
        return new Grid(terrain);
    }

    public static Grid create(Terrain terrain, double[] densities) {
        return new Grid(terrain, densities);
    }

    public static Grid create(Terrain terrain, double[] densities, int landNeighborCnt) {
        return new Grid(terrain, densities, landNeighborCnt);
    }
}
