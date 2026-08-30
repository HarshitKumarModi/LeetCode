class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atmost(nums,k) - atmost(nums,k-1);
    }

    public int atmost(int[] nums , int k){
        if(k == 0){
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int count = 0;

        for(int right = 0; right<nums.length; right++){
            if(map.containsKey(nums[right])){
                map.put(nums[right], map.get(nums[right]) + 1);
            } else {
                map.put(nums[right], 1);
            }

            while(map.size() > k){
                map.put(nums[left], map.get(nums[left]) - 1);

                if(map.get(nums[left]) == 0){
                    map.remove(nums[left]);
                }

                left++;
            }
            count = (count + (right-left+1));
        }
        return count;
    }
}