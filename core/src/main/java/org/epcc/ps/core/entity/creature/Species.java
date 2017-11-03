/*
 * Copyright (C) 2017 the predator-prey-model authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.epcc.ps.core.entity.creature;

import org.epcc.ps.core.config.CoreConfig;

/**
 * <p>Enumeration of species on landscape.</p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 11/10/2017
 */
public enum Species {
    /**
     * Species puma
     */
    PUMA("puma"),
    /**
     * Species hare
     */
    HARE("hare");

    public final static double NON_EXIST_DATA = 0.0;
    private final static String PUMA_NAME = "puma";
    private final static String HARE_NAME = "hare";

    private String speciesName;
    private double birthRate;
    private double predationRate;
    private double mortalityRate;
    private double diffusionRate;

    Species(String speciesName) {
        CoreConfig config = CoreConfig.DEFAULT;
        this.speciesName = speciesName;
        switch (speciesName) {
            case PUMA_NAME:
                birthRate = config.getPumaBirthRate();
                predationRate = config.getPumaPredationRate();
                mortalityRate = config.getPumaMortalityRate();
                diffusionRate = config.getPumaDiffusionRate();
                break;
            case HARE_NAME:
                birthRate = config.getHareBirthRate();
                predationRate = NON_EXIST_DATA;
                mortalityRate = NON_EXIST_DATA;
                diffusionRate = config.getHareDiffusionRate();
                break;
            default:
                birthRate = NON_EXIST_DATA;
                predationRate = NON_EXIST_DATA;
                mortalityRate = NON_EXIST_DATA;
                diffusionRate = NON_EXIST_DATA;
            }
    }

    /**
     * @return name of this species
     */
    public String getSpeciesName() {
        return speciesName;
    }

    /**
     * @return birth rate of this species
     */
    public double getBirthRate() {
        return birthRate;
    }

    /**
     * @return predation rate of this species
     */
    public double getPredationRate() {
        return predationRate;
    }

    /**
     * @return mortality rate of this species
     */
    public double getMortalityRate() {
        return mortalityRate;
    }

    /**
     * @return diffusion rate of this species
     */
    public double getDiffusionRate() {
        return diffusionRate;
    }

    @Override
    public String toString() {
        return String.format("Species[%s]", speciesName);
    }
}
