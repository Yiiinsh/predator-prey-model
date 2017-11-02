package org.epcc.ps.client.shell.util;

import com.google.common.base.Preconditions;
import org.epcc.ps.client.shell.exception.PPMFileException;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Landscape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

/**
 * @author shaohan.yin
 * Created on 28/10/2017
 */
public class PpmUtil {
    private static final Logger logger = LoggerFactory.getLogger(PpmUtil.class);

    private static final String PLAIN_PPM_MAGIC_NUMBER = "P3";
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final int RGB_MAX_VAL = 80;

    private PpmUtil() {

    }

    public static void generateRedBasedPPMFileFromLandscape(String fileName, int width, int height,
                                                            int maxVal, Landscape landscape, Species species) throws PPMFileException {
        Preconditions.checkNotNull(landscape);
        Preconditions.checkArgument(landscape.getGrids().length > 0);
        Preconditions.checkArgument(height == landscape.getGrids().length, "Height not match!");
        Preconditions.checkArgument(width == landscape.getGrids()[0].length, "Width not match!");

        try (PrintWriter printWriter = new PrintWriter(fileName, DEFAULT_ENCODING)) {
            printWriter.println(PLAIN_PPM_MAGIC_NUMBER);
            printWriter.printf("%d %d\n", width, height);
            printWriter.println(RGB_MAX_VAL);

            double portion;
            for (int x = 0; x != height; ++x) {
                for (int y = 0; y != width; ++y) {

                    portion = landscape.getGrids()[x][y].getDensity(species) / maxVal;
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
