import java.util.*;

class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int totalOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') totalOnes++;
        }

        String t = "1" + s + "1";

        List<Character> chars = new ArrayList<>();
        List<Integer> lens = new ArrayList<>();

        int i = 0;
        while (i < t.length()) {
            char ch = t.charAt(i);
            int j = i;
            while (j < t.length() && t.charAt(j) == ch) {
                j++;
            }
            chars.add(ch);
            lens.add(j - i);
            i = j;
        }

        int ans = totalOnes;

        for (int k = 1; k < chars.size() - 1; k++) {
            if (chars.get(k) == '1'
                    && chars.get(k - 1) == '0'
                    && chars.get(k + 1) == '0') {

                int removedOnes = lens.get(k);
                int mergedZero = lens.get(k - 1) + removedOnes + lens.get(k + 1);

                int candidate = totalOnes - removedOnes + mergedZero;
                ans = Math.max(ans, candidate);
            }
        }

        return Math.min(ans, s.length());
    }
}