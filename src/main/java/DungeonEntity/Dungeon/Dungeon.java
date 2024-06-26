package DungeonEntity.Dungeon;

import DungeonEntity.Rooms.Base.Room;
import DungeonEntity.Rooms.DataStructure.RoomList;
import lombok.Getter;

/**
 * The abstract Dungeon Class should provide a basic set of an API, so that one can build on top
 * a powerful dungeon. Dungeon takes a Type T that extends Room.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public abstract class Dungeon<T extends Room> {

	protected RoomList<T> rooms;
	@Getter
	protected T currentRoom;

	/**
	 * The Dungeon Constructor takes a room list.
	 * @param rooms A list of rooms.
	 */
	public Dungeon(RoomList<T> rooms) {
		this.rooms = rooms;
		currentRoom = rooms.first();
	}

	/**
	 * This Method will bring you to the next room. Its sets the currentRoom: FourDoorRoom.
	 *
	 * @param nextRoom An index that will set the current room out of the rooms list.
	 */
	abstract public void gotoNextRoom(int nextRoom);

	/**
	 * Spawn new enemies in the rooms List. This Method should be called inside of the Subclasses Constructor.
	 */
	abstract protected void spawnEnemies();

	/**
	 * ReSpawn dead enemies.
	 */
	abstract protected void reSpawnEnemies();

	/**
	 * This should drop enemy items under you conditions.
	 */
	abstract public void dropEnemyItems();
}
