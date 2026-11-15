class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1000000007;

        long odd = 0;
        long even = 1;
        long ans = 0;
        int prefixSum = 0;

        for (int num : arr) {
            prefixSum += num;

            if (prefixSum % 2 == 0) {
                ans = (ans + odd) % MOD;
                even++;
            } else {
                ans = (ans + even) % MOD;
                odd++;
            }
        }

        return (int) ans;
    }
}