class MinStack {
    Stack<Integer> st = new Stack<>();
    Stack<Integer> min = new Stack<>(); // extra stack s.c= O(n)
    public MinStack() {
        // constructor
    }
    
    public void push(int value) {
        if(st.size() == 0){
            st.push(value);
            min.push(value);
        } else {
            st.push(value);
            if(min.peek() < value) min.push(min.peek());
            else min.push(value);
        }
    }
    
    public void pop() {
        st.pop();
        min.pop();
    }
    
    public int top() {
        return st.peek();
    }
    
    public int getMin() {
        return min.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */