package DungeonEntity.Rooms;

import DungeonEntity.Rooms.DataStructure.OuroborusRoomQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OuroborusRoomQueueTest {

	private OuroborusRoomQueue<String> queue;

	@BeforeEach
	void setUp() {
		queue = new OuroborusRoomQueue<>(1);
	}

	@Test
	void illegalArgument() {
		assertThrows(IllegalArgumentException.class, () -> new OuroborusRoomQueue<>(-10));
	}

	@Test
	void put() {
		queue.put("Hello");
		assertFalse(queue.isEmpty());
		queue.put("world");
		queue.put("yes");
	}

	@Test
	void isEmpty() {
		assertTrue(queue.isEmpty());
	}

	@Test
	void remove() {
		queue.put("Hello");
		assertEquals("Hello", queue.remove());
		assertTrue(queue.isEmpty());
		assertNull(queue.remove());
	}

	@Test
	void getFrontElement() {
		assertNull(queue.getFrontElement());
		queue.put("Hello");
		assertEquals("Hello", queue.getFrontElement());
	}

	@Test
	void getRearElement() {
		assertNull(queue.getRearElement());
		queue.put("Hello");
		assertEquals("Hello", queue.getRearElement());
	}

	@Test
	void runQueue() {
		queue.put("Hello");
		queue.put("world");
		assertEquals("Hello", queue.getFrontElement());
		assertEquals("world", queue.getRearElement());
		assertEquals("Hello", queue.remove());
		assertEquals("world", queue.remove());
		assertTrue(queue.isEmpty());
		assertNull(queue.getFrontElement());
		assertNull(queue.getRearElement());
	}

	@Test
	void size() {
		assertEquals(0, queue.size());
		queue.put("Hello");
		assertEquals(1, queue.size());
		queue.remove();
		assertEquals(0, queue.size());
	}
}