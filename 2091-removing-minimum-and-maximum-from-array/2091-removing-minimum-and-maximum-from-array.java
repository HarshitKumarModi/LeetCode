class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        for(int i = 0; i<n; i++){
            if(nums[i] < nums[minIndex]){
                minIndex = i;
            }
        }

        for(int i = 0; i<n; i++){
            if(nums[i] > nums[maxIndex]){
                maxIndex = i;
            }
        }

        int left = Math.min(maxIndex, minIndex);
        int right = Math.max(maxIndex, minIndex);

        int fromFront = right + 1;

        int fromBack = n - left;

        int fromBothEnd = (left+1) + (n-right);

        return Math.min(fromFront, Math.min(fromBack,fromBothEnd));
    }
}