class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;

        int[] dp = new int[n+1];

        for(int i = n-1; i>=0; i--){
            int max = 0;
            for(int j = i; j<n && j < i+k; j++){
                max = Math.max(max, arr[j]);
                int length = j-i+1;
                int sum = max * length + dp[j+1];
                dp[i] = Math.max(dp[i], sum);
            }
        }
        return dp[0];
    }
}