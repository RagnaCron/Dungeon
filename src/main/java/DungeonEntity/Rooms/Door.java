package DungeonEntity.Rooms;

/**
 * Just some Directions.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public enum Door {
	NORTH_DOOR,
	SOUTH_DOOR,
	WEST_DOOR,
	EAST_DOOR;

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
