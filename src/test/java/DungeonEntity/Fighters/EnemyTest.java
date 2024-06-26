package DungeonEntity.Fighters;

import DungeonEntity.Items.Potion;
import DungeonEntity.Items.Shield;
import DungeonEntity.Items.Weapon;
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
		potion = new Potion("Potion", 10);
		shield = new Shield("Shield", 10);
		player = new Player("Manuel", true, 10, weapon, shield, potion);
		enemy = new Enemy("Ork", true, 10, weapon, shield);
	}

	@Test
	void testToString() {
		String expected = "Ork has 10 life points, \nright hand: Sword, it has 10 attack points," +
				"\nleft hand: Shield, it has 10 defense points";
		assertEquals(expected, enemy.toString());
	}

	@Test
	void enemyAttack() {
		enemy.attack(player);
		assertFalse(player.isALife());
	}

	@Test
	void getRightHandWeapon() {
		assertEquals(weapon.toString(), enemy.getRightHandWeapon().toString());
	}
}