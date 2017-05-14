import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        deque.addFirst("Hello1");
        deque.addFirst("Hello2");


    }

    private void clearDeque() {
        if (deque != null) {
            while(!deque.isEmpty()) {
                deque.removeFirst();
            }
        }
    }
}