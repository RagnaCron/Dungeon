package DungeonEntity.Rooms;


import DungeonEntity.Fighters.Enemy;
import DungeonEntity.Items.Base.ItemList;
import DungeonEntity.Rooms.Base.Room;
import lombok.Getter;
import lombok.Setter;

/**
 * The FourDoorRoom has four doors.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public final class FourDoorRoom extends Room {

	private Door northDoor;
	private Door southDoor;
	private Door westDoor;
	private Door eastDoor;

	@Getter
	private ItemList items;
	@Setter
	@Getter
	private Enemy enemy;

	public FourDoorRoom(String roomName) {
		this(roomName, null, null, null, null, null);
	}

	public FourDoorRoom(String roomName, int roomNumber, Enemy enemy, ItemList items) {
		this(roomName);
		this.enemy = enemy;
		this.items = items;
	}

	public FourDoorRoom(String roomName, Enemy enemy, Door northDoor , Door southDoor, Door westDoor, Door eastDoor) {
		super(roomName);
		this.northDoor = northDoor;
		this.southDoor = southDoor;
		this.westDoor = westDoor;
		this.eastDoor = eastDoor;
		this.enemy = enemy;
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

	public void setDoorDirection(Door direction) {
		switch (direction){
			case NORTHDOOR: northDoor = direction;
			case SOUTHDOOR: southDoor = direction;
			case WESTDOOR: westDoor = direction;
			case EASTDOOR: eastDoor = direction;
		}
	}

}
