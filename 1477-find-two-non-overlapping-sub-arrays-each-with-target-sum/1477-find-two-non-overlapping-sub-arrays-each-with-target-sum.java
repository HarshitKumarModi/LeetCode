class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int[] best = new int[n];

        int INF = n + 1;

        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        int left = 0;
        int sum = 0;
        int ans = INF;
        int minLength = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            // Shrink the window if sum becomes too large
            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            // Found a subarray with sum = target
            if (sum == target) {

                int currentLength = right - left + 1;

                // Check if there is a previous non-overlapping subarray
                if (left > 0 && best[left - 1] != INF) {
                    ans = Math.min(ans, currentLength + best[left - 1]);
                }

                // Keep the smallest target-sum subarray seen so far
                minLength = Math.min(minLength, currentLength);
            }

            // Store the best answer up to this index
            if (right > 0) {
                best[right] = Math.min(best[right - 1], minLength);
            } else {
                best[right] = minLength;
            }
        }

        return ans == INF ? -1 : ans;
    }
}