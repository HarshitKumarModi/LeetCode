class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int zero = 0, ones = 0, twos = 0;
        for(int i = 0; i<n; i++){
            if(nums[i] == 0) zero++;
            else if(nums[i] == 1) ones++;
            else twos++; 
        }
        int i = 0;
        while(zero-- > 0) nums[i++] = 0;
        while(ones-- > 0) nums[i++] = 1;
        while(twos-- > 0) nums[i++] = 2;
    }
}