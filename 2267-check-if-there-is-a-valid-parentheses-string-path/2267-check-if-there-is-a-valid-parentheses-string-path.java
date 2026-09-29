class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // A valid parentheses string must have even length
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Start must be '(' and end must be ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        Boolean[][][] dp = new Boolean[m][n][m + n];

        return dfs(grid, 0, 0, 0, dp);
    }

    private boolean dfs(char[][] grid, int r, int c, int balance,
                         Boolean[][][] dp) {

        // Add current character
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        // Balance can never become negative
        if (balance < 0) {
            return false;
        }

        // Too many '(' to possibly close
        if (balance > grid.length + grid[0].length - r - c - 1) {
            return false;
        }

        // Reached bottom-right
        if (r == grid.length - 1 && c == grid[0].length - 1) {
            return balance == 0;
        }

        if (dp[r][c][balance] != null) {
            return dp[r][c][balance];
        }

        boolean result = false;

        // Move down
        if (r + 1 < grid.length) {
            result = dfs(grid, r + 1, c, balance, dp);
        }

        // Move right
        if (!result && c + 1 < grid[0].length) {
            result = dfs(grid, r, c + 1, balance, dp);
        }

        return dp[r][c][balance] = result;
    }
}