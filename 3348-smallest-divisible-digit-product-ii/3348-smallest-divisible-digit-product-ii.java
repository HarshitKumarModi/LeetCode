import java.util.*;

class Solution {
    // Prime factor counts contributed by digits 0 through 9
    private static final int[][] DIGIT_FACTORS = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2 -> 2^1
        {0, 1, 0, 0}, // 3 -> 3^1
        {2, 0, 0, 0}, // 4 -> 2^2
        {0, 0, 1, 0}, // 5 -> 5^1
        {1, 1, 0, 0}, // 6 -> 2^1 * 3^1
        {0, 0, 0, 1}, // 7 -> 7^1
        {3, 0, 0, 0}, // 8 -> 2^3
        {0, 2, 0, 0}  // 9 -> 3^2
    };

    public String smallestNumber(String num, long t) {
        int[] tPrimes = new int[4]; // Stores required powers for [2, 3, 5, 7]
        long tt = t;

        // Factorize t into prime factors (2, 3, 5, 7)
        while (tt % 2 == 0) { tPrimes[0]++; tt /= 2; }
        while (tt % 3 == 0) { tPrimes[1]++; tt /= 3; }
        while (tt % 5 == 0) { tPrimes[2]++; tt /= 5; }
        while (tt % 7 == 0) { tPrimes[3]++; tt /= 7; }

        // If t has prime factors other than 2, 3, 5, or 7, no digit product can satisfy it
        if (tt != 1) return "-1";

        int n = num.length();

        // 1. Check if the minimum digits required to form 't' exceeds the string length
        int[] reqDigitsForT = getOptimalDigitCounts(tPrimes);
        int minLenForT = sum(reqDigitsForT);
        if (minLenForT > n) {
            return constructString(reqDigitsForT, minLenForT);
        }

        // 2. Compute prefix prime factor counts for num
        int[][] prefixPrimes = new int[n + 1][4];
        int firstZero = n;
        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';
            if (d == 0) {
                firstZero = i;
                break;
            }
            for (int p = 0; p < 4; p++) {
                prefixPrimes[i + 1][p] = prefixPrimes[i][p] + DIGIT_FACTORS[d][p];
            }
        }

        // If 'num' contains no '0' and its product is already divisible by t
        if (firstZero == n && isSatisfied(tPrimes, prefixPrimes[n])) {
            return num;
        }

        // 3. Try to find the smallest valid number with the same length as 'num'
        for (int i = n - 1; i >= 0; i--) {
            if (i > firstZero) continue; // Skip positions beyond the first '0'

            int currentDigit = num.charAt(i) - '0';
            int spaceRemaining = n - 1 - i;

            for (int d = currentDigit + 1; d <= 9; d++) {
                int[] currentPrimes = new int[4];
                for (int p = 0; p < 4; p++) {
                    currentPrimes[p] = prefixPrimes[i][p] + DIGIT_FACTORS[d][p];
                }

                int[] neededPrimes = new int[4];
                for (int p = 0; p < 4; p++) {
                    neededPrimes[p] = Math.max(0, tPrimes[p] - currentPrimes[p]);
                }

                int[] digitsNeeded = getOptimalDigitCounts(neededPrimes);
                int countNeeded = sum(digitsNeeded);

                if (countNeeded <= spaceRemaining) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num.substring(0, i));
                    sb.append(d);

                    int onesToFill = spaceRemaining - countNeeded;
                    for (int k = 0; k < onesToFill; k++) {
                        sb.append('1');
                    }

                    for (int digit = 2; digit <= 9; digit++) {
                        for (int k = 0; k < digitsNeeded[digit]; k++) {
                            sb.append(digit);
                        }
                    }
                    return sb.toString();
                }
            }
        }

        // 4. If no candidate of length 'n' exists, construct the answer for length 'n + 1'
        return constructString(reqDigitsForT, n + 1);
    }

    // Convert required prime factor counts [2, 3, 5, 7] to minimal single-digit counts (8, 9, 4, 6, etc.)
    private int[] getOptimalDigitCounts(int[] primes) {
        int[] digitCounts = new int[10];
        
        int p2 = primes[0];
        int p3 = primes[1];
        
        // Greedily group factors to minimize total digit count
        digitCounts[8] = p2 / 3;
        p2 %= 3;

        digitCounts[9] = p3 / 2;
        p3 %= 2;

        digitCounts[5] = primes[2];
        digitCounts[7] = primes[3];

        digitCounts[4] = p2 / 2;
        p2 %= 2;

        digitCounts[2] = p2;
        digitCounts[3] = p3;

        // Optimize combinations (e.g., 2 * 3 -> 6)
        if (digitCounts[2] > 0 && digitCounts[3] > 0) {
            digitCounts[2]--;
            digitCounts[3]--;
            digitCounts[6]++;
        }
        if (digitCounts[3] > 0 && digitCounts[4] > 0) {
            digitCounts[3]--;
            digitCounts[4]--;
            digitCounts[2]++;
            digitCounts[6]++;
        }

        return digitCounts;
    }

    private boolean isSatisfied(int[] required, int[] available) {
        for (int i = 0; i < 4; i++) {
            if (available[i] < required[i]) return false;
        }
        return true;
    }

    private int sum(int[] arr) {
        int res = 0;
        for (int v : arr) res += v;
        return res;
    }

    private String constructString(int[] digitCounts, int totalLength) {
        int currentCount = sum(digitCounts);
        StringBuilder sb = new StringBuilder();
        int ones = totalLength - currentCount;
        for (int i = 0; i < ones; i++) {
            sb.append('1');
        }
        for (int d = 2; d <= 9; d++) {
            for (int k = 0; k < digitCounts[d]; k++) {
                sb.append(d);
            }
        }
        return sb.toString();
    }
}