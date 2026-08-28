class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int num : nums){
            sum += num;
        }

        if(sum % 2 != 0){
            return false;
        }

        int target = sum / 2;

        boolean[][] dp = new boolean[n+1][target+1];

        // sum 0 can always be formed
        for(int i = 0; i<=n; i++){
            dp[i][0] = true;
        }

        // fill the dp
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=target; j++){
                // dont take current element
                dp[i][j] = dp[i-1][j];

                //take current element if it fits
                if(nums[i-1] <= j){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[n][target];
    }
}