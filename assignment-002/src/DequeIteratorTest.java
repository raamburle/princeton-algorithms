import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Rohan Amburle on 5/12/2017.
 */

@DisplayName("Test the Deque iterator")
public class DequeIteratorTest {
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
    @DisplayName("Test remove() method")
    void testRemove() {
        assertThrows(UnsupportedOperationException.class, () -> deque.iterator().remove());
    }

    @Test
    @DisplayName("Test the hasNext() method")
    void testHasNext() {
        // Check for empty Deque
        assertFalse(deque.iterator().hasNext());

        // Check non-empty Deque
        deque.addFirst("Hello1");
        Iterator<String> iterator = deque.iterator();
        assertTrue(iterator.hasNext());

        // Test for concurrent modification
        deque.removeFirst();
        assertThrows(ConcurrentModificationException.class, () -> iterator.hasNext());

        // Test if a new iterator works if created on a modified list
        assertFalse(deque.iterator().hasNext());
    }

    private void clearDeque() {
        if (deque != null) {
            while(!deque.isEmpty()) {
                deque.removeFirst();
            }
        }
    }
}