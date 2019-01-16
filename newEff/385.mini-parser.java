/*
 * @lc app=leetcode id=385 lang=java
 *
 * [385] Mini Parser
 *
 * https://leetcode.com/problems/mini-parser/description/
 *
 * algorithms
 * Medium (31.39%)
 * Total Accepted:    27.1K
 * Total Submissions: 86.5K
 * Testcase Example:  '"324"'
 *
 * Given a nested list of integers represented as a string, implement a parser
 * to deserialize it.
 * 
 * Each element is either an integer, or a list -- whose elements may also be
 * integers or other lists.
 * 
 * Note:
 * You may assume that the string is well-formed:
 * 
 * String is non-empty.
 * String does not contain white spaces.
 * String contains only digits 0-9, [, - ,, ].
 * 
 * 
 * 
 * Example 1:
 * 
 * Given s = "324",
 * 
 * You should return a NestedInteger object which contains a single integer
 * 324.
 * 
 * 
 * 
 * Example 2:
 * 
 * Given s = "[123,[456,[789]]]",
 * 
 * Return a NestedInteger object containing a nested list with 2 elements:
 * 
 * 1. An integer containing value 123.
 * 2. A nested list containing two elements:
 * ⁠   i.  An integer containing value 456.
 * ⁠   ii. A nested list with one element:
 * ⁠        a. An integer containing value 789.
 * 
 * 
 */
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public NestedInteger deserialize(String s) {
    if (s == null) {
        return null;
    }
    Deque<NestedInteger> stack = new LinkedList<>();
   // boolean isNeg = false;
    int sign = 1;
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c == '[') {
            stack.push(new NestedInteger());
        } else if (c == '-') {
            sign = -1;
        } else if (c == ']') {
            if (stack.size() > 1) {
                NestedInteger top = stack.pop();
                stack.peek().add(top);
            }
        } else if (Character.isDigit(c)) {
            int cur = c - '0';
            while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                cur = cur * 10 + (s.charAt(i + 1) - '0');
                i++;
            }
            cur = cur * sign;
            sign = 1;
            if (stack.size() > 0) {
                stack.peek().add(new NestedInteger(cur));
            } else {
                stack.push(new NestedInteger(cur));
            }
        }
    }

    return stack.size() == 0 ? new NestedInteger() : stack.pop();
    }
    public NestedInteger deserialize2(String s) {
        if (s == null) {
            return null;
        }
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }
        int ind = 1;
        int tmp = 0;
        boolean isNeg = false;
        NestedInteger res = new NestedInteger();
        while (ind < s.length()) {
            if (s.charAt(ind) == '-') {
                isNeg = true;
            }
            else if (Character.isDigit(s.charAt(ind))) {
                tmp = tmp * 10 + (s.charAt(ind) - '0');
            } else if (s.charAt(ind) == '[') {
                int start = ind;
                int left = 1;
                while (ind < s.length() && left > 0) {
                    ind++;
                    if (s.charAt(ind) == '[') {
                        left++;
                    } else if (s.charAt(ind) == ']') {
                        left--;
                    }
                }
                NestedInteger cur = deserialize(s.substring(start, ind + 1));
                res.add(cur);
            } else if (s.charAt(ind) == ',') {
                if (s.charAt(ind - 1) == '[' || s.charAt(ind - 1) == ']') {
                    ind++;
                    continue;
                }
                if (isNeg) {
                    res.add(new NestedInteger(-tmp));
                } else {
                    res.add(new NestedInteger(tmp));
                }
                isNeg = false;
                tmp = 0;
            } else if (s.charAt(ind) == ']') {
                 if (s.charAt(ind - 1) == '[' || s.charAt(ind - 1) == ']') {
                     ind++;
                     continue;
                 }
                 if (isNeg) {
                     res.add(new NestedInteger(-tmp));
                 } else {
                     res.add(new NestedInteger(tmp));
                 }
                 isNeg = false;
                 tmp = 0;
            }
            ind++;
        }
        return res;

    }
}
