import org.junit.jupiter.api.*;

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
    void isEmpty() {
        assertTrue(deque.isEmpty());

        deque.addFirst("Hello");
        assertFalse(deque.isEmpty());

        deque.removeFirst();
        assertTrue(deque.isEmpty());
    }

    @Test
    @DisplayName("Test size() method")
    void size() {
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
    void addFirst() {
        deque.addFirst("Hello1");
        assertEquals(deque.removeFirst(), "Hello1");

        assertThrows(NullPointerException.class,() -> deque.addFirst(null));
    }

    @Test
    @DisplayName("Test the addLast() method")
    void addLast() {
        deque.addLast("Hello1");
        assertEquals(deque.removeLast(), "Hello1");

        assertThrows(NullPointerException.class,() -> deque.addLast(null));
    }

    @Test
    @DisplayName("Test the removeFirst() method")
    void removeFirst() {
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
    void removeLast() {
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

        for (int i = 3; i > 0 ; i--) {
            assertEquals(deque.removeFirst(), "Hello" + i);
        }

        for (int i = 1; i < 4; i++) {
            deque.addLast("Hello" + i);
        }

        for (int i = 3; i > 0 ; i--) {
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
            while(!deque.isEmpty()) {
                deque.removeFirst();
            }
        }
    }
}