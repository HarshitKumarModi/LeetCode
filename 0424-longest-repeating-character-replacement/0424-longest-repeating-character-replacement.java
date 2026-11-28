public class Solution {
    public int characterReplacement(String s, int k) {
        int n = s.length();

        int left = 0;
        int maxFreq = 0;
        int maxLength = 0;

        int[] freq = new int[26];

        for(int right = 0; right<n; right++){
            int index = s.charAt(right) - 'A';
            freq[index]++;
            maxFreq = Math.max(maxFreq, freq[index]);

            while((right-left+1) - maxFreq > k){
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right-left+1);
        }

        return maxLength;
    }
}
