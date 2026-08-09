class Solution {

    int[][] dp;
    int[] suffix;
    int n;

    public int stoneGameII(int[] piles) {

        n = piles.length;

        dp = new int[n][n + 1];
        suffix = new int[n + 1];

        // Calculate suffix sums
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        return dfs(0, 1);
    }

    private int dfs(int i, int M) {

        // All piles have been taken
        if (i >= n) {
            return 0;
        }

        // Already calculated
        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int maxStones = 0;

        // Try taking X piles
        for (int X = 1; X <= 2 * M && i + X <= n; X++) {

            int opponent = dfs(
                i + X,
                Math.max(M, X)
            );

            int currentPlayer = suffix[i] - opponent;

            maxStones = Math.max(maxStones, currentPlayer);
        }

        dp[i][M] = maxStones;

        return maxStones;
    }
}