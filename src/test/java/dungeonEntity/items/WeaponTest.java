package dungeonEntity.items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * WeaponTestClass
 *
 * @author Manuel Werder
 * @version 0.1
 */
class WeaponTest {

	private Weapon weapon;

	@BeforeEach
	void setUp() {
		weapon = new Weapon("Sword", 10);
	}

	@Test
	void getAttackPoints() {
		assertEquals(10, weapon.getAttackPoints());
	}

	@Test
	void testToString() {
		String expected = "Sword has 10 attack points";
		assertEquals(expected, weapon.toString());
	}
}