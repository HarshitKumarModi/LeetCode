class Solution {
    public boolean uniformArray(int[] nums1) {

        int min = nums1[0];

        for (int i = 1; i < nums1.length; i++) {
            min = Math.min(min, nums1[i]);
        }

        if (min % 2 != 0) {
            return true;
        }

        for (int num : nums1) {
            if (num % 2 != 0) {
                return false;
            }
        }

        return true;
    }
}