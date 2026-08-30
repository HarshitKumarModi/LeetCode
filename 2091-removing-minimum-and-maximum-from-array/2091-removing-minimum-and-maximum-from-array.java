class Solution {
    public int minimumDeletions(int[] nums) {

        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find indices of minimum and maximum
        for (int i = 1; i < n; i++) {

            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Remove both from the front
        int bothFront = Math.max(minIndex, maxIndex) + 1;

        // Remove both from the back
        int bothBack = n - Math.min(minIndex, maxIndex);

        // Remove one from front and one from back
        int oneFrontOneBack =
                Math.min(minIndex, maxIndex) + 1
                + n - Math.max(minIndex, maxIndex);

        return Math.min(
                bothFront,
                Math.min(bothBack, oneFrontOneBack)
        );
    }
}