import edu.princeton.cs.algs4.StdIn;

import java.util.NoSuchElementException;

/**
 * Permutation client
 *
 * @author Rohan Amburle
 * @version %I%, %G%
 * @since 1.0
 */
public class Permutation {

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("One numerical argument required");
        }

        int displayCount = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        for (; ; ) {
            try {
                queue.enqueue(StdIn.readString());
            } catch (NoSuchElementException e) {
                break;
            }
        }
        for (int i = 0; i < displayCount; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
