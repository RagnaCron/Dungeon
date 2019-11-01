package DungeonEntity.Rooms.DataStructure;

import DungeonEntity.Rooms.FourDoorRoom;

import java.util.ArrayList;
import java.util.Arrays;

public class RoomList extends ArrayList<FourDoorRoom> {

	private int currentRoomIndex; // TODO: ROOM TRAVERSING.

	public RoomList(RoomList rooms) {
		super(rooms);
		currentRoomIndex = 0;
	}

	public RoomList(FourDoorRoom[] rooms) {
		super(Arrays.asList(rooms));
		currentRoomIndex = 0;
	}

	public RoomList(int amount) {
		super(Arrays.asList(new FourDoorRoom[amount]));
		currentRoomIndex = 0;
	}

	public RoomList(FourDoorRoom room) {
		super();
		this.add(room);
		currentRoomIndex = 0;
	}

	public RoomList() {
		super();
		currentRoomIndex = 0;
	}


	@Override
	public String toString() {
		StringBuilder buildItemList = new StringBuilder();
		buildItemList.append("Rooms:\n");
		for (FourDoorRoom room : this) {
			buildItemList.append("\t").append(room.toString()).append("\n");
		}
		return buildItemList.toString();
	}

}
