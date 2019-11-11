package Generator.Base;

import DungeonEntity.Dungeon.Dungeon;
import DungeonEntity.Rooms.Base.Room;

/**
 * The abstract DungeonGenerator class provides two Methods.
 * Type T must extend Room.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public abstract class DungeonGenerator<T extends Room> {
	/**
	 * With this the rooms are generated with the given count of rooms.
	 * @param count How many rooms do you want?
	 */
	abstract protected void generateRooms(int count);

	/**
	 * Returns an instance of a Dungeon Subclass.
	 * @return The Dungeon to explore.
	 */
	abstract public Dungeon<T> getDungeon();
}
