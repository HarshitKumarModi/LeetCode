class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        if(n<=1){
            return 0;
        }

        int[] hold = new int[n];
        int[] sold = new int[n];
        int[] cooldown = new int[n];

        hold[0] = -prices[0];
        sold[0] = 0;
        cooldown[0] = 0;

        for(int i = 1; i<n; i++){
            hold[i] = Math.max(hold[i-1], cooldown[i-1] - prices[i]);

            sold[i] = hold[i-1] + prices[i];

            cooldown[i] = Math.max(cooldown[i-1], sold[i-1]);
        }

        return Math.max(sold[n-1], cooldown[n-1]);
    }
}