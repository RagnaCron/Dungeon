package DungeonEntity.Rooms.Base;

public interface Queue<T> {

	int size();

	void put(T payload);

	boolean isEmpty();

	T remove();

	T getFrontElement();

	T getRearElement();

}
