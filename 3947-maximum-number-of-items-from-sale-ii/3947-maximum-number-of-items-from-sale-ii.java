
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;

        // Find maximum factor and minimum price
        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        // Count frequency of each factor
        int[] freq = new int[maxFactor + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Count how many items each factor can unlock
        int[] gain = new int[maxFactor + 1];

        for (int i = 1; i <= maxFactor; i++) {
            for (int j = i; j <= maxFactor; j += i) {
                gain[i] += freq[j];
            }
            gain[i]--;
        }

        // Sort items by price
        Arrays.sort(items, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        long remaining = budget;
        long answer = 0;

        // Buy items that provide one additional free item
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            // A boosted item must cost less than
            // twice the cheapest item's price
            if (2L * price >= 2L * minPrice * 2L) {
                break;
            }

            int freeCount = gain[factor];

            if (freeCount <= 0) {
                continue;
            }

            long take = Math.min(
                (long) freeCount,
                remaining / price
            );

            answer += take * 2;
            remaining -= take * price;
        }

        // Spend remaining budget on cheapest items
        answer += remaining / minPrice;

        return (int) answer;
    }
}
