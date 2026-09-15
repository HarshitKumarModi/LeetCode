class Solution {
    public int maxPalindromes(String s, int k) {
        int count = 0;
        int n = s.length();
        int lastEnd = -1; // Tracks the rightmost boundary of the last selected palindrome

        for (int i = 0; i < n; i++) {
            // Check for a valid palindrome of length k
            int l1 = i - k + 1;
            int r1 = i;
            if (l1 > lastEnd && isPalindrome(s, l1, r1)) {
                count++;
                lastEnd = r1;
                continue;
            }

            // Check for a valid palindrome of length k + 1
            int l2 = i - k;
            int r2 = i;
            if (l2 > lastEnd && isPalindrome(s, l2, r2)) {
                count++;
                lastEnd = r2;
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int left, int right) {
        if (left < 0) return false;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}