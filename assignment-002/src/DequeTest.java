import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Rohan Amburle on 5/12/2017.
 */

@DisplayName("Test the Deque API")
public class DequeTest {
    private Deque deque;

    @BeforeEach
    void setUp() {
        deque = new Deque<String>();
    }

    @AfterEach
    void tearDown() {
        clearDeque();
    }

    @Test
    @DisplayName("Test isEmpty() method")
    void testIsEmpty() {
        assertTrue(deque.isEmpty());

        deque.addFirst("Hello");
        assertFalse(deque.isEmpty());

        deque.removeFirst();
        assertTrue(deque.isEmpty());
    }

    @Test
    @DisplayName("Test size() method")
    void testSize() {
        assertEquals(deque.size(), 0);

        deque.addFirst("Hello1");
        assertEquals(deque.size(), 1);

        deque.addLast("Hello2");
        assertEquals(deque.size(), 2);

        deque.removeFirst();
        assertEquals(deque.size(), 1);

        deque.removeLast();
        assertEquals(deque.size(), 0);
    }

    @Test
    @DisplayName("Test the addFirst() method")
    void testAddFirst() {
        deque.addFirst("Hello1");
        assertEquals(deque.size(), 1);
        assertEquals(deque.removeFirst(), "Hello1");

        // Test null element addition
        assertThrows(NullPointerException.class, () -> deque.addFirst(null));
        assertEquals(deque.size(), 0);
    }

    @Test
    @DisplayName("Test the addLast() method")
    void testAddLast() {
        deque.addLast("Hello1");

        assertEquals(deque.size(), 1);
        assertEquals(deque.removeLast(), "Hello1");

        // Test null element addition
        assertThrows(NullPointerException.class, () -> deque.addLast(null));
        assertEquals(deque.size(), 0);
    }

    @Test
    @DisplayName("Test the removeFirst() method")
    void testRemoveFirst() {
        assertThrows(NoSuchElementException.class, () -> deque.removeFirst());

        deque.addFirst("Hello1");
        assertEquals(deque.removeFirst(), "Hello1");
        assertEquals(deque.size(), 0);

        deque.addLast("Hello2");
        assertEquals(deque.removeFirst(), "Hello2");
        assertEquals(deque.size(), 0);
    }

    @Test
    @DisplayName("Test the removeLast() method")
    void testRemoveLast() {
        assertThrows(NoSuchElementException.class, () -> deque.removeLast());

        deque.addLast("Hello1");
        assertEquals(deque.removeLast(), "Hello1");
        assertEquals(deque.size(), 0);

        deque.addFirst("Hello1");
        assertEquals(deque.removeLast(), "Hello1");
        assertEquals(deque.size(), 0);
    }

    @Test
    @DisplayName("Test Forward FIFO sequence. Add and remove in same direction")
    void testForwardSequence() {
        for (int i = 1; i < 4; i++) {
            deque.addFirst("Hello" + i);
        }

        for (int i = 3; i > 0; i--) {
            assertEquals(deque.removeFirst(), "Hello" + i);
        }

        for (int i = 1; i < 4; i++) {
            deque.addLast("Hello" + i);
        }

        for (int i = 3; i > 0; i--) {
            assertEquals(deque.removeLast(), "Hello" + i);
        }
    }

    @Test
    @DisplayName("Test Reverse FIFO sequence. Add and remove in reverse direction")
    void testReverseSequence() {
        for (int i = 1; i < 4; i++) {
            deque.addFirst("Hello" + i);
        }

        for (int i = 1; i < 4; i++) {
            assertEquals(deque.removeLast(), "Hello" + i);
        }

        for (int i = 1; i < 4; i++) {
            deque.addLast("Hello" + i);
        }

        for (int i = 1; i < 4; i++) {
            assertEquals(deque.removeFirst(), "Hello" + i);
        }
    }

    private void clearDeque() {
        if (deque != null) {
            while (!deque.isEmpty()) {
                deque.removeFirst();
            }
        }
    }
}