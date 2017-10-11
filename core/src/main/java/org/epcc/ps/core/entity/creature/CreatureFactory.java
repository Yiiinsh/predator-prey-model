package org.epcc.ps.core.entity.creature;

/**
 * @author shaohan.yin
 * Created on 12/10/2017
 */
public class CreatureFactory {
    private static CreatureFactory instance = new CreatureFactory();

    private CreatureFactory() {
    }

    public static CreatureFactory getInstance() {
        return instance;
    }

    public Creature create(Species species) {
        Creature creature;
        switch (species) {
            case PUMA:
                creature = new Puma();
                break;
            case HARE:
                creature = new Hare();
                break;
            default:
                creature = null;
        }
        return creature;
    }
}
