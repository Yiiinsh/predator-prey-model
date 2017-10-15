package org.pecc.ps.core.entity.environment;

import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.GridFactory;
import org.epcc.ps.core.entity.environment.Terrain;
import org.junit.Assert;
import org.junit.Test;
import org.pecc.ps.core.AbstractTest;

/**
 * @author shaohan.yin
 * Created on 12/10/2017
 */
public class GridFactoryTest extends AbstractTest {
    @Test
    public void testGridCreate() {
        Grid grid = GridFactory.create(Terrain.LAND);
        Assert.assertEquals(grid.getTerrain(), Terrain.LAND);
    }
}
