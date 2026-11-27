
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;
        int[] dp = new int[budget + 1];

        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            minPrice = Math.min(minPrice, item[1]);
        }

        // 0/1 Knapsack
        for (int i = 0; i < n; i++) {

            int factor = items[i][0];
            int price = items[i][1];

            // Count items received (including the purchased item)
            int count = 0;

            for (int j = 0; j < n; j++) {
                if (items[j][0] % factor == 0) {
                    count++;
                }
            }

            // Buy first copy of this item type
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(
                    dp[b],
                    dp[b - price] + count
                );
            }
        }

        // Use remaining budget to buy cheapest items
        int ans = 0;

        for (int b = 0; b <= budget; b++) {
            int total = dp[b] + (budget - b) / minPrice;
            ans = Math.max(ans, total);
        }

        return ans;
    }
}
