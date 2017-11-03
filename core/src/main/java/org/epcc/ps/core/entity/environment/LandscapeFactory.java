package org.epcc.ps.core.entity.environment;

/**
 * <p>Factory methods for {@link Landscape}</p>
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 12/10/2017
 */
public class LandscapeFactory {
    private LandscapeFactory() {
    }

    public static Landscape create(int length, int width, Grid[][] grids) {
        return new Landscape(length, width, grids);
    }
}
