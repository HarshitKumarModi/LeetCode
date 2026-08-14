public class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        int count = 0;
        HashMap<Integer, Integer> ans = new HashMap<>();
        ans.put(0, 1);
        for(int i = 0; i<n; i++){
            sum = sum + nums[i];

            if(ans.containsKey(sum-k)){
                count = count + ans.get(sum-k);
            }
            if(ans.containsKey(sum)){
                ans.put(sum, ans.get(sum) + 1);
            }
            else {
                ans.put(sum, 1);
            }
        }
        return count;

    }
}
