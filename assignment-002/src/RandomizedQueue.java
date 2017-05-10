import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of a randomized queue
 *
 * @author Rohan Amburle
 * @version %I%, %G%
 * @since 1.0
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    // Holds the number of current active elements in the queue
    private int size;

    // We use an array to store elements. Resize as needed.
    Item queue[];

    public RandomizedQueue() {

        // Start at initial size 10
        // We keep this as minimum as we want to avoid
        // frequent resize for small values
        // TODO - Change this to use Array.newInstance()
        queue = (Item[]) new Object[10];
        size = 0;
    }

    /**
     * Increase the array size if full
     * Doubles the array size
     */
    private void enlargeQueueIfNeeded() {
        if (queue.length == size) {
            resizeQueue(queue.length * 2);
        }
    }

    /**
     * Reduce the array size if sufficiently empty
     * Halves the array size if it is <= 25% full
     */
    private void trimQueueIfpossible() {
        if (size <= (queue.length / 4)) {
            // No need to check lower boundaries
            // as array size should never go below 2
            resizeQueue(queue.length/2);
        }
    }

    /**
     * Resizes the array to give size and copies the content over
     * @param newSize New array size
     */
    private void resizeQueue(int newSize) {
        if (newSize < 1) {
            // Just in case
            throw new UnsupportedOperationException(
                    "Array size must be a positive integer");
        }
        // We won't reduce array size below 10
        if (newSize >= 10) {
            Item newQueue[] = (Item[]) new Object[newSize];

            for(int i=0; i<size;i++) {
                newQueue[i] = queue[i];
                // Remove old references
                queue[i] = null;
            }

            queue = newQueue;
        }
    }

    /**
     * Check if queue is empty
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Checks size of the queue
     *
     * @return Number of current active nodes in the queue
     */
    public int size() {
        return size;
    }

    /**
     * Add an element to the back of the queue
     * @param item Element to be added
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException(
                    "Cannot add NULL element to the queue");
        }
        enlargeQueueIfNeeded();
        queue[size] = item;
        size++;
    }

    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is already empty");
        }

        Item element = queue[size];
        queue[size] = null;
        size--;
        trimQueueIfpossible();
        return  element;
    }

    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
    }

    @Override
    public Iterator<Item> iterator() {
    }

    public static void main(String[] args) {
    }
}
