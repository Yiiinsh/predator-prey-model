package org.epcc.ps.core.entity.environment;

/**
 * @author shaohan.yin
 * Created on 11/10/2017
 */
public class Landscape {
    private int length;
    private int width;
    private Grid grids[][];

    Landscape(int length, int width) {
        this.length = length;
        this.width = width;
        grids = new Grid[length][width];
    }

    @Override
    public String toString() {
        return String.format("%d * %d Landscape", length, width);
    }
}
