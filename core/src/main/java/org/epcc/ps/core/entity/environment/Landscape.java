package org.epcc.ps.core.entity.environment;

import org.epcc.ps.core.config.CoreConfig;
import org.epcc.ps.core.config.DefaultCoreConfig;
import org.epcc.ps.core.util.GridUtil;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author shaohan.yin
 * Created on 11/10/2017
 */
public class Landscape implements Serializable {
    private static CoreConfig config = new DefaultCoreConfig();

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
        checkArgument(length >= 1 && length <= config.getLandscapeLengthLimit(),
                String.format("Length of Landscape must between %d to %d!", 0, config.getLandscapeLengthLimit()));
        checkArgument(width >= 1 && width <= config.getLandscapeWidthLimit(),
                String.format("Width of Landscape must between %d to %d!", 0, config.getLandscapeWidthLimit()));
        checkNotNull(grids, "Grids on the Landscape cannot be null");

        checkArgument(grids.length == length, "Length of Grids doesn't match!");
        checkArgument(grids[0].length == width, "Width of Grids doesn't match!");
    }

    private void updateLandNeighborCnt() {
        Grid[][] gridsWithHaloBoundary = GridUtil.generateGridWithHaloBoundary(length, width, grids);

        for(int xIdx = 0; xIdx != length; ++xIdx) {
            for(int yIdx = 0; yIdx != width; ++yIdx) {
                grids[xIdx][yIdx].setLandNeighborCnt(
                GridUtil.getNeighborCntWithType(xIdx + 1, yIdx + 1, gridsWithHaloBoundary, Terrain.LAND));
            }
        }
    }

}
