package Generator;

import DungeonEntity.Rooms.DataStructure.RoomList;
import DungeonEntity.Rooms.FourDoorRoom;
import Generator.Base.DungeonGenerator;
import Menace.MenaceDungeon;

import java.util.Random;

/**
 * The TwoWayDungeonGenerator class is capable of generating random dungeon,
 * the rooms are connected with one door to another. No more then two
 * doors are used per room.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class TwoWayDungeonGenerator extends DungeonGenerator {

	// TODO: JAVADOC

	protected RoomList rooms = new RoomList();
	protected Random rand = new Random();

	private int roomsCount;

	protected Integer randomDoorDirection() {
		return rand.nextInt(4);
	}


	public TwoWayDungeonGenerator(int roomsCount) {
		this.roomsCount = roomsCount;
	}

	@Override
	protected void generateRooms(int count) {
		rooms = new RoomList();
		for (int i = 0; i <= count; i++) {
			rooms.add(new FourDoorRoom("Room " + (i + 1), i));
		}
	}

	@Override
	public MenaceDungeon getDungeon() {
		generateRooms(roomsCount);
		connectRooms();
		return new MenaceDungeon(rooms);
	}

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

	protected boolean checkDoorToNextRoom(Integer direction, FourDoorRoom currentRoom) {
		if (direction == 0 && !currentRoom.hasNorthDoor()) return true;
		if (direction == 1 && !currentRoom.hasSouthDoor()) return true;
		if (direction == 2 && !currentRoom.hasWestDoor()) return true;
		if (direction == 3 && !currentRoom.hasEastDoor()) return true;
		return false;
	}

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
