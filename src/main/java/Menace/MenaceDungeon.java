package Menace;

import DungeonEntity.Dungeon.Dungeon;
import DungeonEntity.Fighters.Enemy;
import DungeonEntity.Items.DataStructure.ItemList;
import DungeonEntity.Items.*;
import DungeonEntity.Rooms.DataStructure.RoomList;
import DungeonEntity.Rooms.FourDoorRoom;

import java.util.Random;

/**
 * This is the Dungeon for the Player to explore. It extends the abstract generic Dungeon class with the
 * Type FourDoorRoom. It provides the functionality to spawn enemies, drop Items of the enemy and
 * reset the doors so it looks like a different Dungeon. The MenaceDungeon only knows one direction.
 * NOTE: This Dungeon assumes that the RoomList contains 10 Rooms. This is so, that the Player
 * has the illusion of a never ending Dungeon as the internal Room number is incremented to 11, 12 and so on
 * but there will be never more then 10 Rooms.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class MenaceDungeon extends Dungeon<FourDoorRoom> {

	private Random rand = new Random();

	private String[] potionNames = {"Water", "Small Potion", "Potion", "Big Potion",};
	private String[] shieldsNames = {"Broken Shield", "Shield", "Iron Shield",};
	private String[] weaponNames = { "Stick", "Golden Sword", "Katana", "Broken Sword", "Sword", "Iron Sword",};
	private String[] enemyNames = {"Skeleton", "Ork", "Dragon", "Ghoul", "Zombie", "Dark Elb", "Goblin", "Slime",
			"Mummy", "Your Mom", "Your non existing Girlfriend", "Dark Magician"
	};

	/**
	 * This inits the MenaceDungeon, it first calls super() on the rooms and then
	 * spawnEnemies is called.
	 * @param rooms This should be a RoomList of Type FourDoorRoom and preferably 10 Rooms.
	 */
	public MenaceDungeon(RoomList<FourDoorRoom> rooms) {
		super(rooms);
		spawnEnemies();
	}

	/**
	 * This Method will bring you to the next room. Its sets the currentRoom: FourDoorRoom.
	 *
	 * @param nextRoom An index that will set the current room out of the rooms list.
	 */
	@Override
	public void gotoNextRoom(int nextRoom) {
		if (nextRoom == 0) reSpawnEnemies();
		currentRoom = rooms.get(nextRoom);
	}

	/**
	 * Spawn new enemies in the rooms List. Call this Method after you have
	 * created a dungeon to Spawn Enemies in the dungeon.
	 */
	@Override
	protected void spawnEnemies() {
		for (int i = 0; i < rooms.size(); i++) {
			FourDoorRoom room = rooms.get(i);
			room.setItems(new ItemList());
			room.setCurrentRoomNumber(i + 1);
			if (i % 4 == 0 || i % 10 == 0) {
				room.getItems().add(createPotion(room.getCurrentRoomNumber()));
			}
			spawnEnemy(room, room.getCurrentRoomNumber());
		}
	}

	/**
	 * ReSpawn dead enemies and Items in all the rooms in the RoomList.
	 */
	@Override
	protected void reSpawnEnemies() {
		for (int i = 0; i < rooms.size(); i++) {
			FourDoorRoom room = rooms.get(i);
			resetDoorDirection(room, rand.nextInt(4));
			room.setItems(new ItemList());
			room.setCurrentRoomNumber(room.getCurrentRoomNumber() + 10);
			room.setRoomName("Room " + room.getCurrentRoomNumber());
			if (i % 4 == 0 || i % 10 == 0) {
				room.getItems().add(createPotion(room.getCurrentRoomNumber()));
			}
			else
			spawnEnemy(room, room.getCurrentRoomNumber());
		}
	}

	/**
	 * With this Method we reset the door direction is done. The previous direction is set to null,
	 * and the new direction is set. The door of a room contains an Integer value that is the index to
	 * the next room.
	 *
	 * @param room The room to reset the single door direction.
	 * @param direction The new direction to be set form 0 (north), 1 (south), 2 (west) or 3 (east).
	 */
	private void resetDoorDirection(FourDoorRoom room, int direction) {
		if (room.hasNorthDoor()) {
			Integer nextRoomNumber = room.getNorthDoor();
			room.setNorthDoor(null);
			setNewDirection(nextRoomNumber, room, direction);
		}
		else if (room.hasSouthDoor()) {
			Integer nextRoomNumber = room.getSouthDoor();
			room.setSouthDoor(null);
			setNewDirection(nextRoomNumber, room, direction);
		}
		else if (room.hasWestDoor()) {
			Integer nextRoomNumber = room.getWestDoor();
			room.setWestDoor(null);
			setNewDirection(nextRoomNumber, room, direction);
		}
		else if (room.hasEastDoor()) {
			Integer nextRoomNumber = room.getEastDoor();
			room.setEastDoor(null);
			setNewDirection(nextRoomNumber, room, direction);
		}
	}

	/**
	 * This Method does the job of an actual door reset.
	 * @param nextRoomNumber The index to the next room.
	 * @param room The current room to have its door reset.
	 * @param direction The new direction to be set form 0 (north), 1 (south), 2 (west) or 3 (east).
	 */
	private void setNewDirection(Integer nextRoomNumber, FourDoorRoom room, int direction) {
		if (direction == 0) room.setNorthDoor(nextRoomNumber);
		else if (direction == 1) room.setSouthDoor(nextRoomNumber);
		else if (direction == 2) room.setWestDoor(nextRoomNumber);
		else if (direction == 3) room.setEastDoor(nextRoomNumber);
	}

	/**
	 * This Method drops the enemy Items if certain conditions are met.
	 */
	@Override
	public void dropEnemyItems() {
		if (currentRoom.getCurrentRoomNumber() % 10 == 0) {
			currentRoom.getItems().add(currentRoom.getEnemy().getRightHandWeapon());
			currentRoom.getItems().add(currentRoom.getEnemy().getLeftHandShield());
			currentRoom.getItems().add(createPotion(currentRoom.getCurrentRoomNumber()));
		}
		else if (currentRoom.getCurrentRoomNumber() % 4 == 0) {
			currentRoom.getItems().add(currentRoom.getEnemy().getRightHandWeapon());
		}
		else if (currentRoom.getCurrentRoomNumber() % 3 == 0) {
			currentRoom.getItems().add(currentRoom.getEnemy().getLeftHandShield());
			currentRoom.getItems().add(createPotion(currentRoom.getCurrentRoomNumber()));
		}
	}

	/**
	 * Spawns a new Enemy in the current room.
	 * @param room The current room to get a new Enemy.
	 * @param roomNumber This is used to calculate the Enemy specs.
	 */
	private void spawnEnemy(FourDoorRoom room, int roomNumber) {
		room.setEnemy(createEnemy(roomNumber));
	}

	/**
	 * This Method creates a new Potion with a new name from the static potionsNames array.
	 * @param roomNumber This is used to calculate the Potion lifePoints.
	 * @return The Power to survive.
	 */
	private Potion createPotion(int roomNumber) {
		return new Potion(potionNames[rand.nextInt(potionNames.length)],
				(roomNumber + 2) * 2 + 1);
	}

	/**
	 * This Method creates a new Weapon with a new name from the static weaponNames array.
	 * @param roomNumber This is used to calculate the Weapon attackPoints.
	 * @return The Power to kill.
	 */
	private Weapon createWeapon(int roomNumber) {
		return new Weapon(weaponNames[rand.nextInt(weaponNames.length)],
				(roomNumber + 3) * 2 - 2);
	}

	/**
	 * This Method creates a new Shield with a new name from the static shieldNames array.
	 * @param roomNumber This is used to calculate the Shield defensePoints.
	 * @return The Power to defend.
	 */
	private Shield createShield(int roomNumber) {
		return new Shield(shieldsNames[rand.nextInt(shieldsNames.length)],
				(roomNumber + 3) * 2 - 3);
	}


	/**
	 * This Method creates a new Enemy with a new name from the static enemyNames array.
	 * @param roomNumber This is used to calculate the Enemy lifePoints, the Shields defensePoints and the Weapon
	 *                   attackPoints.
	 * @return An enemy to murder.
	 */
	private Enemy createEnemy(int roomNumber) {
		int i = rand.nextInt(roomNumber + 1);
		return new Enemy(enemyNames[rand.nextInt(enemyNames.length)], true,
				(roomNumber + 10 + i) / 2,
				createWeapon(roomNumber), createShield(roomNumber));
	}

}
