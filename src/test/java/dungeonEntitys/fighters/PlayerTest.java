package dungeonEntitys.fighters;

import dungeonEntitys.items.Potion;
import dungeonEntitys.items.Shield;
import dungeonEntitys.items.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * PlayerTestClass
 *
 * @author Manuel Werder
 * @version 0.1
 */
class PlayerTest {

	private Weapon weapon;
	private Weapon weapon1;
	private Enemy enemy;
	private Enemy enemy1;
	private Player player;
	private Potion potion;
	private Potion potion1;
	private Shield shield;

	@BeforeEach
	void setUp() {
		weapon = new Weapon("Sword", 10);
		weapon1 = new Weapon("Long Sword", 20);
		enemy = new Enemy("Ork", true, 10, weapon);
		enemy1 = new Enemy("Big Ork", true, 10, weapon1);
		potion = new Potion("Potion", 10);
		potion1 = new Potion("Big Potion", 20);
		shield = new Shield("Shield", 10);
		player = new Player("Manuel", true, 10, weapon, shield, potion);
	}

	@Test
	void testToString() {
		String expected = "Manuel has 10 life points, " +
				"\nPotion has 10 life points, " +
				"\nright hand: Sword has 10 attack points, " +
				"\nleft hand: Shield has 10 defense points";
		assertEquals(expected, player.toString());
	}

	@Test
	void attack() {
		player.attack(enemy);
		assertFalse(enemy.isALife());
	}

	@Test
	void defend() {
		player.defend(enemy);
		assertEquals(10, player.getLifePoints());
		player.defend(enemy1);
		assertFalse(player.isALife());
	}

	@Test
	void heal() {
		assertEquals(10, player.getLifePoints());
		player.heal();
		assertEquals(20, player.getLifePoints());
		player.heal();
		assertEquals(20, player.getLifePoints());
	}

	@Test
	void setPotion() {
		String expected = "Potion has 10 life points";
		String expected1 = "Big Potion has 20 life points";
		assertEquals(expected, potion.toString());
		assertEquals(expected1, potion1.toString());
		player.heal();
		player.setPotion(potion1);
		player.heal();
		assertEquals(40, player.getLifePoints());
	}
}