class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int maxStart = n - k;

        Map<Integer, List<Integer>> map = new HashMap<>();

        // Store positions of every value
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], x -> new ArrayList<>()).add(i);
        }

        int ans = -1;

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> pos = entry.getValue();

            int union = 0;
            int curL = -1, curR = -1;

            for (int p : pos) {
                int L = Math.max(0, p - k + 1);
                int R = Math.min(p, maxStart);

                if (L > R) continue;

                if (curL == -1) {
                    curL = L;
                    curR = R;
                } else if (L <= curR + 1) {
                    curR = Math.max(curR, R);
                } else {
                    union += curR - curL + 1;
                    curL = L;
                    curR = R;
                }
            }

            if (curL != -1) union += curR - curL + 1;

            if (union == 1) {
                ans = Math.max(ans, entry.getKey());
            }
        }

        return ans;
    }
}