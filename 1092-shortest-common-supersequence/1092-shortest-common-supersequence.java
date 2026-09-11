class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m+1][n+1];

        for(int i = m-1; i>=0; i--){
            for(int j = n-1; j>=0; j--){
                if(str1.charAt(i) == str2.charAt(j)){
                    dp[i][j] = 1 + dp[i+1][j+1];
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        
        int i = 0;
        int j = 0;
        
        while(i<m && j<n){
            if(str1.charAt(i) == str2.charAt(j)){
                ans.append(str1.charAt(i));
                i++;
                j++;
            } else if(dp[i+1][j] >= dp[i][j+1]){
                ans.append(str1.charAt(i));
                i++;
            } else {
                ans.append(str2.charAt(j));
                j++;
            }
        }
        while(i<m){
            ans.append(str1.charAt(i));
            i++;
        }
        while(j<n){
            ans.append(str2.charAt(j));
            j++;
        }

        return ans.toString();
    }
}