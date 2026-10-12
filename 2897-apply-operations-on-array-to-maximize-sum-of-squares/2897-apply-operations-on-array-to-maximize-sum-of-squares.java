
class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int MOD = 1_000_000_007;
        int[] bitCount = new int[31];

        // Step 1: Count occurrences of each set bit
        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if ((num & (1 << bit)) != 0) {
                    bitCount[bit]++;
                }
            }
        }

        long ans = 0;

        // Step 2: Construct k maximum values
        for (int i = 0; i < k; i++) {
            long value = 0;

            for (int bit = 30; bit >= 0; bit--) {
                if (bitCount[bit] > 0) {
                    value |= (1L << bit);
                    bitCount[bit]--;
                }
            }

            // Step 3: Add the square modulo MOD
            ans = (ans + value * value % MOD) % MOD;
        }

        return (int) ans;
    }
}
