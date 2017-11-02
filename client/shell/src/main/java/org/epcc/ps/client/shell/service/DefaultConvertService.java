package org.epcc.ps.client.shell.service;

import org.epcc.ps.client.shell.config.ShellConfig;
import org.epcc.ps.client.shell.exception.ConvertException;
import org.epcc.ps.client.shell.exception.PPMFileException;
import org.epcc.ps.client.shell.util.PpmUtil;
import org.epcc.ps.client.shell.util.SpeciesDensityGenerator;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jiahao.cao
 * Created on 18/10/2017
 */
public class DefaultConvertService extends AbstractService implements ConvertService {
    private static int pumaDensityMaxVal;
    private static int hareDensityMaxVal;

    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);

    static {
        ShellConfig config = ShellConfig.DEFAULT;
        pumaDensityMaxVal = config.getPumaDensityMaxVal();
        hareDensityMaxVal = config.getHareDensityMaxVal();
    }

    @Override
    public Landscape convertLandscapeFromFile(String fileSource) throws ConvertException {
        Grid[][] grids = generateGridByMap(readMapFromFile(fileSource));
        Landscape landscape = LandscapeFactory.create(grids.length, grids[0].length, grids);
        return landscape;
    }

    @Override
    public void convertLandscapeWithSpeciesToPPM(String fileName, Landscape landscape, Species species)
            throws PPMFileException {
        switch (species) {
            case PUMA:
                fixedThreadPool.submit(() -> {
                    try {
                        PpmUtil.generateRedBasedPPMFileFromGrids(fileName, landscape.getLength(), landscape.getWidth(),
                                pumaDensityMaxVal, getDensitiesFromLandscape(landscape, species));
                    } catch (PPMFileException e) {
                        logger.error("PPM output failed.", e);
                    }
                });
                break;
            case HARE:
                fixedThreadPool.submit(() -> {
                    try {
                        PpmUtil.generateRedBasedPPMFileFromGrids(fileName, landscape.getLength(), landscape.getWidth(),
                                hareDensityMaxVal, getDensitiesFromLandscape(landscape, species));
                    } catch (PPMFileException e) {
                        logger.error("PPM output failed.", e);
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void shutdown() {
        fixedThreadPool.shutdown();
    }

    /***
     * Get land by reading map file
     *
     * */
    private int[][] readMapFromFile(String fileSource) throws ConvertException {
        File file = new File(fileSource);

        try (Scanner scanner = new Scanner(file)) {
            int height = scanner.nextInt();
            int width = scanner.nextInt();
            int[][] land = new int[height][width];
            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    land[i][j] = scanner.nextInt();
                }
            }

            return land;
        } catch (FileNotFoundException e) {
            logger.error("Load file failed.", e);
            throw new ConvertException(e.getMessage());
        }
    }

    /***
     * Initialize grids by map
     *
     * */
    private Grid[][] generateGridByMap(int[][] land) {
        Grid[][] grids = new Grid[land.length][land[0].length];
        for (int i = 0; i != land.length; ++i) {
            for (int j = 0; j != land[0].length; ++j) {
                if (1 == land[i][j]) {
                    grids[i][j] = GridFactory.create(Terrain.LAND);
                    initGrid(grids[i][j],
                            SpeciesDensityGenerator.generateDensity(), SpeciesDensityGenerator.generateDensity());
                } else {
                    grids[i][j] = GridFactory.create(Terrain.WATER);
                    initGrid(grids[i][j], 0.0, 0.0);
                }
            }
        }
        return grids;
    }

    private void initGrid(Grid grid, double hareDensity, double pumaDensity) {
        grid.updateDensity(Species.HARE, hareDensity);
        grid.updateDensity(Species.PUMA, pumaDensity);
    }

    private double[][] getDensitiesFromLandscape(Landscape landscape, Species species) {
        double[][] densities = new double[landscape.getLength()][landscape.getWidth()];
        for (int x = 0; x != landscape.getLength(); ++x) {
            for (int y = 0; y != landscape.getWidth(); ++y) {
                densities[x][y] = landscape.getGrids()[x][y].getDensity(species);
            }
        }
        return densities;
    }
}

