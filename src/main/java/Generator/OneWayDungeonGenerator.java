package Generator;

import DungeonEntity.Rooms.FourDoorRoom;

/**
 * The OneWayDungeonGenerator class is capable of generating a random dungeon,
 * the rooms are connected with one door to another. Only one door is used per room,
 * as there is no reason to go pack in this version of the generator.
 * This Generator uses the FourDoorRoom class.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class OneWayDungeonGenerator extends TwoWayDungeonGenerator {

	/**
	 * Calls super() with the roomsCount.
	 * @param roomsCount How many rooms do you want? (Tip: take 10)
	 */
	public OneWayDungeonGenerator(int roomsCount) {
		super(roomsCount);
	}

	/**
	 * This is used to make a room to room connection.
	 * @param direction The direction form 0 (north), 1 (south), 2 (west) and 3 (east).
	 * @param currentRoom The current room to set the door with the next room number.
	 * @param nextRoom The next room to get the room number form.
	 */
	@Override
	protected void setDoorDirection(Integer direction, FourDoorRoom currentRoom, FourDoorRoom nextRoom) {
		if (direction == 0) {
			currentRoom.setNorthDoor(nextRoom.getCurrentRoomNumber());
		}
		if (direction == 1) {
			currentRoom.setSouthDoor(nextRoom.getCurrentRoomNumber());
		}
		if (direction == 2) {
			currentRoom.setWestDoor(nextRoom.getCurrentRoomNumber());
		}
		if (direction == 3) {
			currentRoom.setEastDoor(nextRoom.getCurrentRoomNumber());
		}
	}

}
