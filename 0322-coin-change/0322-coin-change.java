import java.util.*;
public class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);

        dp[0] = 0;

        for(int j = 1; j<=amount; j++){
            for(int i = 0; i<n; i++){
                if(coins[i] <= j){
                    dp[j] = Math.min(dp[j], 1+dp[j-coins[i]]);
                }
            }
        }
        if(dp[amount] == amount+1){
            return -1;
        }
        return dp[amount];
    }
}
