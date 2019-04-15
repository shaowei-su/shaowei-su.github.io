/*
 * @lc app=leetcode id=22 lang=java
 *
 * [22] Generate Parentheses
 *
 * https://leetcode.com/problems/generate-parentheses/description/
 *
 * algorithms
 * Medium (52.21%)
 * Total Accepted:    306.4K
 * Total Submissions: 574K
 * Testcase Example:  '3'
 *
 * 
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * 
 * 
 * 
 * For example, given n = 3, a solution set is:
 * 
 * 
 * [
 * ⁠ "((()))",
 * ⁠ "(()())",
 * ⁠ "(())()",
 * ⁠ "()(())",
 * ⁠ "()()()"
 * ]
 * 
 */
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n < 1) {
            return res;
        }

        dfs2(res, 0, 0, new StringBuilder(), n);

        return res;
    }

    public void dfs2(List<String> res, int leftCount, int totalLeftCount, StringBuilder sb, int n) {
        if (sb.length() == n * 2) {
            res.add(sb.toString());
            return;
        }
        if (leftCount == 0) {
            sb.append("(");
            dfs2(res, 1, totalLeftCount + 1, sb, n);
            sb.setLength(sb.length() - 1);
        } else if (totalLeftCount < n) {
            sb.append("(");
            dfs2(res, leftCount + 1, totalLeftCount + 1, sb, n);
            sb.setLength(sb.length() - 1);

            sb.append(")");
            dfs2(res, leftCount - 1, totalLeftCount, sb, n);
            sb.setLength(sb.length() - 1);
        } else {
            sb.append(")");
            dfs2(res, leftCount - 1, totalLeftCount, sb, n);
            sb.setLength(sb.length() - 1);
        }
    }


    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        dfs(res, new StringBuilder(), 0, 0, n);
        return res;
    }
    public void dfs(List<String> res, StringBuilder sb, int curLeft, int totalLeft, int n) {
        if (sb.length() == 2 * n) {
            res.add(sb.toString());
            return;
        }
        if (curLeft > 0) {
            sb.append(")");
            dfs(res, sb, curLeft - 1, totalLeft, n);
            sb.setLength(sb.length() - 1);
        } 
        if (totalLeft < n) {
            sb.append("(");
            dfs(res, sb, curLeft + 1, totalLeft + 1, n);
            sb.setLength(sb.length() - 1);
        }
    }
        
}
