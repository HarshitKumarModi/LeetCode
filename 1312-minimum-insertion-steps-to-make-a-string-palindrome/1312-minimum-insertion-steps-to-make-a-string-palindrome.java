class Solution {
    public int minInsertions(String s) {
        int m = s.length();

        String t = new StringBuilder(s).reverse().toString();

        int n = t.length();

        int[][] dp = new int[m+1][n+1];

        for(int i = m-1; i>=0; i--){
            for(int j = n-1; j>=0; j--){
                if(s.charAt(i) == t.charAt(j)){
                    dp[i][j] = 1 + dp[i+1][j+1];
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }
        int lps = dp[0][0];
        return m - lps;
    }
}