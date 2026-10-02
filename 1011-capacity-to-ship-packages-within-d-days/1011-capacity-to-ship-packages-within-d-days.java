class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = 0;

        for(int weight : weights){
            left = Math.max(left, weight);
        }

        for(int weight : weights){
            right = right + weight;
        }

        while(left <= right){
            
            int mid = left + (right - left) / 2;

            int minDays = 1;
            int currentWeight = 0;

            for(int weight : weights){
                if(currentWeight + weight <= mid){
                    currentWeight += weight;
                } else {
                    minDays++;
                    currentWeight = weight;
                }
            }

            if(minDays <= days){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}