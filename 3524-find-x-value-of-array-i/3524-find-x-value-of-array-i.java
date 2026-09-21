class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] result = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {

            long[] newDp = new long[k];

            // Start a new subarray with only num
            int rem = num % k;
            newDp[rem]++;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRem = (int)((long) r * rem % k);
                    newDp[newRem] += dp[r];
                }
            }

            // Add all subarrays ending at this position
            for (int r = 0; r < k; r++) {
                result[r] += newDp[r];
            }

            dp = newDp;
        }

        return result;
    }
}