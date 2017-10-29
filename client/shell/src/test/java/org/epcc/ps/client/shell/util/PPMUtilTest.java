package org.epcc.ps.client.shell.util;

import org.epcc.ps.client.shell.exception.PPMFileException;
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
        PPMUtil.generateRedBasedPPMFile(TEST_FILE_NAME, 2, 2, 5,
                new double[][]{
                        {3,5},
                        {0,2}
                });
    }
}
