package Menace;

import DungeonEntity.Fighters.Player;
import DungeonEntity.Rooms.DataStructure.RoomList;
import DungeonEntity.Rooms.Directions;
import DungeonEntity.Rooms.FourDoorRoom;
import GameInterface.Gamer;
import Generator.SnakeDungeonGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * The MenaceGame class is final. It implements the common Gamer interface.
 * This method describes the sole entry point to the the game.
 * The Default constructor takes no arguments.
 * The game is an endless Dungeon Crawler Game. This means that the only two ways
 * to quit the game is to commit harakiri or be killed by an enemy.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public final class MenaceGame implements Gamer {

	private RoomList dungeon;
	private Player player;
	private FourDoorRoom currentRoom;
	private Map<String, Supplier<String>> commands;
	private Directions previousDirection = null;

	private MenaceGame(String playerName) {
		dungeon = new SnakeDungeonGenerator().generateSnakeDungeon();
		currentRoom = dungeon.get(0);
		player = new Player(playerName);

		commands = new HashMap<>();
		commands.put("harakiri", this::harakiri);
		commands.put("go north", this::goNorth);
		commands.put("go south", this::goSouth);
		commands.put("go west", this::goWest);
		commands.put("go east", this::goEast);
		commands.put("pickup weapon", this::pickupWeapon);
		commands.put("pickup shield", this::pickupShield);
		commands.put("pickup potion", this::pickupPotion);
		commands.put("heal", this::heal);
		commands.put("stats", this::stats);
		commands.put("look", this::look);
		commands.put("attack", this::attack);
	}

	// TODO: FACTORY PATTERN
	public static Gamer CreateNewMenaceGame(String playerName) {
		return new MenaceGame(playerName);
	}

	@Override
	public Supplier<String> playGame(String command) {
		Supplier<String> com = commands.get(command);
		return com != null ? com : this::mmhNoCantDoThat;
	}

	private String mmhNoCantDoThat() {
		return "No, " + player.getName() + " you can't do that...";
	}

	private void traverseToNextRoom() {

	}

	private String goNorth() {
		return "goNorth";
	}

	private String goSouth() {
		return "goSouth";
	}

	private String goWest() {
		return "goWest";
	}

	private String goEast() {
		return "goEast";
	}

	private String attack() {
		return "attacked";
	}

	private String look() {
		return currentRoom.toString();
	}

	private String stats() {
		return player.toString();
	}

	/**
	 * Heal yourself. Consumes the Potion of the player.
	 * @return Shows a nice message of how much you have healed.
	 */
	private String heal() {
		StringBuilder message =  new StringBuilder();
		if (player.hasPotion()) {
			message.append(player.getName()).append(" you have healed yourself by ")
					.append(player.getPotion().getLifePoints());
			player.heal();
			message.append("\n").append(player.getName()).append(" you have ").append(player.getLifePoints());
		}
		else
			message.append(player.getName())
					.append(" you have no potion to heal yourself");
		return message.toString();
	}

	//  TODO: MAKE SHORE THAT HARAKIRE STOPS THE GAME
	/**
	 * Commit harakiri. You will die. Sets the players isALife to false.
	 * It is one of two was to quit the game.
	 * @return A nice message about harakiri and witch room you have reached.
	 */
	private String harakiri() {
		player.setALife(false);
		return "You have committed harakiri. You reached Room " + currentRoom.getCurrentRoomNumber();
	}

	private String pickupWeapon() {
		System.out.println(currentRoom.getItems().toString());
//		Weapon weapon = currentRoom.getItems().removeWeapon("Stick");
//		currentRoom.getItems().add(player.getRightHandWeapon());
//		player.setRightHandWeapon(weapon);
//		return "picked up " + weapon.toString();
		return "picked up weapon";
	}

	private String pickupShield() {
		return "picked up shield";
	}

	private String pickupPotion() {
		return "picked up potion";
	}

}
