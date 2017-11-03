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

package org.epcc.ps.core.algorithm;

import org.epcc.ps.core.entity.environment.Terrain;

/**
 * <p>Default core algorithm class.</p>
 *
 * @author jiahao.cao
 * @since 0.0.1
 * Created on 11/10/2017
 */
public class DefaultCoreAlgorithm implements CoreAlgorithm {

    @Override
    public double getHaresDensity(Terrain terrain, double currentHareDensity,
                                  double currentHareDensityInLeftGrid, double currentHareDensityInRightGrid,
                                  double currentHareDensityInUpGrid, double currentHareDensityInDownGrid,
                                  double hareBirthRate, double pumaPredationRate, double currentPumaDensity,
                                  double hareDiffusionRate, double interval, int landNeighborCnt) {
        if (Terrain.WATER.equals(terrain)) {
            return 0.0;
        }

        return currentHareDensity + interval * (hareBirthRate * currentHareDensity
                    - pumaPredationRate * currentHareDensity * currentPumaDensity
                    + hareDiffusionRate * (currentHareDensityInLeftGrid + currentHareDensityInRightGrid
                        + currentHareDensityInUpGrid + currentHareDensityInDownGrid
                        - landNeighborCnt * currentHareDensity));
    }

    @Override
    public double getPumaDensity(Terrain terrain, double currentPumaDensity, double currentPumaDensityInLeftGrid,
                                 double currentPumaDensityInRightGrid, double currentPumaDensityInUpGrid,
                                 double currentPumaDensityInDownGrid,
                                 double pumaBirthRate, double currentHareDensity,
                                 double pumaDeathRate, double pumaDiffusionRate,
                                 double interval, int landNeighborCnt) {

        if (Terrain.WATER.equals(terrain)) {
            return 0.0;
        }

        return currentPumaDensity + interval * (pumaBirthRate * currentHareDensity * currentPumaDensity
                    - pumaDeathRate * currentPumaDensity
                    + pumaDiffusionRate * (currentPumaDensityInLeftGrid + currentPumaDensityInRightGrid
                        + currentPumaDensityInUpGrid + currentPumaDensityInDownGrid
                        - landNeighborCnt * currentPumaDensity));
    }
}
 