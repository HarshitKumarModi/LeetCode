import java.util.*;

class Solution {

    class MedianWindow {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        long sumLeft = 0, sumRight = 0;
        int sizeLeft = 0, sizeRight = 0;

        void add(int num) {
            if (left.isEmpty() || num <= left.lastKey()) {
                left.put(num, left.getOrDefault(num, 0) + 1);
                sumLeft += num;
                sizeLeft++;
            } else {
                right.put(num, right.getOrDefault(num, 0) + 1);
                sumRight += num;
                sizeRight++;
            }
            balance();
        }

        void remove(int num) {
            if (left.containsKey(num)) {
                decrement(left, num);
                sumLeft -= num;
                sizeLeft--;
            } else {
                decrement(right, num);
                sumRight -= num;
                sizeRight--;
            }
            balance();
        }

        void decrement(TreeMap<Integer, Integer> map, int num) {
            int count = map.get(num);
            if (count == 1) {
                map.remove(num);
            } else {
                map.put(num, count - 1);
            }
        }

        void balance() {
            while (sizeLeft > sizeRight + 1) {
                int num = left.lastKey();
                decrement(left, num);
                sumLeft -= num;
                sizeLeft--;

                right.put(num, right.getOrDefault(num, 0) + 1);
                sumRight += num;
                sizeRight++;
            }

            while (sizeLeft < sizeRight) {
                int num = right.firstKey();
                decrement(right, num);
                sumRight -= num;
                sizeRight--;

                left.put(num, left.getOrDefault(num, 0) + 1);
                sumLeft += num;
                sizeLeft++;
            }
        }

        long getCost() {
            int median = left.lastKey();

            return (long) median * sizeLeft - sumLeft
                 + sumRight - (long) median * sizeRight;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;
        long INF = Long.MAX_VALUE / 4;

        // cost[i] = cost of subarray ending at i (exclusive)
        long[] cost = new long[n + 1];

        MedianWindow window = new MedianWindow();

        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }

        cost[x] = window.getCost();

        for (int i = x + 1; i <= n; i++) {
            window.remove(nums[i - x - 1]);
            window.add(nums[i - 1]);

            cost[i] = window.getCost();
        }

        // DP with rolling arrays
        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        // Zero subarrays require zero operations
        Arrays.fill(prev, 0);

        for (int count = 1; count <= k; count++) {

            Arrays.fill(curr, INF);

            for (int i = count * x; i <= n; i++) {

                // Option 1: Skip current element
                curr[i] = curr[i - 1];

                // Option 2: Select subarray [i-x, i)
                long candidate = prev[i - x] + cost[i];

                curr[i] = Math.min(curr[i], candidate);
            }

            prev = curr.clone();
        }

        return prev[n];
    }
}