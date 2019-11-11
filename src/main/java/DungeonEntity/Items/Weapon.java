package DungeonEntity.Items;

import DungeonEntity.Items.Base.Item;
import lombok.Getter;

/**
 * The Weapon a subclass of Item.
 *
 * @author Manuel Werder
 * @version 0.1
 */
@Getter
public class Weapon extends Item {
    private int attackPoints;

    /**
     * The constructor for the Weapon. Calls super() first and then sets attackPoints.
     * @param name The name of the amazing weapon.
     * @param attackPoints Some nice attackPoints for the weapon.
     */
    public Weapon(String name, int attackPoints) {
        super(name);
        this.attackPoints = attackPoints;
    }

    /**
     * This constructor is used to copy a weapon.
     * @param weapon The Weapon to copy.
     */
    public Weapon(Weapon weapon) {
        this(weapon.getName(), weapon.getAttackPoints());
    }

    /**
     * toString gives you a nice view of what a Weapon is.
     * @return The nice and beauty.
     */
    @Override
    public String toString() {
        String buildName = super.toString() + ", it has " + attackPoints + " attack ";
        if (attackPoints == 1) {
            return buildName + "point";
        }
        return buildName + "points";
    }
}
