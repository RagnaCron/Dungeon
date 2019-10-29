package fighters;

import games.menace.fighters.Enemy;
import games.menace.fighters.Player;
import games.menace.items.Potion;
import games.menace.items.Shield;
import games.menace.items.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


/**
 * EnemyTestClass
 *
 * @author Manuel Werder
 * @version 0.1
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EnemyTest {

	private Weapon weapon;
	private Enemy enemy;
	private Player player;
	private Potion potion;
	private Shield shield;

	@BeforeEach
	void setUp() {
		weapon = new Weapon("Sword", 10);
		enemy = new Enemy("Ork", true, 10, weapon);
		potion = new Potion("Potion", 10);
		shield = new Shield("Shield", 10);
		player = new Player("Manuel", true, 10, weapon, shield, potion);
	}

	@Test
	void testToString() {
		String expected = "Ork has 10 life points, \nright hand: Sword has 10 attack points";
		assertEquals(expected, enemy.toString());
	}

	@Test
	void attack() {
		enemy.attack(player);
		assertFalse(player.isALife());
	}

	@Test
	void getRightHandWeapon() {
		assertEquals(weapon.toString(), enemy.getRightHandWeapon().toString());
	}
}