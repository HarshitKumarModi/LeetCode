class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean valid = true;
            int prevMax = Integer.MIN_VALUE;

            for (int i = 0; i < n; i += k) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                int descents = 0;

                for (int j = i; j < i + k; j++) {
                    min = Math.min(min, nums[j]);
                    max = Math.max(max, nums[j]);

                    int next = (j + 1 == i + k) ? i : j + 1;
                    if (nums[j] > nums[next]) descents++;
                }

                if (descents > 1 || prevMax > min) {
                    valid = false;
                    break;
                }

                prevMax = max;
            }

            if (valid) ans += k;
        }

        return ans;
    }
}