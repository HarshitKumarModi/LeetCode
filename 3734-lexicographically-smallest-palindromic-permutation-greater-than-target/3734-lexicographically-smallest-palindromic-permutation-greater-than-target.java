class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();

        int[] freq = new int[26];

        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // Check if palindrome is possible
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        int halfLen = n / 2;

        int[] halfFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        // n = 1
        if (halfLen == 0) {

            String ans = "" + middle;

            if (ans.compareTo(target) > 0) {
                return ans;
            }

            return "";
        }

        /*
         * Build the smallest half that is
         * lexicographically greater than target's half.
         */
        char[] half = new char[halfLen];

        int[] remaining = halfFreq.clone();

        int i = 0;

        while (i < halfLen) {

            int targetChar = target.charAt(i) - 'a';

            // Try to keep the same character
            if (remaining[targetChar] > 0) {

                half[i] = target.charAt(i);
                remaining[targetChar]--;
                i++;

            } else {

                /*
                 * We cannot use target[i].
                 * Try to put the smallest character
                 * greater than target[i].
                 */
                int bigger = -1;

                for (int c = targetChar + 1; c < 26; c++) {

                    if (remaining[c] > 0) {
                        bigger = c;
                        break;
                    }
                }

                if (bigger != -1) {

                    half[i] = (char) ('a' + bigger);
                    remaining[bigger]--;

                    // Fill the rest with smallest characters
                    int pos = i + 1;

                    for (int c = 0; c < 26; c++) {

                        while (remaining[c] > 0) {
                            half[pos] = (char) ('a' + c);
                            pos++;
                            remaining[c]--;
                        }
                    }

                    return makePalindrome(half, middle);
                }

                /*
                 * No bigger character at this position.
                 * We have to go backwards.
                 */
                break;
            }
        }

        /*
         * We matched the complete first half.
         */
        if (i == halfLen) {

            String palindrome = makePalindrome(half, middle);

            if (palindrome.compareTo(target) > 0) {
                return palindrome;
            }

            // Need the next permutation
            if (nextPermutation(half)) {
                return makePalindrome(half, middle);
            }

            return "";
        }

        /*
         * Backtrack to an earlier position.
         */
        for (int pos = i - 1; pos >= 0; pos--) {

            // Put the current character back
            int oldChar = half[pos] - 'a';
            remaining[oldChar]++;

            int targetChar = target.charAt(pos) - 'a';

            int bigger = -1;

            // Find smallest character > target[pos]
            for (int c = targetChar + 1; c < 26; c++) {

                if (remaining[c] > 0) {
                    bigger = c;
                    break;
                }
            }

            if (bigger != -1) {

                half[pos] = (char) ('a' + bigger);
                remaining[bigger]--;

                // Fill everything after pos with smallest chars
                int index = pos + 1;

                for (int c = 0; c < 26; c++) {

                    while (remaining[c] > 0) {
                        half[index] = (char) ('a' + c);
                        index++;
                        remaining[c]--;
                    }
                }

                return makePalindrome(half, middle);
            }
        }

        return "";
    }


    private String makePalindrome(char[] half, char middle) {

        StringBuilder sb = new StringBuilder();

        // First half
        for (char c : half) {
            sb.append(c);
        }

        // Middle character
        if (middle != 0) {
            sb.append(middle);
        }

        // Reverse half
        for (int i = half.length - 1; i >= 0; i--) {
            sb.append(half[i]);
        }

        return sb.toString();
    }


    private boolean nextPermutation(char[] arr) {

        int i = arr.length - 2;

        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }

        if (i < 0) {
            return false;
        }

        int j = arr.length - 1;

        while (arr[j] <= arr[i]) {
            j--;
        }

        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        int left = i + 1;
        int right = arr.length - 1;

        while (left < right) {

            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }

        return true;
    }
}