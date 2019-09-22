package games.menace.items;

import games.menace.items.generic.Item;

/**
 * The Weapon a subclass of Item.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class Weapon extends Item {
    private int attackPoints;

    /**
     * The constructor for the Weapon.
     * @param name Type: String. The name of the amazing weapon.
     * @param attackPoints Type: int. Some nice attackPoints for the weapon.
     */
    public Weapon(String name, int attackPoints) {
        super(name);
        this.attackPoints = attackPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    @Override
    public String getName() {
        String buildName = super.getName() + " has " + attackPoints + " attack ";
        if (attackPoints == 1) {
            return buildName + "point.";
        }
        return buildName + "points.";
    }
}
