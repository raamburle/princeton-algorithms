import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Rohan Amburle on 5/12/2017.
 */
class RandomizedQueueTest {

    private RandomizedQueue randomizedQueue;
    @BeforeEach
    void setUp() {
        randomizedQueue = new RandomizedQueue();
    }

    @AfterEach
    void tearDown() {
        clearDeque();
    }

    @Test
    @DisplayName("Test isEmpty() method")
    void testIsEmpty() {
        assertTrue(randomizedQueue.isEmpty());

        randomizedQueue.enqueue("Hello");
        assertFalse(randomizedQueue.isEmpty());

        randomizedQueue.dequeue();
        assertTrue(randomizedQueue.isEmpty());
    }

    @Test
    @DisplayName("Test size() method")
    void testSize() {
        assertEquals(randomizedQueue.size(), 0);

        randomizedQueue.enqueue("Hello1");
        assertEquals(randomizedQueue.size(), 1);

        randomizedQueue.enqueue("Hello2");
        assertEquals(randomizedQueue.size(), 2);

        randomizedQueue.dequeue();
        assertEquals(randomizedQueue.size(), 1);

        randomizedQueue.dequeue();
        assertEquals(randomizedQueue.size(), 0);
    }

    @Test
    @DisplayName("Test the enqueue() method")
    void testEnqueue() {
        randomizedQueue.enqueue("Hello1");
        assertEquals(randomizedQueue.size(), 1);
        assertEquals(randomizedQueue.dequeue(), "Hello1");

        assertThrows(NullPointerException.class,() -> randomizedQueue.enqueue(null));
        assertEquals(randomizedQueue.size(), 0);
    }

    @Test
    @DisplayName("Test the dequeue() method")
    void testDequeue() {
        assertThrows(NoSuchElementException.class, () -> randomizedQueue.dequeue());

        randomizedQueue.enqueue("Hello1");
        assertEquals(randomizedQueue.dequeue(), "Hello1");
        assertEquals(randomizedQueue.size(), 0);
    }

    @Test
    @DisplayName("Test the sample() method")
    void testSample() {
        assertThrows(NoSuchElementException.class, () -> randomizedQueue.sample());

        randomizedQueue.enqueue("Hello1");
        assertEquals(randomizedQueue.sample(), "Hello1");
        assertEquals(randomizedQueue.size(), 1);
    }

    private void clearDeque() {
        if (randomizedQueue != null) {
            while(!randomizedQueue.isEmpty()) {
                randomizedQueue.dequeue();
            }
        }
    }

}