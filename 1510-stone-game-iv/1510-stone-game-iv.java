class Solution {
    public boolean winnerSquareGame(int n) {

        boolean[] dp = new boolean[n + 1];

        // dp[0] = false
        // No stones -> player whose turn it is loses

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j * j <= i; j++) {

                int square = j * j;

                // If we can move to a losing state,
                // current player wins
                if (!dp[i - square]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}