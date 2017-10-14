package org.epcc.ps.core.entity.environment;

import com.google.common.base.Preconditions;

import org.epcc.ps.core.config.CoreConfig;
import org.epcc.ps.core.config.DefaultCoreConfig;
import org.epcc.ps.core.util.GridUtil;
import org.epcc.ps.core.util.implement.GridUtilImplement;

/**
 * @author shaohan.yin
 * Created on 11/10/2017
 */
public class Landscape {
    private final static int EXTRA_BORDER = 2;

    private CoreConfig config = new DefaultCoreConfig();
    private GridUtil gridutil=new GridUtilImplement();
    private int length;
    private int width;
    private Grid grids[][];

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
        Preconditions.checkArgument(length >= 1 && length <= config.getLandscapeLengthLimit(),
                String.format("Length of Landscape must between %d to %d!", 0, config.getLandscapeLengthLimit()));

        Preconditions.checkArgument(width >= 1 && width <= config.getLandscapeWidthLimit(),
                String.format("Width of Landscape must between %d to %d!", 0, config.getLandscapeWidthLimit()));

        Preconditions.checkNotNull(grids, "Grids on the Landscape cannot be null");

        Preconditions.checkArgument(grids.length == length, "Length of Grids doesn't match!");
        Preconditions.checkArgument(grids[0].length == width, "Width of Grids doesn't match!");
    }

    private void updateLandNeighborCnt() {
        Grid[][] gridsWithHaloBoundary = generateGridWithHaloBoundary(length, width, grids);

        for(int xIdx = 0; xIdx != length; ++xIdx) {
            for(int yIdx = 0; yIdx != width; ++yIdx) {
                grids[xIdx][yIdx].setLandNeighborCnt(
                gridutil.getNeighborCntWithType(xIdx + 1, yIdx + 1, gridsWithHaloBoundary, Terrain.LAND));
            }
        }
    }

    private Grid[][] generateGridWithHaloBoundary(int length, int width, Grid[][] grids) {
        Grid[][] gridsWithHaloBoundary = new Grid[length + EXTRA_BORDER][width + EXTRA_BORDER];

        for(int xIdx = 0; xIdx != length + EXTRA_BORDER ; ++xIdx) {
            gridsWithHaloBoundary[xIdx][0] = GridFactory.getInstance().create(Terrain.WATER);
            gridsWithHaloBoundary[xIdx][width + EXTRA_BORDER - 1] = GridFactory.getInstance().create(Terrain.WATER);
        }

        for(int yIdx = 0; yIdx != width + EXTRA_BORDER ; ++yIdx) {
            gridsWithHaloBoundary[0][yIdx] = GridFactory.getInstance().create(Terrain.WATER);
            gridsWithHaloBoundary[length + EXTRA_BORDER - 1][yIdx] = GridFactory.getInstance().create(Terrain.WATER);
        }

        for(int xIdx = 0; xIdx != length; ++xIdx) {
            System.arraycopy(grids[xIdx],0,gridsWithHaloBoundary[xIdx + 1], 1, grids[xIdx].length);
        }

        return gridsWithHaloBoundary;
    }

    

}
