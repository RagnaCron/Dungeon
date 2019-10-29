package DungeonEntity.Rooms.Base;

/**
 * The Abstract Room Class. Is the basis for all kind of different Rooms.
 * It has a name and not much more.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public abstract class Room {

	/**
	 * The field roomName is final.
	 */
	private final String roomName;

	/**
	 * The Room of ... what ever.
	 * @param roomName A nice Room name.
	 */
	public Room(String roomName) {
		this.roomName = roomName;
	}

	@Override
	public String toString() {
		return roomName;
	}

}