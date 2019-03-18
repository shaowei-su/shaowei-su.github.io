import java.util.*;

/*
 * @lc app=leetcode id=32 lang=java
 *
 * [32] Longest Valid Parentheses
 *
 * https://leetcode.com/problems/longest-valid-parentheses/description/
 *
 * algorithms
 * Hard (24.61%)
 * Total Accepted:    175.2K
 * Total Submissions: 701.1K
 * Testcase Example:  '"(()"'
 *
 * Given a string containing just the characters '(' and ')', find the length
 * of the longest valid (well-formed) parentheses substring.
 * 
 * Example 1:
 * 
 * 
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 * 
 * 
 */
class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len];
        dp[0] = 0;
        int global = 0;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = i - 2 > 0 ? dp[i - 2] + 2 : 2;
                    global = Math.max(global, dp[i]);
                } else {
                    if (dp[i - 1] > 0 && i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        int prev = (i - dp[i - 1] - 1 > 0) ? dp[i - dp[i - 1] - 2] : 0;
                        dp[i] = dp[i - 1] + 2 + prev;
                        global = Math.max(global, dp[i]);
                    }
                }
            }
        }

        return global;


    }
    public int longestValidParentheses2(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        Deque<Integer> stack = new LinkedList<>();
        int global = 0;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                    stack.pop();
                    if (stack.size() == 0) {
                        stack.push(i);
                    }
                    else {
                        global = Math.max(global, i - stack.peek()); 
                    }
            }
        }
        return global;

    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        int res = sol.longestValidParentheses("((()))())");
            System.out.println(res);
    }
}
