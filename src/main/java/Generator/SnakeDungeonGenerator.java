package Generator;

import DungeonEntity.Fighters.Enemy;
import DungeonEntity.Items.Base.Item;
import DungeonEntity.Items.Base.ItemList;
import DungeonEntity.Items.Potion;
import DungeonEntity.Items.Shield;
import DungeonEntity.Items.Weapon;
import DungeonEntity.Rooms.Base.RoomList;
import DungeonEntity.Rooms.Door;
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

	private Door randomDoorDirection() {
		int i = rand.nextInt(4);
		switch (i) {
			case 0: return Door.NORTHDOOR;
			case 1: return Door.SOUTHDOOR;
			case 2: return Door.WESTDOOR;
			case 3: return Door.EASTDOOR;
			default: return null;
		}
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
		while (true) { // TODO: MAKE DO WHILE OUT OF IT, USE SECOND IF STATEMENT FOR CHECK
			FourDoorRoom room = rooms.get(currentRoomNumber);
			Door direction = randomDoorDirection();
			if (checkDoorToNextRoom(room, direction)) {
				if (currentRoomNumber + 1 < rooms.size() - 2) {
					FourDoorRoom nextRoom = rooms.get(currentRoomNumber + 1);
					setDoorsDirections(room, direction, nextRoom);
					currentRoomNumber++;
				}
				else return;
			}
		}
	}

	private void setDoorsDirections(FourDoorRoom currentRoom, Door direction, FourDoorRoom nextRoom) {
		switch (direction) {
			case NORTHDOOR:
				currentRoom.setDoorDirection(direction);
				nextRoom.setDoorDirection(Door.SOUTHDOOR);
			case SOUTHDOOR:
				currentRoom.setDoorDirection(direction);
				nextRoom.setDoorDirection(Door.NORTHDOOR);
			case WESTDOOR:
				currentRoom.setDoorDirection(direction);
				nextRoom.setDoorDirection(Door.EASTDOOR);
			case EASTDOOR:
				currentRoom.setDoorDirection(direction);
				nextRoom.setDoorDirection(Door.WESTDOOR);
		}
	}

	private boolean checkDoorToNextRoom(FourDoorRoom room, Door direction) {
		switch (direction) {
			case NORTHDOOR: return !room.hasNorthDoor();
			case SOUTHDOOR: return !room.hasSouthDoor();
			case WESTDOOR: return !room.hasWestDoor();
			case EASTDOOR: return !room.hasEastDoor();
			default: return false;
		}
	}


}
