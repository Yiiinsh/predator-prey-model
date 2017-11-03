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

import org.epcc.ps.core.config.CoreConfig;
import org.epcc.ps.core.config.DefaultCoreConfig;
import org.epcc.ps.core.util.GridUtil;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <p>A class represents landscape for simulation</p>
 * <p>This class maintain a 2-D array for grids.</p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 11/10/2017
 */
public class Landscape implements Serializable {
    private static CoreConfig config = new DefaultCoreConfig();

    private int length;
    private int width;
    private Grid[][] grids;

    public Landscape(int length, int width, Grid[][] grids) {
        checkArguments(length, width, grids);

        this.length = length;
        this.width = width;
        this.grids = grids;

        updateLandNeighborCnt();
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public Grid[][] getGrids() {
        return grids;
    }

    @Override
    public String toString() {
        return String.format("%d * %d Landscape", length, width);
    }

    private void checkArguments(int length, int width, Grid[][] grids) {
        checkArgument(length >= 1 && length <= config.getLandscapeLengthLimit(),
                String.format("Length of Landscape must between %d to %d!", 0, config.getLandscapeLengthLimit()));
        checkArgument(width >= 1 && width <= config.getLandscapeWidthLimit(),
                String.format("Width of Landscape must between %d to %d!", 0, config.getLandscapeWidthLimit()));
        checkNotNull(grids, "Grids on the Landscape cannot be null");

        checkArgument(grids.length == length, "Length of Grids doesn't match!");
        checkArgument(grids[0].length == width, "Width of Grids doesn't match!");
    }

    private void updateLandNeighborCnt() {
        Grid[][] gridsWithHaloBoundary = GridUtil.generateGridWithHalo(length, width, grids);

        for (int xIdx = 0; xIdx != length; ++xIdx) {
            for (int yIdx = 0; yIdx != width; ++yIdx) {
                grids[xIdx][yIdx].setLandNeighborCnt(
                        GridUtil.getNeighborCntWithType(xIdx + 1, yIdx + 1, gridsWithHaloBoundary, Terrain.LAND));
            }
        }
    }

}
