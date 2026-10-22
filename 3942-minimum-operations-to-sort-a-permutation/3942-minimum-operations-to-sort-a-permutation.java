class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        int ans = Integer.MAX_VALUE;

        boolean increasing = true;
        for (int i = 1; i < n; i++) {
            int prev = (zero + i - 1) % n;
            int curr = (zero + i) % n;

            if (nums[curr] != (nums[prev] + 1) % n) {
                increasing = false;
                break;
            }
        }

        if (increasing) {
            ans = Math.min(ans, zero);
            ans = Math.min(ans, n - zero + 2);
        }

        boolean decreasing = true;
        for (int i = 1; i < n; i++) {
            int prev = (zero - i + n) % n;
            int curr = (zero - i - 1 + n) % n;

            if (nums[curr] != (nums[prev] + 1) % n) {
                decreasing = false;
                break;
            }
        }

        if (decreasing) {
            ans = Math.min(ans, zero + 2);
            ans = Math.min(ans, n - zero);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}