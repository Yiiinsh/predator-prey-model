package org.epcc.ps.core.entity.environment;

/**
 * @author shaohan.yin
 * Created on 11/10/2017
 */
public enum Terrain {
    WATER("water"),
    LAND("land");

    private String type;

    Terrain(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("[%s]", type);
    }
}
