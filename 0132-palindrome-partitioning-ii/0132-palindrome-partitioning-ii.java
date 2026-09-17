class Solution {
    public int minCut(String s) {
        int n = s.length();

        int[] dp = new int[n+1];

        dp[n] = -1;

        for(int i = n-1; i>=0; i--){
            dp[i] = Integer.MAX_VALUE;
            for(int j = i; j<n; j++){
                if (isPallindrome(s, i, j)){
                    dp[i] = Math.min(dp[i], 1+dp[j+1]);
                }
            }
        }
        return dp[0];
    }

    public boolean isPallindrome(String s, int i, int j){
        while(i<j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}