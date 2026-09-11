import java.util.*;

class Solution {
    public int totalNumbers(int[] digits) {
        boolean[] used = new boolean[digits.length];
        Set<Integer> set = new HashSet<>();

        backtrack(digits, used, 0, 0, set);

        return set.size();
    }

    private void backtrack(int[] digits, boolean[] used,
                           int count, int num, Set<Integer> set) {

        if (count == 3) {
            if (num % 2 == 0) {
                set.add(num);
            }
            return;
        }

        for (int i = 0; i < digits.length; i++) {

            if (used[i])
                continue;

            // No leading zero
            if (count == 0 && digits[i] == 0)
                continue;

            used[i] = true;

            backtrack(
                digits,
                used,
                count + 1,
                num * 10 + digits[i],
                set
            );

            used[i] = false;
        }
    }
}