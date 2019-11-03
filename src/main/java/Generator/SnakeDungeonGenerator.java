package Generator;

import DungeonEntity.Fighters.Enemy;
import DungeonEntity.Items.Base.Item;
import DungeonEntity.Items.DataStructure.ItemList;
import DungeonEntity.Items.Potion;
import DungeonEntity.Items.Shield;
import DungeonEntity.Items.Weapon;
import DungeonEntity.Rooms.DataStructure.RoomList;
import DungeonEntity.Rooms.Directions;
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

	private Directions randomDoorDirection() {
		int i = rand.nextInt(4);
		switch (i) {
			case 0: return Directions.NORTH_DOOR;
			case 1: return Directions.SOUTH_DOOR;
			case 2: return Directions.WEST_DOOR;
			case 3: return Directions.EAST_DOOR;
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
			Directions direction = randomDoorDirection();
			if (direction != null && checkDoorToNextRoom(room, direction)) {
				if (currentRoomNumber + 1 < rooms.size() - 2) {
					FourDoorRoom nextRoom = rooms.get(currentRoomNumber + 1);
					setDoorDirection(direction, room, nextRoom);
					currentRoomNumber++;
				}
				else return;
			}
		}
	}

	private boolean checkDoorToNextRoom(FourDoorRoom room, Directions direction) {
		switch (direction) {
			case NORTH_DOOR: return !room.hasNorthDoor();
			case SOUTH_DOOR: return !room.hasSouthDoor();
			case WEST_DOOR: return !room.hasWestDoor();
			case EAST_DOOR: return !room.hasEastDoor();
			default: return false;
		}
	}

	private void setDoorDirection(Directions direction, FourDoorRoom currentRoom, FourDoorRoom nextRoom) {
		switch (direction){
			case NORTH_DOOR:
				currentRoom.setNorthDoor(Directions.SOUTH_DOOR);
				nextRoom.setLastRoomNumber(currentRoom.getCurrentRoomNumber());
				currentRoom.setNextRoomNumber(nextRoom.getCurrentRoomNumber());
				break;
			case SOUTH_DOOR:
				currentRoom.setNorthDoor(Directions.NORTH_DOOR);
				nextRoom.setLastRoomNumber(currentRoom.getCurrentRoomNumber());
				currentRoom.setNextRoomNumber(nextRoom.getCurrentRoomNumber());
				break;
			case WEST_DOOR:
				currentRoom.setNorthDoor(Directions.EAST_DOOR);
				nextRoom.setLastRoomNumber(currentRoom.getCurrentRoomNumber());
				currentRoom.setNextRoomNumber(nextRoom.getCurrentRoomNumber());
				break;
			case EAST_DOOR:
				currentRoom.setNorthDoor(Directions.WEST_DOOR);
				nextRoom.setLastRoomNumber(currentRoom.getCurrentRoomNumber());
				currentRoom.setNextRoomNumber(nextRoom.getCurrentRoomNumber());
				break;
		}
	}


}
