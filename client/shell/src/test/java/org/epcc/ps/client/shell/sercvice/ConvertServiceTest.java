package org.epcc.ps.client.shell.sercvice;

import org.epcc.ps.client.shell.exception.ConvertException;
import org.epcc.ps.client.shell.service.ConvertService;
import org.epcc.ps.client.shell.service.DefaultConvertService;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author jiahao.cao
 * Created on 19/10/2017
 */
public class ConvertServiceTest {

    ConvertService convertService = new DefaultConvertService();

    @Test
    public void testShutdown() {
        convertService.awaitTermination(1, TimeUnit.SECONDS);
    }

    @Test
    public void testConvertToLandscapeWithPPM() throws InterruptedException {
        String test = "test.ppm";
        Grid[][] grids = new Grid[1][1];
        grids[0][0] = GridFactory.create(Terrain.LAND);
        convertService.convertLandscapeWithSpeciesToPPM(test,
                LandscapeFactory.create(1, 1, grids),
                Species.HARE);

        convertService.awaitTermination(2, TimeUnit.SECONDS);
        File testFile = new File(test);
        Assert.assertTrue(testFile.exists());
        testFile.delete();
    }

    @Test
    public void testConvertLandscapeFromFile() throws ConvertException {
        Landscape landscape = convertService.convertLandscapeFromFile("src/test/resources/file3.dat");
        Assert.assertEquals(3, landscape.getLength());
        Assert.assertEquals(3, landscape.getWidth());
        Grid[][] grid = landscape.getGrids();

        checkGrid(grid[0][0], Terrain.LAND, -1, -1, 2);
        checkGrid(grid[0][1], Terrain.LAND, -1, -1, 2);
        checkGrid(grid[0][2], Terrain.LAND, -1, -1, 2);
        checkGrid(grid[1][0], Terrain.LAND, -1, -1, 2);
        checkGrid(grid[1][1], Terrain.WATER, 0, 0, 4);
        checkGrid(grid[1][2], Terrain.LAND, -1, -1, 1);
        checkGrid(grid[2][0], Terrain.LAND, -1, -1, 2);
        checkGrid(grid[2][1], Terrain.LAND, -1, -1, 1);
        checkGrid(grid[2][2], Terrain.WATER, 0, 0, 2);

    }

    private void checkGrid(Grid grid, Terrain terrain, double hareDensity, double pumaDensity, int landNeighborCnt) {
        Assert.assertEquals(terrain, grid.getTerrain());

        if (hareDensity >= 0) {
            Assert.assertEquals(hareDensity, grid.getDensity(Species.HARE), 0);
        } else {
            Assert.assertTrue(grid.getDensity(Species.HARE) >= 0
                    && grid.getDensity(Species.HARE) <= 5);
        }
        if (pumaDensity >= 0) {
            Assert.assertEquals(pumaDensity, grid.getDensity(Species.PUMA), 0);
        } else {
            Assert.assertTrue(grid.getDensity(Species.PUMA) >= 0
                    && grid.getDensity(Species.PUMA) <= 5);
        }

        Assert.assertEquals(landNeighborCnt, grid.getLandNeighborCnt());
    }
}
