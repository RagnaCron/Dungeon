package Generator;

import DungeonEntity.Dungeon.Dungeon;
import DungeonEntity.Rooms.DataStructure.RoomList;
import DungeonEntity.Rooms.FourDoorRoom;
import Generator.Base.DungeonGenerator;
import Menace.MenaceDungeon;

import java.util.Random;

/**
 * The TwoWayDungeonGenerator class is capable of generating random dungeon,
 * the rooms are connected with one door to another. No more then two
 * doors are used per room. This Generator uses the FourDoorRoom class.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class TwoWayDungeonGenerator extends DungeonGenerator {

	protected RoomList<FourDoorRoom> rooms;
	protected Random rand = new Random();

	private int roomsCount;

	/**
	 * Get a random direction between 0 an 3.
	 * @return The direction form 0 (north), 1 (south), 2 (west) or 3 (east).
	 */
	protected Integer randomDoorDirection() {
		return rand.nextInt(4);
	}


	/**
	 * Init TwoWayDungeonGenerator with the given amount of rooms.
	 * @param roomsCount How many rooms do you want?
	 */
	public TwoWayDungeonGenerator(int roomsCount) {
		this.roomsCount = roomsCount;
	}

	/**
	 * Generates empty room.
	 * @param count How many rooms do you want?
	 */
	@Override
	protected void generateRooms(int count) {
		rooms = new RoomList<>();
		for (int i = 0; i <= count; i++) {
			rooms.add(new FourDoorRoom("Room " + (i + 1), i));
		}
	}

	/**
	 * Returns a MenaceDungeon.
	 * @return The MenaceDungeon to explore.
	 */
	@Override
	public Dungeon getDungeon() {
		generateRooms(roomsCount);
		connectRooms();
		return new MenaceDungeon(rooms);
	}

	/**
	 * The true beauty of this generator. It makes traversing possible in the RoomList with FourDoorRooms.
	 * You just net a Method in the game to go to the next room.
	 */
	protected void connectRooms() {
		int currentRoomNumber = 0;
		while (true) {
			FourDoorRoom currentRoom = rooms.get(currentRoomNumber);
			Integer direction = randomDoorDirection();
			if (checkDoorToNextRoom(direction, currentRoom)) {
				if (currentRoomNumber + 1 < rooms.size() - 1) {
					FourDoorRoom nextRoom = rooms.get(currentRoomNumber + 1);
					setDoorDirection(direction, currentRoom, nextRoom);
					currentRoomNumber++;
				} else if (currentRoomNumber + 1 == rooms.size() - 1) {
					FourDoorRoom nextRoom = rooms.first();
					setDoorDirection(direction, currentRoom, nextRoom);
					currentRoomNumber++;
				}
				else return;
			}
		}
	}

	/**
	 * Check if there is a door set for the direction.
	 * @param direction The direction form 0 (north), 1 (south), 2 (west) or 3 (east).
	 * @param currentRoom The current room to check.
	 * @return Is true if the direction is not yet set.
	 */
	protected boolean checkDoorToNextRoom(Integer direction, FourDoorRoom currentRoom) {
		if (direction == 0 && !currentRoom.hasNorthDoor()) return true;
		if (direction == 1 && !currentRoom.hasSouthDoor()) return true;
		if (direction == 2 && !currentRoom.hasWestDoor()) return true;
		if (direction == 3 && !currentRoom.hasEastDoor()) return true;
		return false;
	}

	/**
	 * This is used to make a room to room connection.
	 * @param direction The direction form 0 (north), 1 (south), 2 (west) and 3 (east).
	 * @param currentRoom The current room to set the door with the next room number.
	 * @param nextRoom The next room to get the room number form.
	 */
	protected void setDoorDirection(Integer direction, FourDoorRoom currentRoom, FourDoorRoom nextRoom) {
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
