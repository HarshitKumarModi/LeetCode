class Solution {
    public int stoneGameVIII(int[] stones) {

        int n = stones.length;

        // Step 1: Convert stones into prefix sums
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }

        // If Alice takes all stones
        int dp = stones[n - 1];

        // Step 2: Work backwards
        for (int i = n - 2; i > 0; i--) {
            dp = Math.max(dp, stones[i] - dp);
        }

        return dp;
    }
}