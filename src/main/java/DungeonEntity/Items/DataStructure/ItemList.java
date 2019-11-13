package DungeonEntity.Items.DataStructure;

import DungeonEntity.Items.Base.Item;
import DungeonEntity.Items.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ItemList extends the ArrayList. It provides some convenient Methods to get Items per name out.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class ItemList extends ArrayList<Item> {

	/**
	 * Init a new ItemList with an existing one.
	 * @param items The existing ItemList.
	 */
	public ItemList(ItemList items) {
		super(items);
	}

	/**
	 * Init a new ItemList out of a static Item array.
	 *
	 * @param items The static Item Array.
	 */
	public ItemList(Item[] items) {
		super(Arrays.asList(items));
	}

	/**
	 * Init a new ItemList from a single Item.
	 * @param item The Item to use.
	 */
	public ItemList(Item item) {
		super();
		this.add(item);
	}

	/**
	 * Init empty ItemList.
	 */
	public ItemList() {
		super();
	}

	/**
	 * Gives a nice representation of the content of an ItemList.
	 *
	 * @return The beauty and the nice.
	 */
	@Override
	public String toString() {
		StringBuilder buildItemList = new StringBuilder();
		buildItemList.append("Items:\n");
		for (Item item : this) {
			buildItemList.append("\t").append(item.toString()).append("\n");
		}
		return buildItemList.toString();
	}

	/**
	 * The removeItem method will remove and return the Item with the search name, if the
	 * search name is not found it will return null.
	 *
	 * @param name The name of the Item (Weapon, Shield or Potions Name).
	 * @return If the item is not found it will return null.
	 */
	public Item removeItem(String name) {
		for (int i = 0; i < size(); i++) {
			Item item = get(i);
			if (item.getName().equals(name)) {
				return remove(i);
			}
		}
		return null;
	}

	/**
	 * The removeWeapon method will remove and return the Weapon with the search name, if the
	 * search name is not found it will return null.
	 *
	 * @param name The name of the Weapon to search for.
	 * @return If the Weapon is not found it will return null.
	 */
	public Weapon removeWeapon(String name) {
		Item item = removeItem(name);
		if (item == null) return null;
		try {
			Weapon weapon = (Weapon) item;
			return weapon;
		} catch (ClassCastException e) {
			// we dont care so we return null
		}
		return null;
	}

	/**
	 * The removePotion method will remove and return the Potion with the search name, if the
	 * search name is not found it will return null.
	 *
	 * @param name The name of the Potion to search for.
	 * @return If the Potion is not found it will return null.
	 */
	public Potion removePotion(String name) {
		Item item = removeItem(name);
		if (item == null) return null;
		try {
			Potion potion = (Potion) item;
			return potion;
		} catch (ClassCastException e) {
			// we dont care so we return null
		}
		return null;
	}

	/**
	 * The removeShield method will remove and return the Shield with the search name, if the
	 * search name is not found it will return null.
	 *
	 * @param name The name of the Shield to search for.
	 * @return If the Shield is not found it will return null.
	 */
	public Shield removeShield(String name) {
		Item item = removeItem(name);
		if (item == null) return null;
		try {
			Shield shield = (Shield) item;
			return shield;
		} catch (ClassCastException e) {
			// we dont care so we return null
		}
		return null;
	}

}
