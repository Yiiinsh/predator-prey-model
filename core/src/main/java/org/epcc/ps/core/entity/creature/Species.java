package org.epcc.ps.core.entity.creature;

import org.epcc.ps.core.config.CoreConfig;
import org.epcc.ps.core.config.DefaultCoreConfig;

/**
 * @author shaohan.yin
 * Created on 11/10/2017
 */
public enum  Species {
    PUMA("puma"),
    HARE("hare");

    public final static double NON_EXIST_DATA = 0.0;
    private final static String PUMA_NAME = "puma";
    private final static String HARE_NAME = "hare";

    private CoreConfig config = new DefaultCoreConfig();

    private String speciesName;
    private double birthRate;
    private double predationRate;
    private double mortalityRate;
    private double diffusionRate;

    Species(String speciesName) {
        this.speciesName = speciesName;

        switch (speciesName) {
            case PUMA_NAME :
                birthRate = config.getPumaBirthRate();
                predationRate = config.getPumaPredationRate();
                mortalityRate = config.getPumaMortalityRate();
                diffusionRate = config.getPumaDiffusionRate();
                break;
            case HARE_NAME :
                birthRate = config.getHareBirthRate();
                predationRate = NON_EXIST_DATA;
                mortalityRate = NON_EXIST_DATA;
                diffusionRate = config.getHareDiffutionRate();
                break;
            default :
                birthRate = NON_EXIST_DATA;
                predationRate = NON_EXIST_DATA;
                mortalityRate = NON_EXIST_DATA;
                diffusionRate = NON_EXIST_DATA;
        }
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public double getBirthRate() {
        return birthRate;
    }

    public double getPredationRate() {
        return predationRate;
    }

    public double getMortalityRate() {
        return mortalityRate;
    }

    public double getDiffusionRate() {
        return diffusionRate;
    }

    @Override
    public String toString() {
        return String.format("Species[%s]", speciesName);
    }
}
