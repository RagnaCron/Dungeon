package games.menace.items;

import games.menace.items.generic.Item;

/**
 * The Shield a subclass from Item.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class Shield extends Item {
    private int defensePoints;

    /**
     * The constructor for the Shield.
     * @param name Type: String. The name of the Shield.
     * @param defensePoints Type: int. The defensePoints to have when holding the shield.
     */
    public Shield(String name, int defensePoints) {
        super(name);
        this.defensePoints = defensePoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    @Override
    public String getName() {
        String buildName = super.getName() + " has " + defensePoints + " defense ";
        if (defensePoints == 1) {
           return buildName + "point.";
           }
        return buildName + "points.";
    }
}
