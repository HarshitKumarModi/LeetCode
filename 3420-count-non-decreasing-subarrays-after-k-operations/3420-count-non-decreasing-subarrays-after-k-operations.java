
import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        long ans = 0;
        long cost = 0;

        Deque<int[]> dq = new ArrayDeque<>();

        int j = nums.length - 1;

        for (int i = nums.length - 1; i >= 0; i--) {

            int num = nums[i];
            int count = 1;

            // Maintain a non-increasing monotonic deque
            while (!dq.isEmpty() && dq.peekLast()[0] < num) {

                int[] last = dq.pollLast();

                int nextNum = last[0];
                int nextCount = last[1];

                count += nextCount;

                // Increase smaller elements to the current number
                cost += (long) (num - nextNum) * nextCount;
            }

            dq.offerLast(new int[]{num, count});

            // Shrink the window if operations exceed k
            while (cost > k) {

                int[] first = dq.pollFirst();

                int rightmostNum = first[0];
                int rightmostCount = first[1];

                cost -= rightmostNum - nums[j];
                j--;

                if (rightmostCount > 1) {
                    dq.offerFirst(
                        new int[]{rightmostNum, rightmostCount - 1}
                    );
                }
            }

            // Count all valid subarrays ending at j
            ans += j - i + 1;
        }

        return ans;
    }
}
