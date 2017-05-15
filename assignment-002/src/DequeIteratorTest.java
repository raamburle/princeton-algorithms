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

    @Test
    @DisplayName("Test the next() method" + "")
    void testNext() {
        // Test for next() on an empty deque
        assertThrows(NoSuchElementException.class, () -> deque.iterator().next());

        // Test on a non-empty deque
        deque.addFirst("Hello1");
        deque.addFirst("Hello2");
        Iterator<String> iterator1 = deque.iterator();
        assertEquals(iterator1.next(), "Hello2");

        // Test for concurrent modification
        // Must throw ConcurrentModificationException
        deque.removeFirst();
        assertThrows(ConcurrentModificationException.class, () -> iterator1.next());

        // Confirm NoSuchElementException at the deque end
        deque.addFirst("Hello3");
        deque.addLast("Hello4");
        Iterator<String> iterator2 = deque.iterator();
        for (int i = 0; i < deque.size(); i++) {
            iterator2.next();
        }

        assertThrows(NoSuchElementException.class, () -> iterator2.next());
    }

    private void clearDeque() {
        if (deque != null) {
            while(!deque.isEmpty()) {
                deque.removeFirst();
            }
        }
    }
}