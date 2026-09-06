class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }

        return solve(0, 0, s, t, dp);
    }

    public int solve(int i, int j, String s, String t, int[][] dp) {

        // t is completely formed
        if (j == t.length()) {
            return 1;
        }

        // s is finished but t is not
        if (i == s.length()) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (s.charAt(i) == t.charAt(j)) {

            int take = solve(i + 1, j + 1, s, t, dp);

            int skip = solve(i + 1, j, s, t, dp);

            dp[i][j] = take + skip;

        } else {

            dp[i][j] = solve(i + 1, j, s, t, dp);
        }

        return dp[i][j];
    }
}