class Solution {
    public int solve(int start, int end, int[] nums){
        int len = end-start+1;
        if(len == 1) return nums[start];
        int[] dp = new int[end-start+1];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start+1]);
        for(int i = 2; i<len; i++){
            dp[i] = Math.max(dp[i-1], nums[start+i]+dp[i-2]);
        }
        return dp[len-1];
    }
    public int rob(int[] nums){
        int n = nums.length;
        if(n == 1) return nums[0];
        int case1 = solve(0, n-2, nums);
        int case2 = solve(1, n-1, nums);

        return Math.max(case1, case2);
    }
}