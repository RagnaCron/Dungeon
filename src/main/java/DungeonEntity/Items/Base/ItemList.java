package DungeonEntity.Items.Base;

import DungeonEntity.Items.Potion;
import DungeonEntity.Items.Shield;
import DungeonEntity.Items.Weapon;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemList extends ArrayList<Item> {

	public ItemList(ItemList items) {
		super(items);
	}

	public ItemList(Item[] items) {
		super(Arrays.asList(items));
	}

	public ItemList(int amount) {
		super(Arrays.asList(new Item[amount]));
	}

	public ItemList(Item item) {
		super();
		this.add(item);
	}

	public ItemList() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder buildItemList = new StringBuilder();
		buildItemList.append("Items:\n");
		for (Item item : this) {
			buildItemList.append("\t").append(item.toString()).append("\n");
		}
		return new String(buildItemList);
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
		try {
			Shield shield = (Shield) item;
			return shield;
		} catch (ClassCastException e) {
			// we dont care so we return null
		}
		return null;
	}

}
