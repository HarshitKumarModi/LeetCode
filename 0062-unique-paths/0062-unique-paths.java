import java.util.*;
class Solution {
    public int ways(int sr, int sc, int er, int ec, int[][] dp){
        if(sr > er || sc > ec){
            return 0;
        }
        if(sr == er && sc == ec){
            return 1;
        }
        if(dp[sr][sc]!= -1){
            return dp[sr][sc];
        }
        int rightways = ways(sr,sc+1,er,ec,dp);
        int downways = ways(sr+1,sc,er,ec,dp);

        dp[sr][sc] = rightways + downways;
        return dp[sr][sc];

    }
    public int uniquePaths(int m, int n){
        int[][] dp = new int[m][n];
        for(int i = 0; i<m; i++){
            Arrays.fill(dp[i],-1);
        }
        return ways(0,0,m-1,n-1,dp);
    }
}
