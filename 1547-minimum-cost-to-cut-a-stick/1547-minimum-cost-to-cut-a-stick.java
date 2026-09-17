class Solution {
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;

        int[] points = new int[m+2];

        points[0] = 0;
        points[m+1] = n;

        for(int i = 0; i<m; i++){
            points[i+1] = cuts[i];
        }

        Arrays.sort(points);

        int[][] dp = new int[m+2][m+2];

        for(int i = m; i>=1; i--){
            for(int j = i; j<=m; j++){
                dp[i][j] = Integer.MAX_VALUE;

                for(int k = i; k<=j; k++){
                    int cost = dp[i][k-1] + dp[k+1][j] + points[j+1] - points[i-1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[1][m];
    }
}