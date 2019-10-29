package items;

import games.menace.items.Potion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * PotionTestClass
 *
 * @author Manuel Werder
 * @version 0.1
 */
class PotionTest {

	private Potion potion;

	@BeforeEach
	void setUp() {
		potion = new Potion("Potion", 10);
	}

	@Test
	void getLifePoints() {
		assertEquals(10, potion.getLifePoints());
		assertNotEquals(11, potion.getLifePoints());
	}

	@Test
	void testToString() {
		String expected = "Potion has 10 life points";
		assertEquals(expected, potion.toString());
	}
}