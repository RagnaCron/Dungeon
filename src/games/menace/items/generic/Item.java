package games.menace.items.generic;


/**
 * The Item interface provides the basic API for Shields, Weapons and Potion
 */
public class Item {
    protected String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
