class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for(int num : nums){
            sum += num;
        }

        int subsetSum = (sum+target) / 2;

        if(Math.abs(target) > sum){
            return 0;
        }
        if((sum+target) % 2 != 0){
            return 0;
        }

        int[] dp = new int[subsetSum + 1];

        dp[0] = 1;

        for(int i = 0; i<n; i++){
            for(int j = subsetSum; j>=nums[i]; j--){
                dp[j] = dp[j] + dp[j-nums[i]];
            }
        }
        return dp[subsetSum];
    }
}