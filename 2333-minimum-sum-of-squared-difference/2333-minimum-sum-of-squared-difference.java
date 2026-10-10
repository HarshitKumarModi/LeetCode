class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long total = (long) k1 + k2;
        int[] diff = new int[n];
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        int[] freq = new int[maxDiff + 1];

        for (int d : diff) {
            freq[d]++;
        }

        for (int d = maxDiff; d > 0 && total > 0; d--) {
            long count = freq[d];
            long use = Math.min(total, count);

            if (use == count) {
                freq[d - 1] += freq[d];
                freq[d] = 0;
                total -= count;
            } else {
                freq[d] -= (int) use;
                freq[d - 1] += (int) use;
                total -= use;
            }
        }

        long result = 0;

        for (int d = 0; d < freq.length; d++) {
            result += (long) d * d * freq[d];
        }

        return result;
    }
}