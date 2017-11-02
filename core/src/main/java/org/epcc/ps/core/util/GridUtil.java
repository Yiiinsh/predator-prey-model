package org.epcc.ps.core.util;

import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.GridFactory;
import org.epcc.ps.core.entity.environment.Terrain;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author shaohan.yin
 * Created on 15/10/2017
 */
public class GridUtil {
    public static final int EXTRA_BORDER = 2;
    public static final int EXTRA_BORDER_OFFSET = 1;

    private GridUtil() {
    }

    public static Grid[][] generateGridWithHaloBoundary(int length, int width, Grid[][] grids) {
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

    public static int getNeighborCntWithType(int xIdx, int yIdx, Grid[][] gridsWithHaloBoundary, Terrain terrain) {
        checkNotNull(gridsWithHaloBoundary, "Grids cannot be null");
        checkArgument(xIdx >= 1 && xIdx <= gridsWithHaloBoundary.length - 2,
                "Index x must within halo boundary of grids");
        checkArgument(yIdx >= 1 && yIdx <= gridsWithHaloBoundary[0].length - 2,
                "Index y must within halo boundary of grids");

        int result = 0;

        result += gridsWithHaloBoundary[xIdx - 1][yIdx].getTerrain().equals(terrain) ? 1 : 0;
        result += gridsWithHaloBoundary[xIdx + 1][yIdx].getTerrain().equals(terrain) ? 1 : 0;
        result += gridsWithHaloBoundary[xIdx][yIdx - 1].getTerrain().equals(terrain) ? 1 : 0;
        result += gridsWithHaloBoundary[xIdx][yIdx + 1].getTerrain().equals(terrain) ? 1 : 0;

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
