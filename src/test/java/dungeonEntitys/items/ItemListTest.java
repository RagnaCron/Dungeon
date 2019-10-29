package dungeonEntitys.items;

import dungeonEntitys.items.generic.Item;
import dungeonEntitys.items.generic.ItemList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ItemListTestClass
 *
 * @author Manuel Werder
 * @version 0.1
 */
class ItemListTest {

	private Weapon weapon;
	private Potion potion;
	private Shield shield;
	private ItemList itemList;

	@BeforeEach
	void setUp() {
		weapon = new Weapon("Sword", 10);
		potion = new Potion("Potion", 10);
		shield = new Shield("Shield", 10);
		itemList = new ItemList(new Item[]{weapon, potion, shield});
	}

	@Test
	void testToString() {
		String expected = "Items:\n" +
				"\tSword has 10 attack points\n" +
				"\tPotion has 10 life points\n" +
				"\tShield has 10 defense points\n";
		assertEquals(expected, itemList.toString());
	}

	@Test
	void removeItem() {
		Item item1 = itemList.removeItem("item");
		assertNull(item1);
		assertEquals(3, itemList.size());
		item1 = itemList.removeItem("Potion");
		assertNotNull(item1);
		assertEquals(item1.toString(), potion.toString());
		assertEquals(2, itemList.size());
	}


	@Test
	void removeWeapon() {
		Item weapon1 = itemList.removeWeapon("item");
		assertNull(weapon1);
		assertEquals(3, itemList.size());
		weapon1 = itemList.removeWeapon("Sword");
		assertNotNull(weapon1);
		assertEquals(weapon1.toString(), weapon.toString());
		assertEquals(2, itemList.size());
	}

	@Test
	void removePotion() {
		Item potion1 = itemList.removePotion("item");
		assertNull(potion1);
		assertEquals(3, itemList.size());
		potion1 = itemList.removePotion("Potion");
		assertNotNull(potion1);
		assertEquals(potion1.toString(), potion.toString());
		assertEquals(2, itemList.size());
	}

	@Test
	void removeShield() {
		Item shield1 = itemList.removeShield("item");
		assertNull(shield1);
		assertEquals(3, itemList.size());
		shield1 = itemList.removeShield("Shield");
		assertNotNull(shield1);
		assertEquals(shield1.toString(), shield.toString());
		assertEquals(2, itemList.size());
	}
}