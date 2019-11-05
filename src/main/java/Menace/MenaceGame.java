package Menace;

import Controller.Model.ControllerState;
import DungeonEntity.Fighters.Player;
import DungeonEntity.Items.Weapon;
import DungeonEntity.Rooms.DataStructure.RoomList;
import DungeonEntity.Rooms.FourDoorRoom;
import GameInterface.Gamer;
import Generator.SnakeDungeonGenerator;
import UserInterface.UserCommandLineInterface;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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

	// TODO: JAVADOC

	private RoomList dungeon;
	private Player player;
	private FourDoorRoom currentRoom;
	private Map<String, Supplier<String>> commands;
	private static Random rand = new Random();
	private UserCommandLineInterface userInterface;
	private MenaceGameState state;

	public MenaceGame(String playerName, UserCommandLineInterface userInterface) {
		dungeon = new SnakeDungeonGenerator().generateSnakeDungeon();
		currentRoom = dungeon.get(0);
		player = new Player(playerName);
		this.userInterface = userInterface;
		state = MenaceGameState.PLAYING;

		// TODO: REFACTOR TO USE GAME LOOP AND PASS IN USER INTERFACE
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

//	// TODO: FACTORY PATTERN DOCUMENTATION
//	public static Gamer CreateNewMenaceGame(String playerName, UserCommandLineInterface userInterface) {
//		return new MenaceGame(playerName, userInterface);
//	}

	/**
	 * This is the soul Method to start a game.
	 * @return Gives a nice message about how the game ended.
	 */
	@Override
	public Supplier<String> playGame() {
		while (player.isALife()) {
			Supplier<String> func = getCommand(userInterface.getInput("menace dungeon> "));
			switch (state) {
				case PLAYING:
					userInterface.println(func.get());
					break;
				case HARAKIRI:
					return func;
			}
		}
		return this::deathMessage;
	}

	private Supplier<String> getCommand(String command) {
		if (command.equals("harakiri"))
			state = MenaceGameState.HARAKIRI;
		Supplier<String> com = commands.get(command);
		return com != null ? com : this::mmhNoCantDoThat;
	}

	/**
	 * Shows the player a nice little death message.
	 * @return The message includes the room wherein you have died and the enemy name.
	 */
	private String deathMessage() {
		return currentRoom.getEnemy().getName() + " has killed you. " +
				player.getName() + " you have reached room number " + currentRoom.getCurrentRoomNumber();
	}

	/**
	 * Tells the Player off, You can't do that.
	 * @return A nice message about what you can't do.
	 */
	private String mmhNoCantDoThat() {
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
			if (currentRoom.hasEnemy())
				return player.getName() + " you can't leave the room there is an enemy...";
			currentRoom = dungeon.get(nextRoomNumber);
			return player.getName() + " you entered a new room...";
		}
		return player.getName() + " you are not a ghost, you can't go through walls...";
	}

	/**
	 * The player can travel north if there is door and the enemy is dead.
	 * @return if there is a door to the north go through it if the enemy is dead.
	 */
	private String goNorth() {
		return traverseToNextRoom(currentRoom.hasNorthDoor(), currentRoom.getNorthDoor());
	}

	/**
	 * The player can travel south if there is door and the enemy is dead.
	 * @return if there is a door to the south go through it if the enemy is dead.
	 */
	private String goSouth() {
		return traverseToNextRoom(currentRoom.hasSouthDoor(), currentRoom.getSouthDoor());
	}

	/**
	 * The player can travel west if there is door and the enemy is dead.
	 * @return if there is a door to the west go through it if the enemy is dead.
	 */
	private String goWest() {
		return traverseToNextRoom(currentRoom.hasWestDoor(), currentRoom.getWestDoor());
	}

	/**
	 * The player can travel east if there is door and the enemy is dead.
	 * @return if there is a door to the east go through it if the enemy is dead.
	 */
	private String goEast() {
		return traverseToNextRoom(currentRoom.hasEastDoor(), currentRoom.getEastDoor());
	}

	/**
	 * The attack method. If you defend against an enemy or the enemy attacks you is random.
	 * @return A nice Message about how the fight went.
	 */
	private String attack() {
		if (currentRoom.hasEnemy()) {
			player.attack(currentRoom.getEnemy());
			StringBuilder message = new StringBuilder(currentRoom.getEnemy().getName());
			boolean enemyWillAttack = rand.nextBoolean();
			boolean playerWillDefend = rand.nextBoolean();
			if (!currentRoom.getEnemy().isALife()) {
				currentRoom.getItems().add(currentRoom.getEnemy().getRightHandWeapon());
				currentRoom.setEnemy(null);
				return message.append(" is dead...").toString();
			}
			else if (enemyWillAttack) {
				if (playerWillDefend) {
					player.defend(currentRoom.getEnemy());
					return player.getName() + " you have defended against the enemy...";
				} else {
					currentRoom.getEnemy().attack(player);
					return message.append(" has attacked you...").toString();
				}
			}
			return player.getName() + " you have attacked the enemy...";
		}
		return player.getName() + " you have already killed the enemy...";
	}

	/**
	 * Look at the room.
	 * @return Shows a nice message of the current room.
	 */
	private String look() {
		return currentRoom.toString();
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
		return "You have committed harakiri. You reached Room " + currentRoom.getCurrentRoomNumber();
	}

	private String pickupWeapon() {
		return "picked up weapon";
	}

	private String pickupShield() {
		return "picked up shield";
	}

	private String pickupPotion() {
		return "picked up potion";
	}

}
