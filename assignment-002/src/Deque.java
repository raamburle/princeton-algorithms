import java.util.ConcurrentModificationException;
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
        element.previous = null;
        if (front != null) {
            front.previous = element;
        }

        if (back == null) {
            back = element;
        }

        front = element;
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
        element.next = null;
        if (back != null) {
            back.next = element;
        }

        if (front == null) {
            front = element;
        }

        back = element;
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
        } else {
            front.previous = null;
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
        } else {
            back.next = null;
        }
        size--;
        return element.data;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private void printQueue() {
        if (front == null) return;
        String output = "Size = " + size + " Elements: ";
        for (Item data : this) {
            //for (Node i = front; i != null; i=i.next) {
            output = output + data + " ";
        }
        System.out.println(output);
    }

    private class DequeIterator implements Iterator<Item> {

        private Node cursor;

        // Store size at creation as a primitive guard against
        // concurrent modification.
        private int startingSize;

        public DequeIterator() {
            cursor = front;
            startingSize = size;
        }

        @Override
        public boolean hasNext() {
            if(startingSize != size){
                //Deque modified. Throw exception
                throw new ConcurrentModificationException(
                        "Iterator expired as the deque has been modified");
            }
            return (cursor == null) ? false : true;
        }

        @Override
        public Item next() {
            if(startingSize != size){
                //Deque modified. Throw exception
                throw new ConcurrentModificationException(
                        "Iterator expired as the deque has been modified");
            }

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