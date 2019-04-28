/*
 * @lc app=leetcode id=772 lang=java
 *
 * [772] Basic Calculator III
 *
 * https://leetcode.com/problems/basic-calculator-iii/description/
 *
 * algorithms
 * Hard (43.84%)
 * Total Accepted:    14.4K
 * Total Submissions: 32.8K
 * Testcase Example:  '"1 + 1"'
 *
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string may contain open ( and closing parentheses ), the plus
 * + or minus sign -, non-negative integers and empty spaces  .
 * 
 * The expression string contains only non-negative integers, +, -, *, /
 * operators , open ( and closing parentheses ) and empty spaces  . The integer
 * division should truncate toward zero.
 * 
 * You may assume that the given expression is always valid. All intermediate
 * results will be in the range of [-2147483648, 2147483647].
 * 
 * Some examples:
 * 
 * 
 * "1 + 1" = 2
 * " 6-4 / 2 " = 4
 * "2*(5+5*2)/3+(6/2+8)" = 21
 * "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
 * 
 * 
 * 
 * 
 * Note: Do not use the eval built-in library function.
 * 
 */
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int pos = 0;
        int num = 0;
        Deque<Integer> stack = new LinkedList<>();
        char sign = '+';
        while (pos < s.length()) {
            char cur = s.charAt(pos);
            if (Character.isDigit(cur)) {
                num = num * 10 + (cur - '0');
            }
            if (cur == '(') {
                int next = findNext(s, pos);
                num = calculate(s.substring(pos + 1, next));
                pos = next;
            }
            if ((!Character.isDigit(cur) && cur != '(' && cur != ' ') || pos == s.length() - 1) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                sign = cur;
                num = 0;
            }
            pos++;
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
    public int findNext(String s, int pos) {
        int count = 1;
        for (int i = pos + 1; i < s.length(); i++) {
           if (s.charAt(i) == ')') {
               count--;
           } else if (s.charAt(i) == '(') {
               count++;
           }
           if (count == 0) {
               return i;
           }
        }
        return -1;
    }
}
