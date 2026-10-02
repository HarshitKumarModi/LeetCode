class Solution {
    public int splitArray(int[] nums, int k) {
        int left = 0;
        int right = 0;

        for(int num : nums){
            left = Math.max(num, left);
        }

        for(int num : nums){
            right += num;
        }

        while(left <= right){
            int mid = left + (right - left) / 2;

            int subArrays = 1;
            int currentSum = 0;

            for(int num : nums){
                if(currentSum + num <= mid){
                    currentSum += num;
                } else {
                    currentSum = num;
                    subArrays++;
                }
            }

            if(subArrays <= k){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}