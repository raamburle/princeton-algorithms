import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of a double ended queue
 *
 * @author Rohan Amburle
 * @version %I%, %G%
 * @since 1.0
 */
public class Deque<Item> implements Iterable<Item> {

    /**
     * Inner class representing individual element in the deque
     */
    private class Node {
        private Node next;
        private Node previous;
        private Item data;
    }

    // First node of the queue
    private Node front;

    // Last node of the queue
    private Node back;

    // Holds number of nodes present in the queue
    private int size;

    /**
     * Constructor
     */
    public Deque() {
        front = null;
        back = null;
        size = 0;
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
     * Add element to the front of the queue
     *
     * @param item Element to be added
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException(
                    "Cannot add NULL element to the queue");
        }
        Node element = new Node();
        element.data = item;
        element.next = front;
        front = element;

        if (back == null) {
            back = element;
        }
        size++;
    }

    /**
     * Add element to the back of the queue
     *
     * @param item Element to be added
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException(
                    "Cannot add NULL element to the queue");
        }
        Node element = new Node();
        element.data = item;
        element.previous = back;
        back = element;

        if (front == null) {
            front = element;
        }
        size++;
    }

    /**
     * Remove element from the front of the queue
     *
     * @return The item removed
     */
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is already empty");
        }
        Node element = front;
        front = front.next;
        if (front == null) {
            // Last element removed.
            back = null;
        }
        size--;
        return element.data;
    }

    /**
     * Remove element from the back of the queue
     *
     * @return The item removed
     */
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is already empty");
        }
        Node element = back;
        back = back.previous;
        if (back == null) {
            // Last element removed.
            front = null;
        }
        size--;
        return element.data;
    }

    public static void main(String[] args) {
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node cursor;

        public DequeIterator() {
            cursor = front;
        }

        @Override
        public boolean hasNext() {
            // Check if current element and next element is not null
            if (cursor != null) {
                if (cursor.next != null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                Node current = cursor;
                cursor = cursor.next;
                return current.data;
            }
            throw new NoSuchElementException("No more elements to return");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException
                    ("Element removal via iterator not allowed");
        }
    }
}