package org.epcc.ps.core.entity.environment;

import org.epcc.ps.core.entity.creature.Creature;
import org.epcc.ps.core.entity.creature.Species;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shaohan.yin
 * Created on 11/10/2017
 */
public class Grid {
    private Terrain terrain;
    private Map<Species, Creature> creatures;

    Grid(Terrain terrain) {
        this.terrain = terrain;
        creatures = new HashMap<>();
    }

    Grid(Terrain terrain, Map<Species, Creature> creatures) {
        this.terrain = terrain;
        this.creatures = creatures;
    }

    // TODO : algorithm implementation & grid update


    public Terrain getTerrain() {
        return terrain;
    }

    public Map<Species, Creature> getCreatures() {
        return creatures;
    }

    public void updateCreatures(Map<Species, Creature> newCreatures) {
        this.creatures = newCreatures;
    }

    @Override
    public String toString() {
        return String.format("Grid[%s]", terrain);
    }
}
