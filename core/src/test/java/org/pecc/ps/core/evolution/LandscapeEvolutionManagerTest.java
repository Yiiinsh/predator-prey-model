package org.pecc.ps.core.evolution;

import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.Landscape;
import org.epcc.ps.core.entity.environment.LandscapeFactory;
import org.epcc.ps.core.evolution.LandscapeEvolutionManager;
import org.junit.Assert;
import org.junit.Test;
import org.pecc.ps.core.AbstractGridTest;

/**
 * @author shaohan.yin
 * Created on 16/10/2017
 */
public class LandscapeEvolutionManagerTest extends AbstractGridTest {
    private static final int EVOLUTION_CNT = 1252;

    @Test
    public void testEvolutionPerTimeStep() {
        Grid[][] grids = initTestGrid();
        Grid[][] target = initTestGrid();

        target[0][0].getCreatures().get(Species.HARE).updateDensity(4.76);
        target[0][0].getCreatures().get(Species.PUMA).updateDensity(2.032);
        target[0][1].getCreatures().get(Species.HARE).updateDensity(4.144);
        target[0][1].getCreatures().get(Species.PUMA).updateDensity(1.088);
        target[1][0].getCreatures().get(Species.HARE).updateDensity(3.112);
        target[1][0].getCreatures().get(Species.PUMA).updateDensity(2.92);

        Landscape landscape = LandscapeFactory.create(2, 2, grids);
        LandscapeEvolutionManager landscapeEvolutionManager = LandscapeEvolutionManager.create(landscape);

        landscapeEvolutionManager.evolutionPerTimeStep();

        Landscape landscapeAfterEvolutionOneTime = landscapeEvolutionManager.getLandscape();
        for(int xIdx = 0; xIdx != 2; ++xIdx) {
            for( int yIdx = 0; yIdx != 2; ++yIdx) {
                Assert.assertEquals(target[xIdx][yIdx].getDensity(Species.PUMA),
                        landscapeAfterEvolutionOneTime.getGrids()[xIdx][yIdx].getDensity(Species.PUMA), 0);
                Assert.assertEquals(target[xIdx][yIdx].getDensity(Species.HARE),
                        landscapeAfterEvolutionOneTime.getGrids()[xIdx][yIdx].getDensity(Species.HARE), 0);
            }
        }
    }

    @Test
    public void testEvolution() {
        Grid[][] grids = initTestGrid();
        Landscape landscape = LandscapeFactory.create(2, 2, grids);
        LandscapeEvolutionManager landscapeEvolutionManager = LandscapeEvolutionManager.create(landscape);
        landscapeEvolutionManager.evolution();

        Assert.assertEquals(EVOLUTION_CNT, landscapeEvolutionManager.getSnapshots().size(), 0);
    }

    private Grid[][] initTestGrid() {
        Grid[][] grids = {
                {createGridWithLand(), createGridWithLand()},
                {createGridWithLand(), createGridWithWater()}
        };

        initGridWithCreate(grids[0][0], Species.PUMA, 2.0);
        initGridWithCreate(grids[0][0], Species.HARE, 5.0);
        initGridWithCreate(grids[0][1], Species.PUMA, 1.0);
        initGridWithCreate(grids[0][1], Species.HARE, 4.0);
        initGridWithCreate(grids[1][0], Species.PUMA, 3.0);
        initGridWithCreate(grids[1][0], Species.HARE, 3.0);
        initGridWithCreate(grids[1][1], Species.PUMA, 0.0);
        initGridWithCreate(grids[1][1], Species.HARE, 0.0);

        return grids;
    }
}
