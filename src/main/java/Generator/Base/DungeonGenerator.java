package Generator.Base;

import DungeonEntity.Dungeon.Dungeon;

public abstract class DungeonGenerator {
	abstract protected void generateRooms(int count);
	abstract public Dungeon getDungeon();
}
