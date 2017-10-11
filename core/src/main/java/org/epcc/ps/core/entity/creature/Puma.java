package org.epcc.ps.core.entity.creature;

import org.epcc.ps.core.entity.environment.Terrain;

/**
 * @author shaohan.yin
 * Created on 11/10/2017
 */
public class Puma extends AbstractCreature {
    Puma() {
        super(Species.PUMA);
    }

    @Override
    public boolean isHabitable(Terrain terrain) {
        return terrain.equals(Terrain.LAND) ? true : false;
    }
}
