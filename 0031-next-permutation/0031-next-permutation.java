class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int index = -1;
        // find the breakpoint
        for(int i = n-2; i>=0; i--){
            if(nums[i] < nums[i+1]){
                index = i;
                break;
            }
        }
        // if there is no breakpoint then reverse the entire array
        if(index == -1){
            reverse(nums,0,n-1);
            return;
        }
        // find the next larger element
        for(int i = n-1; i>index; i--){
            if(nums[i] > nums[index]){
                swap(nums,i,index);
                break;
            }
        }
        // reverse the entire array
        reverse(nums,index+1,n-1);
    }
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void reverse(int[] nums, int left, int right){
        while(left < right){
            swap(nums,left,right);
            left++;
            right--;
        }
    }
}