package Menace;

import Commander.Command;
import DungeonEntity.Fighters.Player;
import DungeonEntity.Items.Potion;
import DungeonEntity.Items.Shield;
import DungeonEntity.Items.Weapon;
import DungeonEntity.Rooms.DataStructure.RoomList;
import DungeonEntity.Rooms.FourDoorRoom;
import GameInterface.Gamer;
import Generator.SnakeDungeonGenerator;
import UserInterface.UserCommandLineInterface;

import java.util.HashMap;
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
public final class MenaceGame extends Command<String, Supplier<String>> implements Gamer {

	private RoomList dungeon;
	private Player player;
	private FourDoorRoom currentRoom;

	public MenaceGame() {
		commands = new HashMap<>();
		commands.put("harakiri", this::harakiri);
		commands.put("go north", this::goNorth);
		commands.put("go south", this::goSouth);
		commands.put("go west", this::goWest);
		commands.put("go east", this::goEast);
		commands.put("pickup weapon", this::pickupWeapon);
		commands.put("pickup shield", this::pickupShield);
		commands.put("pickup potion", this::pickupPotion); // TODO: DROP ITEMS
		commands.put("stats", this::stats);
		commands.put("look", this::look);
		commands.put("attack", this::attack);
	}

	private void newGame(String playerName) {
		dungeon = new SnakeDungeonGenerator().generateSnakeDungeon();
		currentRoom = dungeon.get(0);
		player = new Player(playerName);
	}

	@Override
	public String playGame(UserCommandLineInterface userInterface, String playerName) {
		newGame(playerName);
		do {
			String command = userInterface.getInput("menace dungeon> ");
			command = runCommand(command);
			userInterface.println(command);
		} while (player.isALife());
		return "Returning to dungeon portal.";
	}

	@Override
	protected String runCommand(String command) {
		Supplier<String> func = commands.get(command);
		if (func == null)
			return "No command named: " + command;
		return func.get();
	}

	private String harakiri() {
		player.setALife(false);
		return "You have committed harakiri. You reached Room " + currentRoom.getCurrentRoomNumber();
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

	private String pickupWeapon() {
		return "pickup weapon";
	}

	private String pickupShield() {
		return "pickup shield";
	}

	private String pickupPotion() {
		return "pickup potion";
	}

}
