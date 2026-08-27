class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int bestPivot = -1;
        int bestChar = -1;
        int[] bestFreq = null;

        for (int i = 0; i < n; i++) {

            int curr = target.charAt(i) - 'a';

            // Can we make the answer greater at this position?
            for (int c = curr + 1; c < 26; c++) {

                if (freq[c] > 0) {

                    // This is a valid candidate.
                    // Since we are moving left -> right,
                    // the latest pivot is the best one.
                    bestPivot = i;
                    bestChar = c;
                    bestFreq = freq.clone();

                    break;
                }
            }

            // Match target[i] if possible
            if (freq[curr] == 0) {
                break;
            }

            freq[curr]--;
        }

        // No permutation is greater than target
        if (bestPivot == -1) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        // Prefix remains exactly equal to target
        for (int i = 0; i < bestPivot; i++) {
            ans.append(target.charAt(i));
        }

        // Make the answer greater at bestPivot
        ans.append((char) ('a' + bestChar));
        bestFreq[bestChar]--;

        // Remaining characters in smallest possible order
        for (int c = 0; c < 26; c++) {
            while (bestFreq[c] > 0) {
                ans.append((char) ('a' + c));
                bestFreq[c]--;
            }
        }

        return ans.toString();
    }
}