package Menace;

import Controller.Model.ControllerState;
import DungeonEntity.Fighters.Player;
import DungeonEntity.Rooms.DataStructure.RoomList;
import DungeonEntity.Rooms.FourDoorRoom;
import GameInterface.Gamer;
import Generator.SnakeDungeonGenerator;
import javafx.util.Pair;

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

	// TODO: JAVADOC

	private RoomList dungeon;
	private Player player;
	private FourDoorRoom currentRoom;
	private Map<String, Pair<ControllerState, Supplier<String>>> commands;

	private MenaceGame(String playerName) {
		dungeon = new SnakeDungeonGenerator().generateSnakeDungeon();
		currentRoom = dungeon.get(0);
		player = new Player(playerName);

		commands = new HashMap<>();
		commands.put("harakiri", new Pair<>(ControllerState.CHOOSING_STATE,this::harakiri));
		commands.put("go north", new Pair<>(ControllerState.GAMING_STATE, this::goNorth));
		commands.put("go south", new Pair<>(ControllerState.GAMING_STATE, this::goSouth));
		commands.put("go west", new Pair<>(ControllerState.GAMING_STATE, this::goWest));
		commands.put("go east", new Pair<>(ControllerState.GAMING_STATE, this::goEast));
		commands.put("pickup weapon", new Pair<>(ControllerState.GAMING_STATE, this::pickupWeapon));
		commands.put("pickup shield", new Pair<>(ControllerState.GAMING_STATE, this::pickupShield));
		commands.put("pickup potion", new Pair<>(ControllerState.GAMING_STATE, this::pickupPotion));
		commands.put("heal", new Pair<>(ControllerState.GAMING_STATE, this::heal));
		commands.put("stats", new Pair<>(ControllerState.GAMING_STATE, this::stats));
		commands.put("look", new Pair<>(ControllerState.GAMING_STATE, this::look));
		commands.put("attack", new Pair<>(ControllerState.GAMING_STATE, this::attack));
	}

	// TODO: FACTORY PATTERN DOCUMENTATION
	public static Gamer CreateNewMenaceGame(String playerName) {
		return new MenaceGame(playerName);
	}

	/**
	 * This is the soul Method to get a game command.
	 * @param command The command to find.
	 * @return Returns a controller state and a Method to be run be the Command Pattern.
	 */
	@Override
	public Pair<ControllerState, Supplier<String>> playGame(String command) {
		Pair<ControllerState, Supplier<String>> com = commands.get(command);
		return com != null ? com : new Pair<>(ControllerState.GAMING_STATE, this::mmhNoCantDoThat);
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

	private String attack() {
		return "attacked";
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

	//  TODO: MAKE SURE THAT HARAKIRE STOPS THE GAME, IMPLEMENT TRUE COMMAND PATTERN FOR IT, CHANGE DATA STRUCTURE
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
//		System.out.println(currentRoom.getItems().toString());
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
