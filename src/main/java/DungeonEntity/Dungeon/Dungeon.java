package DungeonEntity.Dungeon;

import DungeonEntity.Rooms.DataStructure.RoomList;
import DungeonEntity.Rooms.FourDoorRoom;
import lombok.Getter;



public abstract class Dungeon {

	protected RoomList rooms;
	@Getter
	protected FourDoorRoom currentRoom;

	public Dungeon(RoomList rooms) {
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
	 * Spawn new enemies in the rooms List.
	 */
	abstract public void spawnEnemies();

	/**
	 * ReSpawn dead enemies.
	 */
	abstract protected void reSpawnEnemies();

	abstract public void dropEnemyItems();
}
