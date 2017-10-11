package org.epcc.ps.core.entity.creature;

import org.epcc.ps.core.entity.environment.Terrain;

/**
 * @author shaohan.yin
 * Created on 12/10/2017
 */
public class Hare extends AbstractCreature {
    Hare() {
        super(Species.HARE);
    }

    @Override
    public boolean isHabitable(Terrain terrain) {
        return terrain.equals(Terrain.LAND) ? true : false ;
    }
}
