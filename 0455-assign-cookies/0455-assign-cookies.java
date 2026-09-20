class Solution {
    public int findContentChildren(int[] g, int[] s) {
       int n = g.length;
       int m = s.length;

       Arrays.sort(g);
       Arrays.sort(s);

       int i = 0; int j = 0;

       while(i<g.length && j<s.length){
           if(s[j] >= g[i]){
               i++;
               j++;
            } else {
                j++;
            }
       }
       return i;
    }
}