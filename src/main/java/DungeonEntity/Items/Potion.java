package DungeonEntity.Items;

import DungeonEntity.Items.Base.Item;
import lombok.Getter;


/**
 * The Potion a subclass of Item.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class Potion extends Item {

    @Getter
    private int lifePoints;

    /**
     * The constructor for the Potion.
     * @param name The name of the Potion.
     * @param lifePoints The lifePoints to gain from the potion.
     */
    public Potion(String name, int lifePoints) {
        super(name);
        this.lifePoints = lifePoints;
    }

    /**
     * This constructor is used to copy a potion.
     * @param potion The Potion to copy.
     */
    public Potion(Potion potion) {
        this(potion.getName(), potion.getLifePoints());
    }

    /**
     * toString gives you a nice view of what a Potion is.
     * @return The nice and beauty.
     */
    @Override
    public String toString() {
        String buildName = super.toString() + " it has " + lifePoints + " life ";
        if (lifePoints == 1) {
            return buildName + "point";
        }
        return buildName + "points";
    }
}
