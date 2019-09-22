package games.menace.items.generic;


/**
 * The Item Superclass provides the basic API for Shields, Weapons and Potions.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class Item {
    private String name;

    /**
     * The constructor for the Item BaseClass.
     * @param name The name of the item.
     */
    public Item(String name) {
        this.name = name;
    }

    /**
     * toString gives you a nice view of what an Item is.
     * @return The nice and beauty.
     */
    @Override
    public String toString() {
        return name;
    }

}
