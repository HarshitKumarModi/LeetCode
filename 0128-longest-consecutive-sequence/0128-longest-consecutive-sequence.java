import java.util.HashSet;

public class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> st = new HashSet<>();
        for(int num : nums) st.add(num);
        int maxStreak = 0;

        for(int num : st){
            if(!st.contains(num - 1)){
                int currentNum = num;
                int currentStreak = 1;
                while(st.contains(currentNum + 1)){
                    currentStreak++;
                    currentNum++;
                }
                maxStreak = Math.max(maxStreak, currentStreak);
            }
        }
        return maxStreak;
    }
}
