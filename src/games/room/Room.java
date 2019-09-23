package games.room;

import games.menace.fighters.Enemy;
import games.menace.items.generic.Item;
import games.menace.items.generic.ItemList;

/**
 * The Room Class is what makes the game fun. It has obviously four walls. Some items to take and
 * some enemies to kill.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class Room {

	private String roomName;
	private int northDoor;
	private int southDoor;
	private int westDoor;
	private int eastDoor;

	private Enemy enemy;
	private ItemList items;


}
