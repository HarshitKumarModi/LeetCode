
class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        int[][] cost = new int[n][n];

        // Precompute minimum changes for every substring
        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {
                int r = l + len - 1;
                cost[l][r] = Integer.MAX_VALUE;

                // Find all proper divisors of substring length
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Divide characters into d groups
                    for (int i = 0; i < d; i++) {
                        int left = l + i;
                        int right = l + i + ((len / d) - 1) * d;

                        // Check whether each group is a palindrome
                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    cost[l][r] = Math.min(cost[l][r], changes);
                }
            }
        }

        // DP: dp[i][j] = minimum changes to divide
        // first i characters into j semi-palindromes
        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            java.util.Arrays.fill(row, Integer.MAX_VALUE / 2);
        }

        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {

                // Last substring starts at position p
                for (int p = 0; p < i; p++) {

                    if (i - p < 2) {
                        continue;
                    }

                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[p][j - 1] + cost[p][i - 1]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
