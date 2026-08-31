public class Solution {
    public String minWindow(String s, String t) {
        if(s.length() < t.length()){
            return "";
        }
        HashMap<Character, Integer> map = new HashMap<>();

        for(int i = 0; i<t.length(); i++){
            char ch = t.charAt(i);

            if(map.containsKey(ch)){
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        int left = 0;
        int minLength = Integer.MAX_VALUE;
        int start = 0;
        int count = 0;

        for(int right = 0; right<s.length(); right++){
            char ch = s.charAt(right);

            if(map.containsKey(ch)){
                if(map.get(ch) > 0){
                    count++;
                }
                map.put(ch, map.get(ch) - 1);
            }

            while(count == t.length()){
                if(right-left+1 < minLength){
                    minLength = right-left+1;
                    start = left;
                }

                char leftchar = s.charAt(left);

                if(map.containsKey(leftchar)){
                    map.put(leftchar, map.get(leftchar) + 1);

                    if(map.get(leftchar) > 0){
                        count--;
                    }
                }
                left++;
            }
        }

        if(minLength == Integer.MAX_VALUE){
            return "";
        }

        return s.substring(start, start+minLength);
    }
}
