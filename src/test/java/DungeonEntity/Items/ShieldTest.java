package DungeonEntity.Items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ShieldTestClass
 *
 * @author Manuel Werder
 * @version 0.1
 */
class ShieldTest {

	private Shield shield;

	@BeforeEach
	void setUp() {
		shield = new Shield("Shield", 10);
	}

	@Test
	void getDefensePoints() {
		assertEquals(10, shield.getDefensePoints());
	}

	@Test
	void testToString() {
		String expected = "Shield has 10 defense points";
		assertEquals(expected, shield.toString());
	}
}