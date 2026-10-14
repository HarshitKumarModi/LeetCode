class Solution{
    public int subarraySum(int[] nums, int k){
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        map.put(0, 1);

        for(int i = 0; i<n; i++){
            sum = sum + nums[i];
            if(map.containsKey(sum - k)){
                count = count + map.get(sum - k);
            }
            if(map.containsKey(sum)){
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1);
            }
        }

        return count;
    }
}