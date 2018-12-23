package org.redquark.dp.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Anirudh Sharma
 *
 * This class calculates the Nth Fibonacci term
 */
public class FibonacciSequence {

    // Lookup array for the memoization technique
    private static long[] lookup;

    public static void main(String[] args) {

        try {

            // Getting the instance of BufferedReader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            // Getting the number of test cases
            int testCases = Integer.parseInt(bufferedReader.readLine());

            // Iterate the logic for each test case
            for (int i = 0; i < testCases; i++) {

                // Getting the value of n.
                int n = Integer.parseInt(bufferedReader.readLine());

                // Getting the result using Tabulation technique
                System.out.println("Result using Tabulation technique :: " + tabulation(n));

                // Stuff for the memoization technique
                lookup = new long[n + 1];

                // Filling the lookup array
                init(lookup, n);

                // Getting the result using Memoization technique
                System.out.println("Result using Memoization technique :: " + memoization(n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param lookup - array for the memoization technique
     */
    private static void init(long[] lookup, int n) {

        // Filling the lookup array
        for (int i = 0; i < n+1; i++) {
            lookup[i] = -1;
        }
    }

    /**
     * Memoization - Top down approach
     * @param n - Nth term to be calculated
     * @return long
     */
    private static long memoization(int n) {

        // This condition will check if the lookup for n is filled. -1 suggests it is not filled
        if (lookup[n] == -1) {

            // Base condition for 0 or 1
            if (n <= 1) {
                lookup[n] = n;
            } else {

                // Recursively fill the lookup for n - this will use the lookup table for already created values
                lookup[n] = memoization(n - 1) + memoization(n - 2);
            }
        }

        // Return the lookup for n.
        return lookup[n];
    }

    /**
     * Tabulation - Bottom up approach
     * @param n - Nth term to be calculated
     * @return long
     */
    private static long tabulation(int n) {

        // Initialized the array with n+1 terms - all values will be filled
        long[] f = new long[n + 1];

        // Base condition
        f[0] = 0;
        f[1] = 1;

        // Iteratively fill the array
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        // Return the Nth term
        return f[n];
    }
}
