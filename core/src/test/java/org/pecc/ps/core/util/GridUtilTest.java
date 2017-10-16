package org.pecc.ps.core.util;

import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.Terrain;
import org.epcc.ps.core.util.GridUtil;
import org.junit.Assert;
import org.junit.Test;
import org.pecc.ps.core.AbstractGridTest;

/**
 * @author shaohan.yin
 * Created on 16/10/2017
 */
public class GridUtilTest extends AbstractGridTest {
    @Test
    public void testGenerateGridWithHaloBoundary() {
        Grid grids[][]={
                {createGridWithLand(),createGridWithLand(),createGridWithLand()},
                {createGridWithLand(),createGridWithWater(),createGridWithLand()},
                {createGridWithLand(),createGridWithLand(),createGridWithWater()}
        };

        Grid[][] gridsWithHaloBoundary = GridUtil.generateGridWithHaloBoundary(3, 3, grids);

        Assert.assertEquals(5, gridsWithHaloBoundary.length);
        Assert.assertEquals(5, gridsWithHaloBoundary[0].length);

        for(int xIdx = 0; xIdx != 5; ++xIdx) {
            Assert.assertTrue(gridsWithHaloBoundary[xIdx][0].getTerrain().equals(Terrain.WATER));
            Assert.assertTrue(gridsWithHaloBoundary[xIdx][4].getTerrain().equals(Terrain.WATER));
        }
        for(int yIdx = 0; yIdx != 5; ++yIdx) {
            Assert.assertTrue(gridsWithHaloBoundary[0][yIdx].getTerrain().equals(Terrain.WATER));
            Assert.assertTrue(gridsWithHaloBoundary[4][yIdx].getTerrain().equals(Terrain.WATER));
        }
    }

    @Test
    public void testGetNeighborCntWithType() {
        Grid grids[][]={
                {createGridWithLand(),createGridWithLand(),createGridWithLand()},
                {createGridWithLand(),createGridWithWater(),createGridWithLand()},
                {createGridWithLand(),createGridWithLand(),createGridWithWater()}
        };

        Grid[][] gridsWithHaloBoundary = GridUtil.generateGridWithHaloBoundary(3, 3, grids);
        Assert.assertEquals(2, GridUtil.getNeighborCntWithType(1, 1,
                gridsWithHaloBoundary, Terrain.LAND));
        Assert.assertEquals(4, GridUtil.getNeighborCntWithType(2, 2,
                gridsWithHaloBoundary, Terrain.LAND));
    }
}
