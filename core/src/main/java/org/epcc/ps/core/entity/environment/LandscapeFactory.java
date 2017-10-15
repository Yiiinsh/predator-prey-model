package org.epcc.ps.core.entity.environment;

/**
 * @author shaohan.yin
 * Created on 12/10/2017
 */
public class LandscapeFactory {
    private LandscapeFactory() {
    }

    public static Landscape create(int length, int width, Grid grids[][]) {
        return new Landscape(length, width, grids);
    }
}
