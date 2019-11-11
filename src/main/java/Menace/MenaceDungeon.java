package Menace;

import DungeonEntity.Dungeon.Dungeon;
import DungeonEntity.Fighters.Enemy;
import DungeonEntity.Items.DataStructure.ItemList;
import DungeonEntity.Items.Potion;
import DungeonEntity.Items.Shield;
import DungeonEntity.Items.Weapon;
import DungeonEntity.Rooms.DataStructure.RoomList;
import DungeonEntity.Rooms.FourDoorRoom;

import java.util.Random;

// TODO: JAVADOC

/**
 *
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class MenaceDungeon extends Dungeon<FourDoorRoom> {

	private Random rand = new Random();

	private String[] potionNames = {"Water", "Small Potion", "Potion", "Big Potion",};
	private String[] shieldsNames = {"Broken Shield", "Shield", "Iron Shield",};
	private String[] weaponName = { "Stick", "Golden Sword", "Katana", "Broken Sword", "Sword", "Iron Sword",};
	private String[] enemyNames = {"Skeleton", "Ork", "Dragon", "Ghoul", "Zombie", "Dark Elb", "Goblin", "Slime",
			"Mummy", "Your Mom", "Your non existing Girlfriend", "Dark Magician"
	};

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
	public void spawnEnemies() {
		for (int i = 0; i < rooms.size(); i++) {
			FourDoorRoom room = rooms.get(i);
			room.setItems(new ItemList());
			room.setCurrentRoomNumber(i + 1);
			if (i % 4 == 0 || i % 10 == 0) {
				room.getItems().add(createPotion(room.getCurrentRoomNumber()));
			}
			resetEnemy(room, room.getCurrentRoomNumber());
		}
	}

	/**
	 * ReSpawn dead enemies. Call this to re spawn enemies in a dungeon.
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
			resetEnemy(room, room.getCurrentRoomNumber());
		}
	}


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

	private void setNewDirection(Integer nextRoomNumber, FourDoorRoom room, int direction) {
		if (direction == 0) room.setNorthDoor(nextRoomNumber);
		else if (direction == 1) room.setSouthDoor(nextRoomNumber);
		else if (direction == 2) room.setWestDoor(nextRoomNumber);
		else if (direction == 3) room.setEastDoor(nextRoomNumber);
	}

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

	private void resetEnemy(FourDoorRoom room, int roomNumber) {
		room.setEnemy(createEnemy(roomNumber));
	}

	private Potion createPotion(int roomNumber) {
		return new Potion(potionNames[rand.nextInt(potionNames.length)],
				(roomNumber + 2) * 2 + 1);
	}

	private Weapon createWeapon(int roomNumber) {
		return new Weapon(weaponName[rand.nextInt(weaponName.length)],
				(roomNumber + 3) * 2 - 2);
	}

	private Shield createShield(int roomNumber) {
		return new Shield(shieldsNames[rand.nextInt(shieldsNames.length)],
				(roomNumber + 3) * 2 - 3);
	}

	private Enemy createEnemy(int roomNumber) {
		int i = rand.nextInt(roomNumber + 1);
		return new Enemy(enemyNames[rand.nextInt(enemyNames.length)], true,
				(roomNumber + 10 + i) / 2,
				createWeapon(roomNumber), createShield(roomNumber));
	}

}
