// // using recursion
// class Solution {
//     public int fib(int n) {
//         if(n<=1){
//            return n;
//         }
//        return fib(n-1) + fib(n-2);
//     }
// }
// // using simple for loops
// class Solution{
//     public int fib(int n){
//         if(n<=1){
//             return n;
//         }
//         int a = 0;
//         int b = 1;
//         for(int i = 2; i<=n; i++){
//             int c = a + b;
//             a = b;
//             b = c;
//         }
//         return b;
//     }
// }
// using dp
class Solution{
    public int helper(int n, int[] dp){
        if(n<=1) return n;
        if(dp[n]!=0) return dp[n];
        int ans = helper(n-1,dp) + helper(n-2, dp);
        dp[n] = ans;
        return ans;
    }
    public int fib(int n){
        int[] dp = new int[n+1];
        return helper(n, dp);
    }
}