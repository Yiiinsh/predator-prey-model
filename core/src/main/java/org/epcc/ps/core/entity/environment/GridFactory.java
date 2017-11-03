package org.epcc.ps.core.entity.environment;

/**
 * <p>Factory methods for {@link Grid}.</p>
 * <p>This class contains three methods with different parameters to create a new grid.</p>
 *
 * @author shaohan.yin
 * @since 0.0.1
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
