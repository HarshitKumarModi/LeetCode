class Solution {
    static final int MOD = 1000000007;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        long[][] c = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            c[i][0] = c[i][i] = 1;
            for (int j = 1; j < i; j++) {
                c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % MOD;
            }
        }

        return (int) ((solve(nums, c) - 1 + MOD) % MOD);
    }

    private long solve(int[] nums, long[][] c) {
        if (nums.length <= 2) return 1;

        int root = nums[0];
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        int l = 0, r = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < root) left[l++] = nums[i];
            else right[r++] = nums[i];
        }

        left = java.util.Arrays.copyOf(left, l);
        right = java.util.Arrays.copyOf(right, r);

        long ways = c[l + r][l];
        ways = ways * solve(left, c) % MOD;
        ways = ways * solve(right, c) % MOD;

        return ways;
    }
}