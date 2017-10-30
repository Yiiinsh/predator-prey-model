package org.epcc.ps.core.util;

import org.epcc.ps.core.entity.creature.CreatureFactory;
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

        for (int xIdx = 0; xIdx != length + EXTRA_BORDER; ++xIdx) {
            gridsWithHaloBoundary[xIdx][0] = GridFactory.create(Terrain.WATER);
            initGridCreatures(gridsWithHaloBoundary[xIdx][0]);

            gridsWithHaloBoundary[xIdx][width + EXTRA_BORDER - 1] = GridFactory.create(Terrain.WATER);
            initGridCreatures(gridsWithHaloBoundary[xIdx][width + EXTRA_BORDER - 1]);
        }

        for (int yIdx = 0; yIdx != width + EXTRA_BORDER; ++yIdx) {
            gridsWithHaloBoundary[0][yIdx] = GridFactory.create(Terrain.WATER);
            initGridCreatures(gridsWithHaloBoundary[0][yIdx]);

            gridsWithHaloBoundary[length + EXTRA_BORDER - 1][yIdx] = GridFactory.create(Terrain.WATER);
            initGridCreatures(gridsWithHaloBoundary[0][yIdx]);
        }

        for (int xIdx = 0; xIdx != length; ++xIdx) {
            System.arraycopy(grids[xIdx], 0, gridsWithHaloBoundary[xIdx + 1], 1, grids[xIdx].length);
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

    private static void initGridCreatures(Grid grid) {
        grid.getCreatures().put(Species.HARE, CreatureFactory.create(Species.HARE));
        grid.getCreatures().put(Species.PUMA, CreatureFactory.create(Species.PUMA));
    }
}
