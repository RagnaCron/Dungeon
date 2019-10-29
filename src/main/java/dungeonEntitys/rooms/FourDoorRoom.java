package dungeonEntitys.rooms;


import dungeonEntitys.items.generic.ItemList;
import dungeonEntitys.rooms.generic.Room;

/**
 * The FourDoorRoom has four doors.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class FourDoorRoom extends Room {

	private Door northDoor;
	private Door southDoor;
	private Door westDoor;
	private Door eastDoor;

	private ItemList items;

	public FourDoorRoom(String roomName, Door northDoor, Door southDoor, Door westDoor, Door eastDoor) {
		super(roomName);
		this.northDoor = northDoor;
		this.southDoor = southDoor;
		this.westDoor = westDoor;
		this.eastDoor = eastDoor;
	}

	@Override
	public String toString() {
		StringBuilder buildDoorString = new StringBuilder(super.toString()).append(":\n");
		if (hasNorthDoor()) buildDoorString.append("\t").append(northDoor.toString()).append("\n");
		if (hasSouthDoor()) buildDoorString.append("\t").append(southDoor.toString()).append("\n");
		if (hasWestDoor()) buildDoorString.append("\t").append(westDoor.toString()).append("\n");
		if (hasEastDoor()) buildDoorString.append("\t").append(eastDoor.toString()).append("\n");
		return new String(buildDoorString);
	}

	public boolean hasNorthDoor() {
		return northDoor != null;
	}

	public boolean hasSouthDoor() {
		return southDoor != null;
	}

	public boolean hasWestDoor() {
		return westDoor != null;
	}

	public boolean hasEastDoor() {
		return eastDoor != null;
	}

}
