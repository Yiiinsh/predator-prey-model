package org.epcc.ps.client.shell.util;

import com.google.common.base.Preconditions;
import org.epcc.ps.client.shell.exception.PpmFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

/**
 * <p>PPM file util class.</p>
 * <p>This class contains a method which helps generate PPM output form given 2D array.</p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 28/10/2017
 */
public class PpmUtil {
    private static final Logger logger = LoggerFactory.getLogger(PpmUtil.class);

    private static final String PLAIN_PPM_MAGIC_NUMBER = "P3";
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final int RGB_MAX_VAL = 80;

    private PpmUtil() {

    }

    /**
     * Generate plain PPM file for given 2D array.
     *
     * @param fileName target output file name
     * @param width width of given array
     * @param height height of given array
     * @param maxVal max output value for plain PPM file
     * @param grids data source of 2D array
     * @throws PpmFileException if fail to write ppm file
     */
    public static void generateRedBasedPPMFileFromGrids(String fileName, int width, int height,
                                                        int maxVal, double[][] grids) throws PpmFileException {
        Preconditions.checkNotNull(grids);
        Preconditions.checkArgument(grids.length > 0);
        Preconditions.checkArgument(height == grids.length, "Height not match!");
        Preconditions.checkArgument(width == grids[0].length, "Width not match!");

        try (PrintWriter printWriter = new PrintWriter(fileName, DEFAULT_ENCODING)) {
            printWriter.println(PLAIN_PPM_MAGIC_NUMBER);
            printWriter.printf("%d %d\n", width, height);
            printWriter.println(RGB_MAX_VAL);

            double portion;
            for (int x = 0; x != height; ++x) {
                for (int y = 0; y != width; ++y) {

                    if (0 == grids[x][y]) {
                        printWriter.printf(" %d  %d  %d ", 0, 0, RGB_MAX_VAL);
                    } else {
                        portion = grids[x][y] / maxVal;
                        printWriter.printf(" %d  %d  %d ", RGB_MAX_VAL,
                                RGB_MAX_VAL - (int) (RGB_MAX_VAL * portion), 0);
                    }

                }
                printWriter.printf("\n");
            }

        } catch (Exception e) {
            logger.error("Cannot generate PPM file.", e);
            throw new PpmFileException(e);
        }
    }
}
