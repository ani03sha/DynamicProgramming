package org.redquark.dp.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Anirudh Sharma
 * <p>
 * Finding the nth Super Ugly Number
 */
public class SuperUglyNumbers {

    public static void main(String[] args) {

        // Try-With-Resource block with BufferedReader instance
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            // Getting the number of test cases
            int testCases = Integer.parseInt(bufferedReader.readLine());

            // Loop for each test case
            for (int i = 0; i < testCases; i++) {

                // Get the value of n
                int n = Integer.parseInt(bufferedReader.readLine());

                // Array that will hold the primes for finding ugly numbers - should be sorted, if not, then sort it
                int[] primes = {2, 3, 5, 7};

                // Finding the nth ugly number
                System.out.println(memoization(primes, n));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int memoization(int[] primes, int n) {

        // Base condition
        if (n == 1 || primes == null || primes.length == 0) {
            return 1;
        }

        // Sorting the primes array
        Arrays.sort(primes);

        // Length of primes array
        int primesLength = primes.length;

        // Index array
        int[] index = new int[primesLength];

        // List that will hold the resulting super ugly sequence
        List<Integer> result = new ArrayList<>();

        // Add 1 which is a default element in any ugly number sequence
        result.add(1);

        for (int i = 1; i < n; i++) {

            // Next value in the sequence
            int next = Integer.MAX_VALUE;

            // Loop for testing each element in the primes array
            for (int j = 0; j < primesLength; j++) {

                // Updating the next value
                next = Math.min(next, primes[j] * result.get(index[j]));
            }

            result.add(next);

            // Updating the index array
            for (int j = 0; j < primesLength; j++) {

                if (primes[j] * result.get(index[j]) == next) {

                    // Updating the index of jth element in the primes array
                    index[j]++;
                }
            }
        }

        // nth super ugly number
        return result.get(result.size() - 1);
    }
}
