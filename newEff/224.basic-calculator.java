import java.util.*;

/*
 * @lc app=leetcode id=224 lang=java
 *
 * [224] Basic Calculator
 *
 * https://leetcode.com/problems/basic-calculator/description/
 *
 * algorithms
 * Hard (31.18%)
 * Total Accepted:    94.3K
 * Total Submissions: 298.4K
 * Testcase Example:  '"1 + 1"'
 *
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string may contain open ( and closing parentheses ), the plus
 * + or minus sign -, non-negative integers and empty spaces  .
 * 
 * Example 1:
 * 
 * 
 * Input: "1 + 1"
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: " 2-1 + 2 "
 * Output: 3
 * 
 * Example 3:
 * 
 * 
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * Note:
 * 
 * 
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 * 
 * 
 */
class Solution {
    public int calculate(String s) {
        Deque<Integer> stack = new LinkedList<>();
        int num = 0;
        int pos = 0;
        char sign = '+';
        while (pos < s.length()) {
            char cur = s.charAt(pos);
            if (Character.isDigit(cur)) {
                num = num * 10 + (cur - '0');
            }
            if (cur == '(') {
                int close = findClose(s, pos + 1);
                num = calculate(s.substring(pos + 1, close));
                pos = close;
            }
            if ((!Character.isDigit(cur) && cur != ' ' && cur != '(') || pos == s.length() - 1) {

                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
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

    public int findClose(String s, int start) {
        int count = 1;
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                count--;
            }
            if (count == 0) {
                return i;
            }
        }
        return -1;
    }

    public int calculate3(String s) {

    Deque<Integer> stack = new LinkedList<>();
    int sign = 1;
    int num = 0;
    int res = 0;
    int pos = 0;
    while (pos < s.length()) {
        char cur = s.charAt(pos);
        if (Character.isDigit(cur)) {
            num = num * 10 + (cur - '0');
        } else if (cur == '+') {
            res += sign * num;
            sign = 1;
            num = 0;
        } else if (cur == '-') {
            res += sign * num;
            sign = -1;
            num = 0;
        } else if (cur == '(') {
            stack.push(res);
            stack.push(sign);
            sign = 1;
            num = 0;
            res = 0;
        } else if (cur == ')') {
            res += sign * num;
            num = 0;
            res *= stack.pop();
            res += stack.pop();
        }
        pos++;
    }
    if (num != 0) {
        res += sign * num;
    }

    return res;
    }




    public int calculate2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = s.trim();
        s = s.replaceAll("\\s+", "");
        Deque<String> stack = new LinkedList<>();
        int res = 0;

        int pos = 0;
        int temp = 0;
        boolean set = false;
        while (pos < s.length()) {
            char cur = s.charAt(pos);
            switch (cur) {
                case '+':
                case '-':
                case '(':
                    if (set) {
                        stack.push(String.valueOf(temp));
                        temp = 0;
                        set = false;
                    }
                    stack.push(String.valueOf(cur));
                    break;
                case ')':
                    stack.push(String.valueOf(temp));
                    temp = 0;
                    set = false;
                    int val = compute(stack);
                    stack.push(String.valueOf(val));
                    break;
                default:
                    temp = temp * 10 + (cur - '0');
                    set = true;
                    break;
            }
            pos++;
        }
        if (set) {
            stack.push(String.valueOf(temp));
        }
        return compute(stack);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.calculate("(1 + (4 + 5 + 2) - 3) + (6 + 8)");
    }

    public int compute(Deque<String> stack) {
        int res = 0;
        while (stack.size() > 0 && !stack.peek().equals("(")) {
            int val = Integer.parseInt(stack.pop());
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
        if (stack.size() > 0 && stack.peek().equals("(")) stack.pop();
        return res;
    }
}
