package org.epcc.ps.client.shell.util;

import com.google.common.base.Preconditions;
import org.epcc.ps.client.shell.exception.PPMFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

/**
 * @author shaohan.yin
 * Created on 28/10/2017
 */
public class PPMUtil {
    private static final Logger logger = LoggerFactory.getLogger(PPMUtil.class);

    private static final String PLAIN_PPM_MAGIC_NUMBER = "P3";
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final int RGB_MAX_VAL = 50;

    private PPMUtil() {

    }

    public static void generateRedBasedPPMFile(String fileName, int width, int height,
                                               int maxVal, double[][] source) throws PPMFileException {
        Preconditions.checkNotNull(source);
        Preconditions.checkArgument(source.length > 0);
        Preconditions.checkArgument(height == source.length, "Height not match!");
        Preconditions.checkArgument(width == source[0].length, "Width not match!");


        try (PrintWriter printWriter = new PrintWriter(fileName, DEFAULT_ENCODING)) {
            printWriter.println(PLAIN_PPM_MAGIC_NUMBER);
            printWriter.printf("%d %d\n", width, height);
            printWriter.println(RGB_MAX_VAL);

            for (int xIdx = 0; xIdx != height; ++xIdx) {
                for (int yIdx = 0; yIdx != width; ++yIdx) {
                    double portion = source[xIdx][yIdx] / maxVal;
                    printWriter.printf(" %d  %d  %d ", RGB_MAX_VAL,
                            RGB_MAX_VAL - (int) (RGB_MAX_VAL * portion), 0);
                }
                printWriter.printf("\n");
            }

        } catch (Exception e) {
            logger.error("Cannot generate PPM file.", e);
            throw new PPMFileException(e);
        }
    }
}
