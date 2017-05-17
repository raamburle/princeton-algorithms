import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Rohan Amburle on 5/16/2017.
 */
public class RandomizedQueueIteratorTest {
    private RandomizedQueue<String> randomizedQueue;

    @BeforeEach
    void setUp() {
        randomizedQueue = new RandomizedQueue<String>();
    }

    @AfterEach
    void tearDown() {
        clearQueue();
    }

    @Test
    @DisplayName("Test remove() method")
    void testRemove() {
        assertThrows(UnsupportedOperationException.class, () -> randomizedQueue.iterator().remove());
    }

    @Test
    @DisplayName("Test the hasNext() method")
    void testHasNext() {
        // Check for empty Queue
        assertFalse(randomizedQueue.iterator().hasNext());

        // Check non-empty Queue
        randomizedQueue.enqueue("Hello1");
        Iterator<String> iterator = randomizedQueue.iterator();
        assertTrue(iterator.hasNext());

        // Test for concurrent modification
        randomizedQueue.dequeue();
        assertThrows(ConcurrentModificationException.class, () -> iterator.hasNext());

        // Test if a new iterator works if created on a modified list
        assertFalse(randomizedQueue.iterator().hasNext());
    }

    @Test
    @DisplayName("Test the next() method" + "")
    void testNext() {
        // Test for next() on an empty randomizedQueue
        assertThrows(NoSuchElementException.class, () -> randomizedQueue.iterator().next());

        // Test on a non-empty randomizedQueue
        randomizedQueue.enqueue("Hello1");
        Iterator<String> iterator1 = randomizedQueue.iterator();
        assertEquals(iterator1.next(), "Hello1");

        // Test for concurrent modification
        // Must throw ConcurrentModificationException
        randomizedQueue.dequeue();
        assertThrows(ConcurrentModificationException.class, () -> iterator1.next());

        // Check if works on the modified queue
        randomizedQueue.enqueue("Hello2");
        Iterator<String> iterator2 = randomizedQueue.iterator();
        assertEquals(iterator2.next(), "Hello2");
    }

    private void clearQueue() {
        if (randomizedQueue != null) {
            while(!randomizedQueue.isEmpty()) {
                randomizedQueue.dequeue();
            }
        }
    }
}
