class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;

        int hold = -prices[0];
        int cash = 0;

        for(int i = 1; i<n; i++){
            int oldHold = hold;
            int oldCash = cash;

            hold = Math.max(hold, cash-prices[i]);

            cash = Math.max(cash, hold+prices[i] - fee);
        }

        return cash;
    }
}