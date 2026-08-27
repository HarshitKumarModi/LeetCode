class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // bottom up appraoch
        int n = triangle.size();
        int[][] dp = new int[n][n];
        // copy the last row
        for(int j = 0; j<n; j++){
            dp[n-1][j] = triangle.get(n-1).get(j);

        }

        for(int i = n-2; i>=0; i--){
            for(int j = 0; j<=i; j++){
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i+1][j], dp[i+1][j+1]);
            }
        }
        return dp[0][0];
    }
}