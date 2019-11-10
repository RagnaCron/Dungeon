package Generator;

import DungeonEntity.Rooms.FourDoorRoom;

public class OneWayDungeonGenerator extends TwoWayDungeonGenerator {

	// TODO: JAVADOC

	public OneWayDungeonGenerator(int roomsCount) {
		super(roomsCount);
	}

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
