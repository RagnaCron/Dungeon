package DungeonEntity.Rooms.DataStructure;

import DungeonEntity.Rooms.FourDoorRoom;

import java.util.ArrayList;
import java.util.Arrays;

public class RoomList extends ArrayList<FourDoorRoom> {

	public RoomList(RoomList rooms) {
		super(rooms);
	}

	public RoomList(FourDoorRoom[] rooms) {
		super(Arrays.asList(rooms));
	}

	public RoomList(int amount) {
		super(Arrays.asList(new FourDoorRoom[amount]));
	}

	public RoomList(FourDoorRoom room) {
		super();
		this.add(room);
	}

	public RoomList() {
		super();
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
