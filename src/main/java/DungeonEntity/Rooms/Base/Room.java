package DungeonEntity.Rooms.Base;

import lombok.Getter;
import lombok.Setter;

/**
 * The Abstract Room Class. Is the basis for all kind of different Rooms.
 * It has a name and not much more.
 *
 * @author Manuel Werder
 * @version 0.1
 */
@Getter
@Setter
public abstract class Room {

	/**
	 * The field roomName.
	 */
	private String roomName;

	/**
	 * The Room of ... what ever.
	 * @param roomName A nice Room name.
	 */
	public Room(String roomName) {
		this.roomName = roomName;
	}

	/**
	 * The nome of the room.
	 * @return The room name.
	 */
	@Override
	public String toString() {
		return roomName;
	}

}
