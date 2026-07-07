import java.util.*;

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int target, int idx, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            if (nums[i] <= target) {
                path.add(nums[i]);
                backtrack(nums, target - nums[i], i, path, res);
                path.remove(path.size() - 1);
            }
        }
    }
}