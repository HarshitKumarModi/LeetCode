class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long option1 = (long) need1 * cost1 + (long) need2 * cost2;

        long both = (long) Math.max(need1, need2) * costBoth;

        long mixed = (long) Math.min(need1, need2) * costBoth
                   + (long) Math.abs(need1 - need2) * 
                     (need1 > need2 ? cost1 : cost2);

        return Math.min(option1, Math.min(both, mixed));
    }
}