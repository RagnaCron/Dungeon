package DungeonEntity.Rooms;


import DungeonEntity.Fighters.Enemy;
import DungeonEntity.Items.DataStructure.ItemList;
import DungeonEntity.Rooms.Base.Room;
import lombok.Getter;
import lombok.Setter;

/**
 * The FourDoorRoom has four doors it extends the Room class. It has an ItemList
 * and one Enemy.
 *
 * @author Manuel Werder
 * @version 0.1
 */
@Getter @Setter
public final class FourDoorRoom extends Room {

	private Integer northDoor;
	private Integer southDoor;
	private Integer westDoor;
	private Integer eastDoor;
	private int currentRoomNumber;
	private ItemList items;
	private Enemy enemy;

	/**
	 * Init the room, and calls a different constructor of this class.
	 * @param roomName A nice and evil name.
	 * @param roomNumber Some Room number, it is used to navigate make traversing from one Room to another possible,
	 *                   it should be an according number that suites you needs.
	 */
	public FourDoorRoom(String roomName, int roomNumber) {
		this(roomName, roomNumber, null, null);
	}

	/**
	 * Init thee room, and calls a different constructor of this class.
	 * @param roomName A nice and evil name.
	 * @param roomNumber Some Room number, it is used to navigate make traversing from one Room to another possible,
	 *                   it should be an according number that suites you needs.
	 * @param enemy Some evil enemy.
	 * @param items Some good stuff to pick up in the game.
	 */
	public FourDoorRoom(String roomName, int roomNumber, Enemy enemy, ItemList items) {
		this(roomName, roomNumber, enemy, items, null, null, null, null);
	}

	/**
	 * Init thee room, and calls a different constructor of this class.
	 * @param roomName A nice and evil name.
	 * @param roomNumber Some Room number, it is used to navigate make traversing from one Room to another possible,
	 *                   it should be an according number that suites you needs.
	 * @param enemy Some evil enemy.
	 * @param items Some good stuff to pick up in the game.
	 * @param northDoor If null no door. Should hold the number to the next Room, if the RoomList is used in a Dungeon
	 *                  Class, as this is the preferable way to traverse to the next room.
	 * @param southDoor If null no door. Should hold the number to the next Room, if the RoomList is used in a Dungeon
	 *                  Class, as this is the preferable way to traverse to the next room.
	 * @param westDoor If null no door. Should hold the number to the next Room, if the RoomList is used in a Dungeon
	 *                 Class, as this is the preferable way to traverse to the next room.
	 * @param eastDoor If null no door. Should hold the number to the next Room, if the RoomList is used in a Dungeon
	 *                 Class, as this is the preferable way to traverse to the next room.
	 */
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

	/**
	 * Shows the content of the room. Might be useful to navigate in the room.
	 * @return Life is beautiful.
	 */
	@Override
	public String toString() {
		StringBuilder buildDoorString = new StringBuilder(super.toString()).append(":\n");
		if (hasNorthDoor()) buildDoorString.append("There is a door to the North").append("\n");
		if (hasSouthDoor()) buildDoorString.append("There is a door to the South").append("\n");
		if (hasWestDoor()) buildDoorString.append("There is a door to the West").append("\n");
		if (hasEastDoor()) buildDoorString.append("There is a door to the East").append("\n");
		if (hasEnemy() && isEnemyALife()) buildDoorString.append(enemy.toString()).append("\n");
		if (!isEnemyALife() && hasItems()) buildDoorString.append(items.toString());
		return buildDoorString.toString();
	}

	/**
	 * Does the room have Items?
	 * @return Is true if ItemList is not null.
	 */
	public boolean hasItems() { return items != null; }

	/**
	 * Does the room have en enemy?
	 * @return Is true if enemy is not null.
	 */
	public boolean hasEnemy() { return enemy != null; }

	/**
	 * Is the enemy a life?
	 * @return Is true if enemy is not null and is a life.
	 */
	public boolean isEnemyALife() { return hasEnemy() && enemy.isALife(); }

	/**
	 * Does the Room have a north door?
	 * @return Is true if the door is not null.
	 */
	public boolean hasNorthDoor() {
		return northDoor != null;
	}

	/**
	 * Does the Room have a south door?
	 * @return Is true if the door is not null.
	 */
	public boolean hasSouthDoor() {
		return southDoor != null;
	}

	/**
	 * Does the Room have a west door?
	 * @return Is true if the door is not null.
	 */
	public boolean hasWestDoor() {
		return westDoor != null;
	}

	/**
	 * Does the Room have a east door?
	 * @return Is true if the door is not null.
	 */
	public boolean hasEastDoor() {
		return eastDoor != null;
	}

}
