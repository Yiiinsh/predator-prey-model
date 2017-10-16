package org.pecc.ps.core;


import org.apache.commons.lang3.SerializationUtils;
import org.epcc.ps.core.entity.environment.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author shaohan.yin
 * Created on 12/10/2017
 */
public class SimpleTest extends AbstractTest {
    @Test
    public void testArrayCopyToUnmatchedSizeArray() {
        int[][] origin = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        int[][] target = new int [5][5];

        for(int xIdx = 0; xIdx != origin.length; ++xIdx) {
            System.arraycopy(origin[xIdx],0,target[xIdx + 1],1,origin[xIdx].length);
        }

        for(int xIdx = 0; xIdx != origin.length; ++xIdx) {
            for(int yIdx = 0; yIdx != origin[xIdx].length; ++yIdx) {
                Assert.assertEquals(target[xIdx + 1][yIdx + 1], origin[xIdx][yIdx]);
            }
        }
    }

    @Test
    public void testDeepCloneUsingSerializationUtil() {
        Grid[][] grids = {
                {GridFactory.create(Terrain.LAND), GridFactory.create(Terrain.WATER)},
                {GridFactory.create(Terrain.LAND), GridFactory.create(Terrain.LAND)}
        };
        Landscape landscape = LandscapeFactory.create(2, 2, grids);

        Landscape clonedLandscape = SerializationUtils.clone(landscape);

        Assert.assertNotEquals(landscape, clonedLandscape);
        for(int xIdx = 0; xIdx != 2; ++xIdx) {
            for(int yIdx = 0; yIdx != 2; ++yIdx) {
                Assert.assertNotEquals(landscape.getGrids()[xIdx][yIdx], clonedLandscape.getGrids()[xIdx][yIdx]);
            }
        }
    }
}
