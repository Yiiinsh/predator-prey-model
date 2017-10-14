package org.pecc.ps.core;



import org.epcc.ps.core.algorithm.CoreAlgorithm;
import org.epcc.ps.core.algorithm.implement.CoreAlgorithmImp;
import org.epcc.ps.core.config.CoreConfig;
import org.epcc.ps.core.config.DefaultCoreConfig;
import org.epcc.ps.core.entity.creature.Creature;
import org.epcc.ps.core.entity.creature.CreatureFactory;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.GridFactory;
import org.epcc.ps.core.entity.environment.Landscape;
import org.epcc.ps.core.entity.environment.LandscapeFactory;
import org.epcc.ps.core.entity.environment.Terrain;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author shaohan.yin
 * Created on 12/10/2017
 */
public class SimpleTest extends AbstractTest {
	private CoreConfig config = new DefaultCoreConfig();
	private CoreAlgorithm ca=new CoreAlgorithmImp();
    @Test
    public void testArrayCopyToUnmatchedSizeArray() {
        int[][] origin = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        int[][] target = new int [5][5];

        for(int xIdx = 0; xIdx != origin.length; ++xIdx) {
            System.arraycopy(origin[xIdx],0,target[xIdx + 1],1,origin[xIdx].length);
        }

        for(int xIdx = 0; xIdx != origin.length; ++xIdx) {
            for(int yIdx = 0; yIdx != origin[xIdx].length; ++yIdx) {
                Assert.assertEquals(origin[xIdx][yIdx], target[xIdx + 1][yIdx + 1]);
            }
        }
    }   
}
