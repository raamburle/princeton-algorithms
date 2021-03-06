import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Rohan Amburle on 5/16/2017.
 * <p>
 * Test class for RandomizedQueue iterator
 */

@DisplayName("Test the RandomizedQueue Iterator")
class RandomizedQueueIteratorTest {
    private RandomizedQueue<String> randomizedQueue;

    @BeforeEach
    void setUp() {
        randomizedQueue = new RandomizedQueue<>();
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
    @DisplayName("Test the next() method")
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

    @Test
    @DisplayName("Test random order of the iterator()")
    void testRandomOrder() {
        for (Integer i = 0; i < 10; i++) {
            randomizedQueue.enqueue(i.toString());
        }

        String str = "";
        for (String s : randomizedQueue) {
            str += s;
        }
        assertNotEquals("0123456789", str);
        assertNotEquals("9876543210", str);
    }

    @Test
    @DisplayName("Test if two iterators have different random orders")
    void testOrderExclusivity() {
        for (Integer i = 0; i < 10; i++) {
            randomizedQueue.enqueue(i.toString());
        }

        String str1 = "";
        String str2 = "";
        Iterator<String> iterator1 = randomizedQueue.iterator();

        while (iterator1.hasNext()) {
            str1 += iterator1.next();
        }
        for (String s : randomizedQueue) {
            str2 += s;
        }
        assertFalse(str1.equals(str2));
    }

    private void clearQueue() {
        if (randomizedQueue != null) {
            while (!randomizedQueue.isEmpty()) {
                randomizedQueue.dequeue();
            }
        }
    }
}
