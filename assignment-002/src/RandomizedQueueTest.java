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

    private RandomizedQueue<String> randomizedQueue;
    @BeforeEach
    void setUp() {
        randomizedQueue = new RandomizedQueue<String>();
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

    @Test
    @DisplayName("Test random order on dequeue()")
    void testRandomDequeue() {
        for (Integer i = 0; i < 10; i++) {
            randomizedQueue.enqueue(i.toString());
        }
        String str = "";
        while(!randomizedQueue.isEmpty()) {
            str += randomizedQueue.dequeue();
        }
        assertNotEquals("0123456789", str);
    }

    @Test
    @DisplayName("Test random order on sample()")
    void testRandomSample() {
        for (Integer i = 0; i < 10; i++) {
            randomizedQueue.enqueue(i.toString());
        }
        String str = "";
        for (int i = 0; i < 10; i++) {
            str += randomizedQueue.sample();
        }
        assertNotEquals("0123456789", str);
    }
    private void clearDeque() {
        if (randomizedQueue != null) {
            while(!randomizedQueue.isEmpty()) {
                randomizedQueue.dequeue();
            }
        }
    }

}