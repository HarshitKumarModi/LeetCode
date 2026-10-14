class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int left = 1;
        int right = 0;

        for(int i = 0; i<n; i++){
            right = Math.max(right, piles[i]);
        }

        while(left <= right){
            int mid = left + (right - left) / 2;
            long hours = 0;
            for(int pile : piles){
                hours = hours + (pile + mid - 1) / mid;
            }

            if(hours <= h){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;

    }
}