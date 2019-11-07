package DungeonEntity.Items;

import DungeonEntity.Items.Base.Item;
import lombok.Getter;

/**
 * The Shield a subclass from Item.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class Shield extends Item {
    @Getter
    private int defensePoints;

    /**
     * The constructor for the Shield.
     * @param name The name of the Shield.
     * @param defensePoints The defensePoints to have when holding the shield.
     */
    public Shield(String name, int defensePoints) {
        super(name);
        this.defensePoints = defensePoints;
    }

    /**
     * This constructor is used to copy a shield.
     * @param shield The Shield to copy.
     */
    public Shield(Shield shield) {
        this(shield.getName(), shield.getDefensePoints());
    }

    /**
     * toString gives you a nice view of what a Shield is.
     * @return The nice and beauty.
     */
    @Override
    public String toString() {
        String buildName = super.toString() + ", it has " + defensePoints + " defense ";
        if (defensePoints == 1) {
           return buildName + "point";
           }
        return buildName + "points";
    }
}
