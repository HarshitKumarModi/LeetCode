class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int num : nums){
            if(num < min){
                min = num;
            } 
            if(num > max){
                max = num;
            }
        }

        for(int i = min; i<=max; i++){
            boolean found  = false;

            for(int j = 0; j<nums.length; j++){
                if(nums[j] == i){
                    found = true;
                    break;
                }
            }
            if(!found){
                    ans.add(i);
            }
        }
        return ans;
    }
}