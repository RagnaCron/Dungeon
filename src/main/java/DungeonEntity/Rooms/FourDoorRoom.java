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
@Getter
@Setter
public final class FourDoorRoom extends Room {

	// TODO: JAVADOC

	private Integer northDoor;
	private Integer southDoor;
	private Integer westDoor;
	private Integer eastDoor;
	private int currentRoomNumber;
	private ItemList items;
	private Enemy enemy;

	public FourDoorRoom(String roomName) {
		this(roomName, 0, null, null);
	}

	public FourDoorRoom(String roomName, int roomNumber, Enemy enemy, ItemList items) {
		this(roomName, roomNumber, enemy, items, null, null, null, null);
	}

	public FourDoorRoom(String roomName, int roomNumber, Enemy enemy, ItemList items,
	                    Integer northDoor , Integer southDoor, Integer westDoor, Integer eastDoor) {
		super(roomName);
		this.currentRoomNumber = roomNumber;
		this.northDoor = northDoor;
		this.southDoor = southDoor;
		this.westDoor = westDoor;
		this.eastDoor = eastDoor;
		this.enemy = enemy;
		this.items = items;
	}

	@Override
	public String toString() {
		StringBuilder buildDoorString = new StringBuilder(super.toString()).append(":\n");
		if (hasNorthDoor()) buildDoorString.append("There is a door to the North").append("\n");
		if (hasSouthDoor()) buildDoorString.append("There is a door to the South").append("\n");
		if (hasWestDoor()) buildDoorString.append("There is a door to the West").append("\n");
		if (hasEastDoor()) buildDoorString.append("There is a door to the East").append("\n");
		if (hasEnemy()) buildDoorString.append(enemy.toString()).append("\n");
		if (!hasEnemy()) buildDoorString.append(items.toString());
		return buildDoorString.toString();
	}

	public boolean hasEnemy() { return enemy != null; }

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
