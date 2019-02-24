/*
 * @lc app=leetcode id=227 lang=java
 *
 * [227] Basic Calculator II
 *
 * https://leetcode.com/problems/basic-calculator-ii/description/
 *
 * algorithms
 * Medium (32.23%)
 * Total Accepted:    95.9K
 * Total Submissions: 295.1K
 * Testcase Example:  '"3+2*2"'
 *
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string contains only non-negative integers, +, -, *, /
 * operators and empty spaces  . The integer division should truncate toward
 * zero.
 * 
 * Example 1:
 * 
 * 
 * Input: "3+2*2"
 * Output: 7
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: " 3/2 "
 * Output: 1
 * 
 * Example 3:
 * 
 * 
 * Input: " 3+5 / 2 "
 * Output: 5
 * 
 * 
 * Note:
 * 
 * 
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 * 
 */
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = s.replaceAll("\\s+", "");
        Deque<String> stack = new LinkedList<>();
        int pos = 0;
        int temp = 0;
        boolean set = false;
        int next = 0;
        int val = 0;
        int prev = 0;
        while (pos < s.length()) {
            char cur = s.charAt(pos);
            switch (cur) {
                case '+':
                case '-':
                    if (set) {
                        set = false;
                        stack.push(String.valueOf(temp));
                        temp = 0;
                    }
                    stack.push(String.valueOf(cur));
                    break;
                case '*':
                    next = 0;
                    pos++;
                    while (pos < s.length() && Character.isDigit(s.charAt(pos))) {
                        next = next * 10 + (s.charAt(pos) - '0');
                        pos++;
                    }
                    pos--;
                    prev = 0;
                    if (set) {
                        prev = temp;
                        set = false;
                        temp = 0;
                    } else {
                        prev = Integer.parseInt(stack.pop());
                    }
                    val = (int) prev * next;
                    stack.push(String.valueOf(val));
                    break;
                case '/':
                     next = 0;
                     pos++;
                     while (pos < s.length() && Character.isDigit(s.charAt(pos))) {
                         next = next * 10 + (s.charAt(pos) - '0');
                         pos++;
                     }   
                     pos--;
                     prev = 0;
                     if (set) {
                         prev = temp;
                         set = false;
                         temp = 0;
                     } else {
                         prev = Integer.parseInt(stack.pop());
                     }   
                     val = (int) prev / next;
                     stack.push(String.valueOf(val));
                     set = false;
                     break;
                default:
                     set = true;
                     temp = temp * 10 + (cur - '0');
            }
            pos++;
        }
        if (set) {
            stack.push(String.valueOf(temp));
        }
        int res = 0;
        while (stack.size() > 0) {
            val = Integer.parseInt(stack.pop());
            if (stack.size() > 0) {
                String top = stack.peek();
                if (top.equals("+")) {
                    stack.pop();
                } else if (top.equals("-")) {
                    val = -val;
                    stack.pop();
                }
            }
            res += val;
        }
        return res;
    }
}
