package com.roh;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Code a weighted quick union
 *
 * Program waits for pair of integers. Exits if any of them is negative
 *
 * ** TODO :: Change to make this weighted quick union and Implement a way to display the connected sets
 */
public class WeightedQuickUnion {

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
            System.out.println(Arrays.toString(new int[]{0,1,2,3,4,5,6,7,8,9}));
            System.out.println(Arrays.toString(elements));
        }
    }

    private static void union(int[] elements, int p, int q) {
        if (rootElement(elements, p) == rootElement(elements, q)) {
            System.out.println("Already connected");
        } else {
            elements[rootElement(elements,p)] = elements[rootElement(elements,q)];
        }
    }

    private static void connected(int[] elements, int p, int q) {
        System.out.println("Elements " + p + " and " + q + " are" +
                ((rootElement(elements, p) == rootElement(elements, q)) ? " " : " not ") + "connected");
    }

    private static int rootElement(int[] elements, int p) {
        /*while (elements[p] != p) {
            p = elements[p];
        }
        return p;*/

        int element = p;
        int parentElement, grandParentElement;
        while(true) {
            parentElement = elements[element];
            if(parentElement == element) {
                break;
            }
            grandParentElement = elements[parentElement];

            /** Flattening the tree
             * If grandparent exits, make it parent of the element in question
             */
            if (grandParentElement != parentElement) {
                elements[element] = grandParentElement;
            }
            element = parentElement;
        }
        return element;
    }

}
