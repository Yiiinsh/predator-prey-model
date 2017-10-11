package org.epcc.ps.core.config;

/**
 * @author shaohan.yin
 * Created on 10/10/2017
 */
public interface CoreConfig {
    /** PUMA Config **/
    double getPumaBirthRate();
    double getPumaPredationRate();
    double getPumaMortalityRate();
    double getPumaDiffusionRate();

    /** HARE Config **/
    double getHareBirthRate();
    double getHareDiffutionRate();
}
