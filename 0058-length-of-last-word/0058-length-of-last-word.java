public class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        return s.length() - s.lastIndexOf(' ') - 1;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.lengthOfLastWord("Hello World"));                 
        System.out.println(sol.lengthOfLastWord("   fly me   to   the moon  ")); 
        System.out.println(sol.lengthOfLastWord("luffy is still joyboy"));       
    }
}