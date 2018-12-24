package org.redquark.dp.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Anirudh Sharma
 * <p>
 * Ugly numbers are numbers whose only prime factors are 2, 3 or 5.
 * The sequence 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, … shows the first 11 ugly numbers.
 * By convention, 1 is included.
 * <p>
 * Given a number n, the task is to find n’th Ugly number.
 */
public class UglyNumbers {

    public static void main(String[] args) {

        // Try-With-Resource block with BufferedReader instance
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            // Getting the number of test cases
            int testCases = Integer.parseInt(bufferedReader.readLine());

            // Iterate the logic for each test case
            for (int i = 0; i < testCases; i++) {

                // Getting the value of n
                int n = Integer.parseInt(bufferedReader.readLine());

                // Finding the count of ugly numbers using the tabulation technique
                System.out.println(memoization(n));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int memoization(int n) {

        // Lookup array
        int[] lookup = new int[n];

        // Indexes for each factor 2, 3, and 5 respectively
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;

        // Initialize three choices of the next ugly number
        int next2 = 2;
        int next3 = 3;
        int next5 = 5;

        // Next ugly number
        int next = 1;

        // First number of the ugly number sequence
        lookup[0] = 1;

        // Iterate until we find the nth ugly number
        for (int i = 1; i < n; i++) {

            // Assigning the ith element in the lookup array with the minimum of next2, next3, next5
            next = Math.min(next2, Math.min(next3, next5));

            lookup[i] = next;

            if (next == next2) {
                i2++;
                next2 = lookup[i2] * 2;
            }

            if (next == next3) {
                i3++;
                next3 = lookup[i3] * 3;
            }

            if (next == next5) {
                i5++;
                next5 = lookup[i5] * 5;
            }
        }

        return next;
    }
}
