package Generator;

import DungeonEntity.Fighters.Enemy;
import DungeonEntity.Items.Base.Item;
import DungeonEntity.Items.DataStructure.ItemList;
import DungeonEntity.Items.Potion;
import DungeonEntity.Items.Shield;
import DungeonEntity.Items.Weapon;
import DungeonEntity.Rooms.DataStructure.RoomList;
import DungeonEntity.Rooms.FourDoorRoom;

import java.util.Random;

/**
 * The SnakeDungeonGenerator class is capable of generating random dungeons,
 * the rooms are connected with one door to another. No more then two
 * doors are used per room. There are random Monsters an Loot to be found.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class SnakeDungeonGenerator {


	private RoomList rooms = new RoomList();
	private Random rand = new Random();
	private ItemList items = new ItemList(
			new Item[]{
					new Potion("Small Potion", 3),
					new Shield("Broken Shield", 4),
					new Weapon("Sword", 6)
			}
	);
	private Enemy[] enemies = {
			new Enemy("Lazy Skeleton", true, 5, new Weapon("Stick", 3)),
			new Enemy("Skeleton", true, 7, new Weapon("Wooden Sword", 5)),
			new Enemy("Strong Skeleton", true, 5, new Weapon("Iron Sword", 7)),
			new Enemy("Lazy Ork", true, 5, new Weapon("Strong Stick", 5)),
			new Enemy("Ork", true, 5, new Weapon("Axe", 6)),
	};

	private Integer randomDoorDirection() {
		return rand.nextInt(4);
	}

	private Item getRandomItem() {
		return items.get(rand.nextInt(items.size()));
	}

	private Enemy getRandomEnemy() {
		return enemies[rand.nextInt(enemies.length )];
	}

	private void generateRooms() {
		rooms = new RoomList();
		for (int i = 0; i < 43; i++) {
			ItemList itemList = new ItemList();
			itemList.add(getRandomItem());
			itemList.add(getRandomItem());
			rooms.add(new FourDoorRoom("Room " + i, i, getRandomEnemy(), itemList));
		}
	}

	public RoomList generateSnakeDungeon() {
		generateRooms();
		connectRooms();
		return rooms;
	}

	private void connectRooms() {
		int currentRoomNumber = 0;
		while (true) {
			FourDoorRoom currentRoom = rooms.get(currentRoomNumber);
			Integer direction = randomDoorDirection();
			if (checkDoorToNextRoom(direction, currentRoom)) {
				if (currentRoomNumber + 1 < rooms.size() - 2) {
					FourDoorRoom nextRoom = rooms.get(currentRoomNumber + 1);
					setDoorDirection(direction, currentRoom, nextRoom);
					currentRoomNumber++;
				}
				else return;
			}
		}
	}

	private boolean checkDoorToNextRoom(Integer direction, FourDoorRoom currentRoom) {
		if (direction == 0 && !currentRoom.hasNorthDoor()) return true;
		if (direction == 1 && !currentRoom.hasSouthDoor()) return true;
		if (direction == 2 && !currentRoom.hasWestDoor()) return true;
		if (direction == 3 && !currentRoom.hasEastDoor()) return true;
		return false;
	}

	private void setDoorDirection(Integer direction, FourDoorRoom currentRoom, FourDoorRoom nextRoom) {
		if (direction == 0) {
			currentRoom.setNorthDoor(nextRoom.getCurrentRoomNumber());
			nextRoom.setSouthDoor(currentRoom.getCurrentRoomNumber());
		}
		if (direction == 1) {
			currentRoom.setSouthDoor(nextRoom.getCurrentRoomNumber());
			nextRoom.setNorthDoor(currentRoom.getCurrentRoomNumber());
		}
		if (direction == 2) {
			currentRoom.setWestDoor(nextRoom.getCurrentRoomNumber());
			nextRoom.setEastDoor(currentRoom.getCurrentRoomNumber());
		}
		if (direction == 3) {
			currentRoom.setEastDoor(nextRoom.getCurrentRoomNumber());
			nextRoom.setWestDoor(currentRoom.getCurrentRoomNumber());
		}
	}

}
