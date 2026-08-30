public class Solution {
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];

        int left = 0;
        int maxFrequency = 0;
        int maxLength = 0;

        for(int right = 0; right<s.length(); right++){
            int index = s.charAt(right) - 'A';
            freq[index]++;

            maxFrequency = Math.max(maxFrequency, freq[index]);

            while(((right-left+1) - maxFrequency) > k){
            freq[s.charAt(left)-'A']--;
            left++;
            }
            maxLength = Math.max(maxLength, right-left+1);
        }
        return maxLength;
    }
}
