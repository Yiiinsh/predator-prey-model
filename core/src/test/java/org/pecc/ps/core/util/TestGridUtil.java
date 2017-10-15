package org.pecc.ps.core.util;

import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.GridFactory;
import org.epcc.ps.core.entity.environment.Terrain;

/**
 * @author shaohan.yin
 * Created on 15/10/2017
 */
public class TestGridUtil {
    private TestGridUtil() {
    }

    public static Grid createGridWithWater() {
        return GridFactory.create(Terrain.WATER);
    }

    public static Grid createGridWithLand() {
        return GridFactory.create(Terrain.LAND);
    }
}
