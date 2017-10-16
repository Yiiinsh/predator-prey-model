package org.epcc.ps.core.entity.environment;

import org.epcc.ps.core.entity.creature.Creature;
import org.epcc.ps.core.entity.creature.Species;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shaohan.yin
 * Created on 11/10/2017
 */
public class Grid implements Serializable {
    private Terrain terrain;
    private Map<Species, Creature> creatures;
    private int landNeighborCnt;

    public Grid(Terrain terrain) {
        this.terrain = terrain;
        creatures = new HashMap<>();
        landNeighborCnt = 0;
    }

    public Grid(Terrain terrain, Map<Species, Creature> creatures) {
        this.terrain = terrain;
        this.creatures = creatures;
        landNeighborCnt = 0;
    }

    public Grid(Terrain terrain, Map<Species, Creature> creatures, int landNeighborCnt) {
        this.terrain = terrain;
        this.creatures = creatures;
        this.landNeighborCnt = landNeighborCnt;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public double getDensity(Species species) {
        if (creatures.containsKey(species)) {
            return creatures.get(species).getDensity();
        }
        return 0;
    }

    public Map<Species, Creature> getCreatures() {
        return creatures;
    }

    public void updateCreatures(Map<Species, Creature> newCreatures) {
        this.creatures = newCreatures;
    }

    public int getLandNeighborCnt() {
        return landNeighborCnt;
    }

    public void setLandNeighborCnt(int landNeighborCnt) {
        this.landNeighborCnt = landNeighborCnt;
    }

    @Override
    public String toString() {
        return String.format("Grid[%s]", terrain);
    }

}
