import edu.princeton.cs.algs4.StdRandom;

import javax.naming.OperationNotSupportedException;
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

        // Set PRNG seed using system time.
        StdRandom.setSeed(System.currentTimeMillis());
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

    /**
     * Removes and returns a random item from the queue
     * @return A random item from the queue.
     */
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is already empty");
        }

        int elementToRemove = StdRandom.uniform(size);
        Item element = queue[elementToRemove];

        // Move the last element to this location so the array is compressed.
        queue[elementToRemove] = queue[size - 1];

        // Reset the removed element to address loitering.
        queue[size - 1] = null;

        size--;
        trimQueueIfpossible();
        return  element;
    }

    /**
     * Returns (But does not remove) a random item from the queue
     * @return A random item from the queue.
     */
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }

        return queue[StdRandom.uniform(size)];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int[] elementOrder;
        private int cursor;

        public RandomizedQueueIterator() {

            // Initiate array with random order
            elementOrder = new int[size];
            for (int i = 0; i < size; i++) {
                elementOrder[i] = i;
            }
            // Each iterator is required to have a separate random
            // order. Set PRNG seed using system time.
            StdRandom.setSeed(System.currentTimeMillis());

            StdRandom.shuffle(elementOrder);
            cursor = 0;
        }
        @Override
        public boolean hasNext() {
            return (cursor < elementOrder.length);
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Queue is already empty");
            }
            return queue[elementOrder[cursor++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException
                    ("Element removal via iterator not allowed");
        }
    }

    public static void main(String[] args) {
    }
}
