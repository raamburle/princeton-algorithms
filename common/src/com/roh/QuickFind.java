package com.roh;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Code a simple quick find and not so quick union
 *
 * Program waits for pair of integers. Exits if any of them is negative
 *
 * ** TODO :: Implement a way to display the connected sets
 */
public class QuickFind {

    public static void main(String[] args) throws IOException {

        Scanner StdIn = new Scanner(System.in);
        int[] elements = new int[10];

        // populate the unconnected data set
        for (int i = 0; i < elements.length; i++) {
            elements[i] = i;
            //System.out.println(elements[i]);
        }

        while (true) {
            int p = StdIn.nextInt();
            int q = StdIn.nextInt();
            if (p < 0 || q < 0)
                break;
            if (p > 9 || q > 9) {
                System.out.println("Invalid element");
                continue;
            }

            connected(elements, p, q);
            union(elements, p, q);
            System.out.println(Arrays.toString(elements));
        }
    }

    private static void union(int[] elements, int p, int q) {
        int pid = elements[p];
        int qid = elements[q];
        if (pid == qid) {
            System.out.println("Already connected");
        } else {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] == pid) {
                    elements[i] = qid;
                }
            }
        }
    }

    private static void connected(int[] elements, int p, int q) {
        System.out.println("Elements " + p + " and " + q + " are" +
                ((elements[p] == elements[q]) ? " " : " not ") + "connected");
    }
}
