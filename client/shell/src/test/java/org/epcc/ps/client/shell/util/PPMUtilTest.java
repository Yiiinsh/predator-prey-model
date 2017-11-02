package org.epcc.ps.client.shell.util;

import org.epcc.ps.client.shell.exception.PPMFileException;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.*;
import org.junit.After;
import org.junit.Test;

import java.io.File;

/**
 * @author shaohan.yin
 * Created on 28/10/2017
 */
public class PPMUtilTest {
    private static final String TEST_FILE_NAME = "test.ppm";

    @After
    public void tearDown() {
        File file = new File(TEST_FILE_NAME);
        file.delete();
    }

    @Test
    public void testPPMUtil() throws PPMFileException {

        Grid[][] grids = new Grid[][] {
                {GridFactory.create(Terrain.LAND), GridFactory.create(Terrain.LAND)},
                {GridFactory.create(Terrain.LAND), GridFactory.create(Terrain.LAND)}
        };
        grids[0][0].updateDensity(Species.HARE, 3);
        grids[0][1].updateDensity(Species.HARE, 5);
        grids[1][0].updateDensity(Species.HARE, 0);
        grids[1][1].updateDensity(Species.HARE, 2);

        Landscape landscape = LandscapeFactory.create(2, 2, grids);

        PpmUtil.generateRedBasedPPMFileFromLandscape(TEST_FILE_NAME, 2, 2, 5,
                landscape, Species.HARE);
    }
}
