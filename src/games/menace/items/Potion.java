package games.menace.items;

import games.menace.items.generic.Item;


/**
 * Potion Subclasses Item. It adds a private field lifePoints and a get method to access lifePoints.
 *
 */
public class Potion extends Item {

    private int lifePoints;

    public Potion(String name, int lifePoints) {
        super(name);
        this.lifePoints = lifePoints;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    @Override
    public String getName() {
        String buildName = super.getName() + " gives " + lifePoints;
        if (lifePoints == 1) {
            buildName += " life point.";
        }
        return buildName + " life points.";
    }
}
