package org.epcc.ps.core.entity.environment;

import org.epcc.ps.core.entity.creature.Creature;
import org.epcc.ps.core.entity.creature.Species;

import java.util.Map;

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

    public static Grid create(Terrain terrain, Map<Species, Creature> creatures) {
        return new Grid(terrain, creatures);
    }

    public static Grid create(Terrain terrain, Map<Species, Creature> creatures, int landNeighborCnt) {
        return new Grid(terrain, creatures, landNeighborCnt);
    }
}
