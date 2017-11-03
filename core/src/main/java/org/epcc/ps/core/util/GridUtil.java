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

package org.epcc.ps.core.util;

import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.GridFactory;
import org.epcc.ps.core.entity.environment.Terrain;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <p>Grid Utils for computational convenience.</p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 15/10/2017
 */
public class GridUtil {
    public static final int EXTRA_BORDER = 2;
    public static final int EXTRA_BORDER_OFFSET = 1;

    private GridUtil() {
    }

    /**
     * <p>Generate a 2-D Grids array with halo(Boundary with water terrain)</p>
     *
     * @param length length of grids
     * @param width width of grids
     * @param grids current grids
     * @return grids with halo filled by water terrain
     */
    public static Grid[][] generateGridWithHalo(int length, int width, Grid[][] grids) {
        checkArgument(length > 0, "Length must greater than 0!");
        checkArgument(width > 0, "Width must greater than 0!");
        checkNotNull(grids, "Grids cannot be null!");
        checkArgument(length == grids.length, "Length not match!");
        checkArgument(length == grids[0].length, "Width not match!");

        Grid[][] gridsWithHaloBoundary = new Grid[length + EXTRA_BORDER][width + EXTRA_BORDER];

        for (int x = 0; x != length + EXTRA_BORDER; ++x) {
            gridsWithHaloBoundary[x][0] = initGridDensities(GridFactory.create(Terrain.WATER));
            gridsWithHaloBoundary[x][width + EXTRA_BORDER - 1] = initGridDensities(GridFactory.create(Terrain.WATER));
        }

        for (int y = 0; y != width + EXTRA_BORDER; ++y) {
            gridsWithHaloBoundary[0][y] = initGridDensities(GridFactory.create(Terrain.WATER));
            gridsWithHaloBoundary[length + EXTRA_BORDER - 1][y] = initGridDensities(GridFactory.create(Terrain.WATER));
        }

        for (int x = 0; x != length; ++x) {
            for (int y = 0; y != width; ++y) {
                gridsWithHaloBoundary[x + EXTRA_BORDER_OFFSET][y + EXTRA_BORDER_OFFSET]
                        = initGridDensities(GridFactory.create(grids[x][y].getTerrain()),
                        grids[x][y].getDensity(Species.HARE),
                        grids[x][y].getDensity(Species.PUMA));
                gridsWithHaloBoundary[x + EXTRA_BORDER_OFFSET][y + EXTRA_BORDER_OFFSET]
                        .setLandNeighborCnt(grids[x][y].getLandNeighborCnt());
            }
        }

        return gridsWithHaloBoundary;
    }

    /**
     * <p>Calculate the total count of directory(left, right, up, down) neighbor with given {@link Terrain}</p>
     *
     * @param x position x
     * @param y position y
     * @param gridsWithHalo grids with halo
     * @param terrain target terrain
     * @return total count of neighbor grids with given terrain
     */
    public static int getNeighborCntWithType(int x, int y, Grid[][] gridsWithHalo, Terrain terrain) {
        checkNotNull(gridsWithHalo, "Grids cannot be null");
        checkArgument(x >= 1 && x <= gridsWithHalo.length - 2,
                "Index x must within halo boundary of grids");
        checkArgument(y >= 1 && y <= gridsWithHalo[0].length - 2,
                "Index y must within halo boundary of grids");

        int result = 0;

        result += gridsWithHalo[x - 1][y].getTerrain().equals(terrain) ? 1 : 0;
        result += gridsWithHalo[x + 1][y].getTerrain().equals(terrain) ? 1 : 0;
        result += gridsWithHalo[x][y - 1].getTerrain().equals(terrain) ? 1 : 0;
        result += gridsWithHalo[x][y + 1].getTerrain().equals(terrain) ? 1 : 0;

        return result;
    }

    private static Grid initGridDensities(Grid grid) {
        grid.updateDensity(Species.HARE, 0.0);
        grid.updateDensity(Species.PUMA, 0.0);
        return grid;
    }

    private static Grid initGridDensities(Grid grid, double hareDensity, double pumaDensity) {
        grid.updateDensity(Species.HARE, hareDensity);
        grid.updateDensity(Species.PUMA, pumaDensity);
        return grid;
    }
}
