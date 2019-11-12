package Menace;

import DungeonEntity.Dungeon.Dungeon;
import DungeonEntity.Fighters.Player;
import DungeonEntity.Items.Base.Item;
import DungeonEntity.Items.*;
import DungeonEntity.Rooms.FourDoorRoom;
import Gamer.*;
import Generator.OneWayDungeonGenerator;
import UserInterface.UserCommandLineInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * The MenaceGame class is final. It implements the common Gamer interface.
 * This method describes the sole entry point to the the game.
 * The game is an endless Dungeon Crawler Gamer. This means that the only two ways
 * to quit the game is to commit harakiri or be killed by an enemy.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public final class MenaceGame implements Gamer {

	// TODO: JAVADOC

	/**
	 * This Function gives a description about the MenaceGame class.
	 * @return Me, my self and only what a Player can do with me.
	 */
	public static String GAME_DESCRIPTION() {
		return "The Menace Dungeon.\nIt is a dungeon that only knows one way...\n" +
				"kill the enemy, collect items if any were dropped and go to the next room.\n" +
				"You will start with 100 life points. Try to survive as long as you can.\n" +
				"The Menace Dungeon knows no end...except you commit harakiri or get killed by an enemy.\n" +
				"When you start the game, you should now the commands, as there is now voice that can tell you...\n" +
				"Entering a wrong command will bring the enemy upon you...\n" +
				"Those are the available Menace Dungeon Commands:\n" +
				"'harakiri' - to commit suicide,\n" +
				"'fight' - enters the fighting action with the enemy, \n" +
				"'look' - to have a look at the room,\n" +
				"'stats' - to see how you are doing,\n" +
				"'heal' - to heal yourself,\n" +
				"'pickup item' - you can pick up items when the enemy is dead,\n"+
				"'go <direction>' - brings you to a new room";
	}

	private Dungeon<FourDoorRoom> dungeon;
	private Player player;
	private Map<String, Supplier<String>> commands;
	private UserCommandLineInterface userInterface;
	private GameState state;

	/**
	 * Gives a new instance of the MenaceGame back. It generates a new dungeon, sets the player and userInterface
	 * gives the Game an initial state of PLAYING, after thet it loads all the different Commands to the commands,
	 * HashMap that the player can use.
	 * @param playerName The cool and awesome Player name.
	 * @param userInterface The interface to communicate to.
	 */
	@SuppressWarnings("all")
	public MenaceGame(String playerName, UserCommandLineInterface userInterface) {
		dungeon = new OneWayDungeonGenerator(10).getDungeon();
		player = new Player(playerName);
		this.userInterface = userInterface;
		state = GameState.PLAYING;

		commands = new HashMap<>();
		commands.put("harakiri", this::harakiri);
		commands.put("go north", this::goNorth);
		commands.put("go south", this::goSouth);
		commands.put("go west", this::goWest);
		commands.put("go east", this::goEast);
		commands.put("pickup item", this::pickupItem);
		commands.put("heal", this::heal);
		commands.put("stats", this::stats);
		commands.put("look", this::look);
		commands.put("fight", this::fight);
	}

	/**
	 * This is the soul Method to start a game. This Games goes as long as the player is a life or
	 * he commits harakiri...
	 *
	 * @return Gives a nice message about how the game ended and returns execution back to the
	 * Controller if game is finished.
	 */
	@Override
	public Supplier<String> playGame() {
		userInterface.println(player.getName()+ " you have entered the menace dungeon, good luck...");
		while (player.isALife()) { // Sequenzdiagramm von hier an
			String command = userInterface.getInput("menace dungeon> ");
			Supplier<String> func = getCommand(command);
			switch (state) {
				case PLAYING:
					userInterface.println(func.get());
					break;
				case HARAKIRI:
					return func;
			}
		}
		return this::deathMessage;
	} // Sequenzdiagramm bis hier

	/**
	 * Gets the command out of the HashMap<String, Supplier<String>>, makes a simple check if the player
	 * committed Harakiri, if yes set game state to harakiri and return harakiri function.
	 * @param command A command of type String to search as Key in a HashMap
	 * @return Supplier that returns a String, is used for later execution.
	 */
	private Supplier<String> getCommand(String command) {
		if (command.equals("harakiri"))
			state = GameState.HARAKIRI;
		Supplier<String> com = commands.get(command);
		return com != null ? com : this::mmhNoCantDoThat;
	}

	/**
	 * Shows the player a nice little death message.
	 * @return The message includes the room wherein you have died and the enemy name.
	 */
	private String deathMessage() {
		return dungeon.getCurrentRoom().getEnemy().getName() + " has killed you. " +
				player.getName() + " you have reached room number " + dungeon.getCurrentRoom().getRoomName();
	}

	/**
	 * Tells the Player off, You can't do that.
	 * @return A nice message about what you can't do.
	 */
	private String mmhNoCantDoThat() {
		if (dungeon.getCurrentRoom().isEnemyALife()) {
			dungeon.getCurrentRoom().getEnemy().attack(player);
			return "The enemy has attacked you...";
		}
		return "No, " + player.getName() + " you can't do that...";
	}

	/**
	 * Does the magic of changing to a new room.
	 * @param hasDoor Check if the currentRoom has a door to a given direction.
	 * @param nextRoomNumber The index to the next room...
	 * @return If there is a door in the given direction and the the enemy is dead you will change the room
	 * else you will get feedback in relation to what went wrong.
	 */
	private String traverseToNextRoom(boolean hasDoor, Integer nextRoomNumber) {
		if (hasDoor) {
			if (dungeon.getCurrentRoom().isEnemyALife())
				return player.getName() + " you can't go to the next room there is an enemy blocking the door...";
			dungeon.gotoNextRoom(nextRoomNumber);
			return player.getName() + " you entered a new room...";
		}
		return player.getName() + " you are not a ghost, you can't go through walls...";
	}

	/**
	 * The player can travel north if there is door and the enemy is dead.
	 * @return if there is a door to the north go through it if the enemy is dead.
	 */
	private String goNorth() {
		return traverseToNextRoom(dungeon.getCurrentRoom().hasNorthDoor(), dungeon.getCurrentRoom().getNorthDoor());
	}

	/**
	 * The player can travel south if there is door and the enemy is dead.
	 * @return if there is a door to the south go through it if the enemy is dead.
	 */
	private String goSouth() {
		return traverseToNextRoom(dungeon.getCurrentRoom().hasSouthDoor(), dungeon.getCurrentRoom().getSouthDoor());
	}

	/**
	 * The player can travel west if there is door and the enemy is dead.
	 * @return if there is a door to the west go through it if the enemy is dead.
	 */
	private String goWest() {
		return traverseToNextRoom(dungeon.getCurrentRoom().hasWestDoor(), dungeon.getCurrentRoom().getWestDoor());
	}

	/**
	 * The player can travel east if there is door and the enemy is dead.
	 * @return if there is a door to the east go through it if the enemy is dead.
	 */
	private String goEast() {
		return traverseToNextRoom(dungeon.getCurrentRoom().hasEastDoor(), dungeon.getCurrentRoom().getEastDoor());
	}

	/**
	 * The attack method. If you defend against an enemy or the enemy attacks you is random.
	 * @return A nice Message about how the fight went.
	 */
	private String fight() {
		if (dungeon.getCurrentRoom().isEnemyALife()) {
			player.defend(dungeon.getCurrentRoom().getEnemy());
			dungeon.getCurrentRoom().getEnemy().defend(player);
			StringBuilder message = new StringBuilder();
			if (!dungeon.getCurrentRoom().isEnemyALife()) {
				dungeon.dropEnemyItems();
				return message.append(dungeon.getCurrentRoom().getEnemy().getName())
						.append(" is dead...").toString();
			}
			message.append("You have been fighting the enemy:\n")
					.append(player.toString()).append("\n")
					.append(dungeon.getCurrentRoom().getEnemy().toString());
			return message.toString();
		}
		return player.getName() + " you have already killed the enemy...";
	}

	/**
	 * Look at the room.
	 * @return Shows a nice message of the current room.
	 */
	private String look() {
		return dungeon.getCurrentRoom().toString();
	}

	/**
	 * Shows the Players stats
	 * @return A nice message of the Players stats.
	 */
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
			player.heal();
			message.append(player.getName()).append(" you have healed yourself");
		}
		else
			message.append(player.getName()).append(" you have no potion to heal yourself");
		return message.toString();
	}

	/**
	 * Commit harakiri. You will die. Sets the players isALife to false.
	 * It is one of two was to quit the game.
	 * @return A nice message about harakiri and witch room you have reached.
	 */
	private String harakiri() {
		player.setALife(false);
		return "You have committed harakiri. You reached Room " + dungeon.getCurrentRoom().getRoomName();
	}

	/**
	 * Picks up an item of the given choosing. If the player holds a potion and the player picks up a potion, the potion
	 * that is held by the player will be consumed. Shield and Weapon held by the player will be dropped and the
	 * new Weapon or shield will be picked up.
	 *
	 * @return Gives a nice message about success or failure when picking up an item form the dungeon floor.
	 */
	private String pickupItem() {
		if (dungeon.getCurrentRoom().isEnemyALife())
			return "You have to kill the enemy to collect items...";
		dungeon.getCurrentRoom().getItems().forEach((i) -> userInterface.println(i.getName()));
		String itemName = userInterface.getInput("enter item name> ");
		Item item = dungeon.getCurrentRoom().getItems().removeItem(itemName);
		if (item != null) {
			StringBuilder builder = new StringBuilder(player.getName()).append(" you have picked up ");
			if (item instanceof Weapon) {
				dungeon.getCurrentRoom().getItems().add(player.getRightHandWeapon());
				player.setRightHandWeapon((Weapon) item);
				builder.append(player.getRightHandWeapon().toString());
			} else if (item instanceof Shield) {
				dungeon.getCurrentRoom().getItems().add(player.getLeftHandShield());
				player.setLeftHandShield((Shield) item);
				builder.append(player.getLeftHandShield().toString());
			} else if (item instanceof Potion) {
				player.heal();
				player.setPotion((Potion) item);
				builder.append(player.getPotion().toString());
			}
			return builder.toString();
		}
		return "mmh there is no " + itemName + "item...";
	}

}
