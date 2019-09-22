package games.menace.items;

import games.menace.items.generic.Item;


/**
 * The Potion a subclass of Item.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class Potion extends Item {

    private int lifePoints;

    /**
     * The constructor for the Potion.
     * @param name Type: String. The name of the Potion.
     * @param lifePoints Type: int. The lifePoints to gain from the potion.
     */
    public Potion(String name, int lifePoints) {
        super(name);
        this.lifePoints = lifePoints;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public String stats() {
        String buildName = super.getName() + " gives " + lifePoints + " life ";
        if (lifePoints == 1) {
            return buildName + "point";
        }
        return buildName + "points";
    }
}
