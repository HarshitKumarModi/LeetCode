class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        List<String> ans = new ArrayList<>();

        int n = s.length();

        // Store first and last occurrence of every character
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';

            if (first[ch] == -1) {
                first[ch] = i;
            }

            last[ch] = i;
        }

        int prevEnd = -1;

        // Check possible substring starting from each character's first occurrence
        for (int i = 0; i < n; i++) {

            int ch = s.charAt(i) - 'a';

            // We only start from the first occurrence
            if (first[ch] != i) {
                continue;
            }

            int end = last[ch];
            boolean valid = true;

            // Expand the substring
            for (int j = i; j <= end; j++) {

                int current = s.charAt(j) - 'a';

                // This character appeared before i,
                // so we cannot make a valid substring starting at i.
                if (first[current] < i) {
                    valid = false;
                    break;
                }

                // Include all occurrences of this character
                end = Math.max(end, last[current]);
            }

            if (valid) {

                // If it doesn't overlap with previous substring
                if (i > prevEnd) {
                    ans.add(s.substring(i, end + 1));
                    prevEnd = end;
                }

                // Current interval ends earlier,
                // so replace the previous one.
                else {
                    ans.set(ans.size() - 1, s.substring(i, end + 1));
                    prevEnd = end;
                }
            }
        }

        return ans;
    }
}