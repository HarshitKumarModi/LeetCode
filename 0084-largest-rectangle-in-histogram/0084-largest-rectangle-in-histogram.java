class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] pse = new int[n];
        int[] nse = new int[n];
        Stack<Integer> st = new Stack<>();
        // previous smaller element(traverse from left to right)
        for(int i = 0; i<n; i++){
            while(st.size() > 0 && heights[st.peek()]>=heights[i]){
                st.pop();
            }
            if(st.size() == 0){
                pse[i] = -1;
            }
            else {
                pse[i] = st.peek();
            }
            st.push(i);
        }

        // clear the stack
        while(!st.isEmpty()){
            st.pop();
        }
        // next smaller element(traverse from right to left)
        for(int i = n-1; i>=0; i--){
            while(st.size()>0 && heights[st.peek()] >= heights[i]){
                st.pop();
            }
            if(st.size() == 0){
                nse[i] = n;
            }
            else{
                nse[i] = st.peek();
            }
            st.push(i);
        }

        int maxArea = 0;

        for(int i = 0; i<n; i++){
            int width = nse[i]-pse[i]-1;
            int area = heights[i]*width;

            maxArea = Math.max(maxArea, area);
        }
        return maxArea;

    }
}