package DungeonEntity.Rooms.DataStructure;

import DungeonEntity.Rooms.Base.Queue;

public class OuroborusQueue<T> implements Queue<T> {

	private int front, rear;

	private T[] queue;

	public OuroborusQueue() {
		this(10);
	}

	public OuroborusQueue(int capacity) {
		if (capacity < 1)
			throw new IllegalArgumentException("Capacity must be >= 1");
		queue = (T[]) new Object[capacity];
	}

	@Override
	public void put(T payload) {
		if ((rear + 1) % queue.length == front) {
			T[] newQueue = (T[]) new Object[queue.length * 2];
			int start = (front + 1 ) % queue.length;
			if (start < 2) {
				System.arraycopy(queue, start, newQueue, 0, queue.length - 1);
			} else {
				System.arraycopy(queue, start, newQueue, 0, queue.length - start);
				System.arraycopy(queue, 0, newQueue, queue.length - start, rear + 1);
			}
			front = newQueue.length - 1;
			rear = queue.length - 2;
			queue = newQueue;
		}
		rear = (rear + 1) % queue.length;
		queue[rear] = payload;
	}

	@Override
	public int size() {
		return Math.abs(rear - front);
	}

	@Override
	public boolean isEmpty() {
		return front == rear;
	}

	@Override
	public T remove() {
		if (isEmpty()) return null;

		front = (front + 1) % queue.length;
		T item = queue[front];
		queue[front] = null;
		return item;
	}

	@Override
	public T getFrontElement() {
		return isEmpty() ? null : queue[(front + 1) % queue.length];
	}

	@Override
	public T getRearElement() {
		return isEmpty() ? null : queue[rear % queue.length];
	}
}
