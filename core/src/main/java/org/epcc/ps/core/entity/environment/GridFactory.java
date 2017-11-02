package org.epcc.ps.core.entity.environment;

/**
 * @author shaohan.yin
 * Created on 12/10/2017
 */
public class GridFactory {
    private GridFactory() {
    }

    public static Grid create(Terrain terrain) {
        return new Grid(terrain);
    }

    public static Grid create(Terrain terrain, double[] densities) {
        return new Grid(terrain, densities);
    }

    public static Grid create(Terrain terrain, double[] densities, int landNeighborCnt) {
        return new Grid(terrain, densities, landNeighborCnt);
    }
}
