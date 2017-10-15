package org.pecc.ps.core.entity.environment;

import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.GridFactory;
import org.epcc.ps.core.entity.environment.Landscape;
import org.epcc.ps.core.entity.environment.Terrain;
import org.junit.Assert;
import org.junit.Test;
import org.pecc.ps.core.AbstractTest;

/**
 * @author shaohan.yin
 * Created on 12/10/2017
 */
public class LandscapeTest extends AbstractTest {
    @Test
    public void testLandscapeArgumentChecking() {
        Landscape landscape;

        try {
            logger.info("Initialize with length -1");
            landscape = new Landscape(-1,1, new Grid[1][1]);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals("Length of Landscape must between 0 to 2000!", e.getMessage());
            logger.error(e.getMessage());
        }

        try {
            logger.info("Initialize with width -1");
            landscape = new Landscape(1,-1, new Grid[1][1]);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals("Width of Landscape must between 0 to 2000!", e.getMessage());
            logger.error(e.getMessage());
        }

        try {
            logger.info("Initialize with null grids");
            landscape = new Landscape(1,1,null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals("Grids on the Landscape cannot be null", e.getMessage());
            logger.error(e.getMessage());
        }

        try {
            logger.info("Initialize with unmatched grids and length");
            landscape = new Landscape(1,3,new Grid[3][3]);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals("Length of Grids doesn't match!",e.getMessage());
            logger.error(e.getMessage());
        }

        try {
            logger.info("Initialize with unmatched grids and width");
            landscape = new Landscape(3,1,new Grid[3][3]);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals("Width of Grids doesn't match!",e.getMessage());
            logger.error(e.getMessage());
        }

    }

    @Test
    public void testLandscapeInit() {
        Grid[][] grids = {
                {createGridWithLand(),createGridWithWater(),createGridWithWater()},
                {createGridWithLand(),createGridWithLand(),createGridWithWater()},
                {createGridWithLand(),createGridWithWater(),createGridWithWater()}
        };

        int[][] landNeighborCnt = {
                {1,2,0},
                {3,1,1},
                {1,2,0}
        };

        Landscape landscape = new Landscape(3,3,grids);

        for(int x = 0; x != 3; ++x) {
            for(int y = 0; y != 3; ++y) {
                Assert.assertEquals(landscape.getGrids()[x][y].getLandNeighborCnt(), landNeighborCnt[x][y]);
            }
        }

    }

    private Grid createGridWithWater() {
        return GridFactory.create(Terrain.WATER);
    }

    private Grid createGridWithLand() {
        return GridFactory.create(Terrain.LAND);
    }
}
