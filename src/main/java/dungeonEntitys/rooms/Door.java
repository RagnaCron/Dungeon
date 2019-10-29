package dungeonEntitys.rooms;

/**
 *
 *
 * @author Manuel Werder
 * @version 0.1
 */
public enum Door {
	NORTHDOOR,
	SOUTHDOOR,
	WESTDOOR,
	EASTDOOR;

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
