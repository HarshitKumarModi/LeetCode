
import java.util.*;

class Solution {
    public int maxSelectedElements(int[] nums) {

        Arrays.sort(nums);

        HashMap<Integer, Integer> dp = new HashMap<>();

        int ans = 0;

        for (int x : nums) {

            int same = dp.getOrDefault(x, 0);
            int prev = dp.getOrDefault(x - 1, 0);

            // Option 1: Keep x unchanged
            dp.put(x, Math.max(same, prev + 1));

            // Option 2: Increase x by 1
            dp.put(x + 1, Math.max(
                dp.getOrDefault(x + 1, 0),
                same + 1
            ));

            ans = Math.max(ans, Math.max(
                dp.get(x),
                dp.get(x + 1)
            ));
        }

        return ans;
    }
}
