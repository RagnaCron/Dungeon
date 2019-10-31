package DungeonEntity.Rooms;


import DungeonEntity.Fighters.Enemy;
import DungeonEntity.Items.DataStructure.ItemList;
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
	private int currentRoomNumber;
	@Setter
	@Getter
	private int lastRoomNumber;
	@Setter
	@Getter
	private int nextRoomNumber;

	@Getter
	private ItemList items;
	@Setter
	@Getter
	private Enemy enemy;

	public FourDoorRoom(String roomName) {
		this(roomName, 0, null, null);
	}

	public FourDoorRoom(String roomName, int roomNumber, Enemy enemy, ItemList items) {
		this(roomName, roomNumber, enemy, items, null, null, null, null);
	}

	public FourDoorRoom(String roomName, int roomNumber, Enemy enemy, ItemList items,
						Door northDoor , Door southDoor, Door westDoor, Door eastDoor) {
		super(roomName);
		this.currentRoomNumber = roomNumber;
		this.northDoor = northDoor;
		this.southDoor = southDoor;
		this.westDoor = westDoor;
		this.eastDoor = eastDoor;
		this.enemy = enemy;
		this.items = items;
		this.lastRoomNumber = 0;
		this.nextRoomNumber = 0;
	}

	@Override
	public String toString() {
		StringBuilder buildDoorString = new StringBuilder(super.toString()).append(":\n");
		if (hasNorthDoor()) buildDoorString.append("\t").append(northDoor.toString()).append("\n");
		if (hasSouthDoor()) buildDoorString.append("\t").append(southDoor.toString()).append("\n");
		if (hasWestDoor()) buildDoorString.append("\t").append(westDoor.toString()).append("\n");
		if (hasEastDoor()) buildDoorString.append("\t").append(eastDoor.toString()).append("\n");
		buildDoorString.append("\t").append(enemy.getName());
		return buildDoorString.toString();
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

	public void setDoorDirection(Door direction, FourDoorRoom nextRoom) {
		switch (direction){
			case NORTHDOOR:
				northDoor = Door.SOUTHDOOR;
				nextRoom.setLastRoomNumber(currentRoomNumber);
				nextRoomNumber = nextRoom.getCurrentRoomNumber();
				break;
			case SOUTHDOOR:
				southDoor = Door.NORTHDOOR;
				nextRoom.setLastRoomNumber(currentRoomNumber);
				nextRoomNumber = nextRoom.getCurrentRoomNumber();
				break;
			case WESTDOOR:
				westDoor = Door.EASTDOOR;
				nextRoom.setLastRoomNumber(currentRoomNumber);
				nextRoomNumber = nextRoom.getCurrentRoomNumber();
				break;
			case EASTDOOR:
				eastDoor = Door.WESTDOOR;
				nextRoom.setLastRoomNumber(currentRoomNumber);
				nextRoomNumber = nextRoom.getCurrentRoomNumber();
				break;
		}
	}

}
