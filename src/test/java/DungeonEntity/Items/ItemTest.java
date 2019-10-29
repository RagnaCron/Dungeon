package DungeonEntity.Items;

import DungeonEntity.Items.Base.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ItemTestClass
 *
 * @author Manuel Werder
 * @version 0.1
 */
class ItemTest {
	private Item item;

	@BeforeEach
	void setUp() {
		item = new Item("Item");
	}

	@Test
	void testToString() {
		String expected = "Item";
		assertEquals(expected, item.toString());
	}
}