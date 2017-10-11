package org.epcc.ps.core.entity.creature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shaohan.yin
 * Created on 11/10/2017
 */
public abstract class AbstractCreature implements Creature {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected Species species;
    protected double density;

    AbstractCreature(Species species) {
        this.species = species;
        this.density = 0.0;
    }

    @Override
    public String getName() {
        return species.getSpeciesName();
    }

    @Override
    public Species getSpecies() {
        return species;
    }

    @Override
    public double getBirthRate() {
        return species.getBirthRate();
    }

    @Override
    public double getMortalityRate() {
        return species.getMortalityRate();
    }

    @Override
    public double getPredationRate() {
        return species.getPredationRate();
    }

    @Override
    public double getDiffusionRate() {
        return species.getDiffusionRate();
    }

    @Override
    public double getDensity() {
        return density;
    }

    @Override
    public void updateDensity(double newDensity) {
        this.density = newDensity;
    }

    @Override
    public String toString() {
        return String.format("[%s]", species.getSpeciesName());
    }
}
