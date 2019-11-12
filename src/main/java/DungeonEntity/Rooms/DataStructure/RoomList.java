package DungeonEntity.Rooms.DataStructure;

import DungeonEntity.Rooms.Base.Room;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * RoomList extends the ArrayList of Type T. Type T must extend Room.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class RoomList<T extends Room> extends ArrayList<T> {
// TODO: DOKUMENT GENERIC
	/**
	 * Init static T Type Array of rooms.
	 * @param rooms The static T Type Array of rooms.
	 */
	public RoomList(T[] rooms) {
		super(Arrays.asList(rooms));
	}

	/**
	 * Init a RoomList with a T Type Room. First calls super() and then adds the room to RoomList.
	 * @param room T Type Room.
	 */
	public RoomList(T room) {
		super();
		this.add(room);
	}

	/**
	 * Init an empty RoomList.
	 */
	public RoomList() {
		super();
	}

	/**
	 * Gives a nice version of a RoomList back.
	 *
	 * @return The nice and the beauty.
	 */
	@Override
	public String toString() {
		StringBuilder buildItemList = new StringBuilder();
		buildItemList.append("Rooms:\n");
		for (T room : this) {
			buildItemList.append("\t").append(room.toString()).append("\n");
		}
		return buildItemList.toString();
	}

	/**
	 * Returns the first Item of a RoomList.
	 * @return IF isEmpty is true it will return null, otherwise the first element of the RoomList.
	 */
	public T first() {
		return isEmpty() ? null : get(0);
	}

}
