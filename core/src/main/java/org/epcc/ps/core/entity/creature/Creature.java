package org.epcc.ps.core.entity.creature;

import org.epcc.ps.core.entity.environment.Terrain;

/**
 * @author shaohan.yin
 * Created on 11/10/2017
 */
public interface Creature {
    String getName();
    Species getSpecies();
    boolean isHabitable(Terrain terrain);

    /** Rates **/
    double getBirthRate();
    double getMortalityRate();
    double getPredationRate();
    double getDiffusionRate();

    /** Statistics **/
    double getDensity();
    void updateDensity(double newDensity);
}
