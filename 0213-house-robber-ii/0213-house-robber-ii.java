class Solution {
    public int solve(int[] nums, int start, int end){
        int len = end - start + 1;
        if(len == 1) return nums[start];
        int[] dp = new int[len];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start],nums[start+1]);
        for(int i = 2; i<len; i++){
            dp[i] = Math.max(nums[start+i]+dp[i-2],dp[i-1]);
        }
        return dp[len-1];
    }
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];

        int case1 = solve(nums,0,n-2);
        int case2 = solve(nums,1,n-1);

        return Math.max(case1,case2);
    }
}