
class Solution {
    static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (dp[i][j] == 0) continue;

                long ways = dp[i][j];

                // Try moving RIGHT
                int r = i;
                int c = j + 1;
                int dir = 0; // 0 = right, 1 = down

                while (r < m && c < n && grid[r][c] == 1) {
                    if (dir == 0) {
                        r++;
                        dir = 1;
                    } else {
                        c++;
                        dir = 0;
                    }
                }

                if (r < m && c < n) {
                    dp[r][c] = (dp[r][c] + ways) % MOD;
                }

                // Try moving DOWN
                r = i + 1;
                c = j;
                dir = 1;

                while (r < m && c < n && grid[r][c] == 1) {
                    if (dir == 0) {
                        r++;
                        dir = 1;
                    } else {
                        c++;
                        dir = 0;
                    }
                }

                if (r < m && c < n) {
                    dp[r][c] = (dp[r][c] + ways) % MOD;
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}
