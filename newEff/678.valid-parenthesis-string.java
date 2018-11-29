/*
 * [678] Valid Parenthesis String
 *
 * https://leetcode.com/problems/valid-parenthesis-string/description/
 *
 * algorithms
 * Medium (30.83%)
 * Total Accepted:    18.9K
 * Total Submissions: 61.5K
 * Testcase Example:  '"()"'
 *
 * 
 * Given a string containing only three types of characters: '(', ')' and '*',
 * write a function to check whether this string is valid. We define the
 * validity of a string by these rules:
 * 
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left
 * parenthesis '(' or an empty string.
 * An empty string is also valid.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: "()"
 * Output: True
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "(*)"
 * Output: True
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: "(*))"
 * Output: True
 * 
 * 
 * 
 * Note:
 * 
 * The string size will be in the range [1, 100].
 * 
 * 
 */
class Solution {
    public boolean checkValidString(String s) {
         if (s == null || s.length() == 0) {
             return true;
         }
         return helper(s, 0, 0);
    }
    public boolean helper(String s, int ind, int pCount) {
        if (pCount < 0) {
            return false;
        }
        for (int i = ind; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                return helper(s, i + 1, pCount) || helper(s, i + 1, pCount + 1) || helper(s, i + 1, pCount - 1);
            } else if (s.charAt(i) == '(') {
                pCount++;
            } else {
                if (pCount <= 0) {
                    return false;
                }
                pCount--;
            }
        }
    
        return pCount == 0;
    
    }



    public boolean checkValidString2(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        List<String> targets = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(targets, sb, s, 0);
        for (String target : targets) {
            if (isValid(target)) {
                return true;
            }
        }
        return false;
    }
    public void dfs(List<String> targets, StringBuilder sb, String s, int pos) {
        if (pos == s.length()) {
            targets.add(sb.toString());
            return;
        }
        if (s.charAt(sb.length()) == '*') {
            sb.append('(');
            dfs(targets, sb, s, pos + 1);
            sb.setLength(sb.length() - 1);
            sb.append(')');
            dfs(targets, sb, s, pos + 1);
            sb.setLength(sb.length() - 1);
            dfs(targets, sb, s, pos + 1);
        } else {
            sb.append(s.charAt(sb.length()));
            dfs(targets, sb, s, pos + 1);
            sb.setLength(sb.length() - 1);
        }
    }
    public boolean isValid(String s) {
        char[] arr = s.toCharArray();
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            if (stack.size() == 0) {
                stack.push(arr[i]);
            }
            else if (arr[i] == ')' && stack.peek() == '(') {
                stack.pop();
            } else {
                stack.push(arr[i]);
            }
        }
        return stack.size() == 0;
    }
}
