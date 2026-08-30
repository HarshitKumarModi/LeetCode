class Solution {
    public int numberOfSubstrings(String s) {
        int[] last = {-1,-1,-1};
        int count = 0;

        for(int right = 0; right<s.length(); right++){
            char ch = s.charAt(right);

            last[ch - 'a'] = right;

            int minLast = Math.min(last[0],Math.min(last[1],last[2]));

            count = count + minLast + 1;
        }

        return count;
    }
}