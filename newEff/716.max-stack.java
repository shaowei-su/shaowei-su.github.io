/*
 * @lc app=leetcode id=716 lang=java
 *
 * [716] Max Stack
 *
 * https://leetcode.com/problems/max-stack/description/
 *
 * algorithms
 * Easy (38.80%)
 * Total Accepted:    18.9K
 * Total Submissions: 48.1K
 * Testcase Example:  '["MaxStack","push","push","push","top","popMax","top","peekMax","pop","top"]\n[[],[5],[1],[5],[],[],[],[],[],[]]'
 *
 * Design a max stack that supports push, pop, top, peekMax and popMax.
 * 
 * 
 * 
 * push(x) -- Push element x onto stack.
 * pop() -- Remove the element on top of the stack and return it.
 * top() -- Get the element on the top.
 * peekMax() -- Retrieve the maximum element in the stack.
 * popMax() -- Retrieve the maximum element in the stack, and remove it. If you
 * find more than one maximum elements, only remove the top-most one.
 * 
 * 
 * 
 * Example 1:
 * 
 * MaxStack stack = new MaxStack();
 * stack.push(5); 
 * stack.push(1);
 * stack.push(5);
 * stack.top(); -> 5
 * stack.popMax(); -> 5
 * stack.top(); -> 1
 * stack.peekMax(); -> 5
 * stack.pop(); -> 1
 * stack.top(); -> 5
 * 
 * 
 * 
 * Note:
 * 
 * -1e7 
 * Number of operations won't exceed 10000.
 * The last four operations won't be called when stack is empty.
 * 
 * 
 */
class MaxStack {
    int curMax = Integer.MIN_VALUE;
    Deque<Integer> mstack;
    Deque<Integer> stack;
    /** initialize your data structure here. */
    public MaxStack() {
        mstack = new LinkedList<>();
        stack = new LinkedList<>();
    }
    
    public void push(int x) {
       if (x > curMax) {
           curMax = x;
       }
       stack.push(x);
       mstack.push(curMax);
    }
    
    public int pop() {
       int topm = mstack.pop();
       if (topm == curMax) {
           curMax = mstack.size() > 0 ? mstack.peek() : Integer.MIN_VALUE;
       }
       return stack.pop();
    }
    
    public int top() {
       return stack.peek(); 
    }
    
    public int peekMax() {
       return mstack.peek(); 
    }
    
    public int popMax() {
        Deque<Integer> cache = new LinkedList<>();
        while (stack.size() > 0 && stack.peek() != curMax) {
            cache.push(pop());
        }
        if (mstack.size() == 0) {
            return -1;
        }
        int res = pop();
        while (!cache.isEmpty()) {
            push(cache.pop());
        }
        return res;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
