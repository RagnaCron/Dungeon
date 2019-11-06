package Generator;

import DungeonEntity.Rooms.DataStructure.RoomList;

public abstract class DungeonGenerator {
	abstract protected void generateRooms(int count);
	abstract public RoomList getDungeon();
}
