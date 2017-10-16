package org.pecc.ps.core.algorithm;

import org.epcc.ps.core.algorithm.CoreAlgorithm;
import org.epcc.ps.core.algorithm.impl.CoreAlgorithmImpl;
import org.epcc.ps.core.entity.creature.Creature;
import org.epcc.ps.core.entity.creature.CreatureFactory;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.Terrain;
import org.epcc.ps.core.util.GridUtil;
import org.junit.Assert;
import org.junit.Test;
import org.pecc.ps.core.AbstractGridTest;

/**
 * @author jiahao.cao
 * Created on 10/13/2017
 */
public class AlgorithmTest extends AbstractGridTest {
	private CoreAlgorithm coreAlgorithm = new CoreAlgorithmImpl();

	@Test
	public void testSingleIterationAlgorithm()
	{
		Grid grids[][]={
				{createGridWithLand(),createGridWithLand(),createGridWithLand()},
				{createGridWithLand(),createGridWithWater(),createGridWithLand()},
				{createGridWithLand(),createGridWithLand(),createGridWithWater()}
		};
		Grid[][] gridsWithHaloBoundary = GridUtil.generateGridWithHaloBoundary(3, 3, grids);

		int hareNum=9;
		int pumaNum=1;
		for(int i = 0; i < grids.length; i++) {
			for(int j = 0; j < grids[0].length; j++) {
				switch (grids[i][j].getTerrain()) {
					case LAND:
						initGridWithCreate(grids[i][j], Species.HARE, hareNum);
						initGridWithCreate(grids[i][j], Species.PUMA, pumaNum);
						--hareNum;
						++pumaNum;
						break;
					case WATER:
						initGridWithCreate(grids[i][j], Species.HARE, 0);
						initGridWithCreate(grids[i][j], Species.PUMA, 0);
						break;
					default:
						initGridWithCreate(grids[i][j], Species.HARE, 0);
						initGridWithCreate(grids[i][j], Species.PUMA, 0);
				}
			}
		}

		for(int i = 0; i <grids.length ; ++i) {
			for(int j = 0; j < grids[0].length ; ++j) {
				grids[i][j].setLandNeighborCnt(GridUtil.getNeighborCntWithType(
						i + 1, j + 1,
						gridsWithHaloBoundary, Terrain.LAND
				));
			}
		}
		
		double newHareDensity,newPumaDensity;
		gridsWithHaloBoundary = GridUtil.generateGridWithHaloBoundary(3, 3, grids);
		for(int i = 0; i < grids.length; ++i) {
			for(int j = 0; j < grids[0].length; ++j) {
				newHareDensity= coreAlgorithm.getHaresNum(
						gridsWithHaloBoundary[i + 1][j + 1].getCreatures().get(Species.HARE).getDensity(),
						gridsWithHaloBoundary[i + 1][j].getDensity(Species.HARE),
						gridsWithHaloBoundary[i + 1][j + 2].getDensity(Species.HARE),
						gridsWithHaloBoundary[i][j + 1].getDensity(Species.HARE),
						gridsWithHaloBoundary[i + 2][j + 1].getDensity(Species.HARE),
						Species.HARE.getBirthRate(),
						Species.PUMA.getPredationRate(),
						gridsWithHaloBoundary[i + 1][j + 1].getDensity(Species.PUMA),
						Species.HARE.getDiffusionRate(),
						0.4,
						gridsWithHaloBoundary[i + 1][j + 1].getLandNeighborCnt());
				
				newPumaDensity= coreAlgorithm.getPumaNum(
						gridsWithHaloBoundary[i + 1][j + 1].getDensity(Species.PUMA),
						gridsWithHaloBoundary[i + 1][j].getDensity(Species.PUMA),
						gridsWithHaloBoundary[i + 1][j + 2].getDensity(Species.PUMA),
						gridsWithHaloBoundary[i][j + 1].getDensity(Species.PUMA),
						gridsWithHaloBoundary[i + 2][j + 1].getDensity(Species.PUMA),
						Species.PUMA.getBirthRate(),
						gridsWithHaloBoundary[i + 1][j + 1].getDensity(Species.HARE),
						Species.PUMA.getMortalityRate(),
						Species.PUMA.getDiffusionRate(),
						0.4,
						gridsWithHaloBoundary[i + 1][j + 1].getLandNeighborCnt());

				grids[i][j].getCreatures().get(Species.HARE).updateDensity(newHareDensity);
				grids[i][j].getCreatures().get(Species.PUMA).updateDensity(newPumaDensity);
			}
		}

		Assert.assertEquals(8.824, grids[0][0].getCreatures().get(Species.HARE).getDensity(), 0);
		Assert.assertEquals(1.368, grids[0][0].getCreatures().get(Species.PUMA).getDensity(), 0);
	}

	private void initGridWithCreate(Grid grid, Species species, double density) {
		Creature creature = CreatureFactory.create(species);
		creature.updateDensity(density);
		grid.getCreatures().put(species, creature);
	}
}
