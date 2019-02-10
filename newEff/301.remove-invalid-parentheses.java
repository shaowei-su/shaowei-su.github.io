/*
 * @lc app=leetcode id=301 lang=java
 *
 * [301] Remove Invalid Parentheses
 *
 * https://leetcode.com/problems/remove-invalid-parentheses/description/
 *
 * algorithms
 * Hard (37.81%)
 * Total Accepted:    105.5K
 * Total Submissions: 277.1K
 * Testcase Example:  '"()())()"'
 *
 * Remove the minimum number of invalid parentheses in order to make the input
 * string valid. Return all possible results.
 * 
 * Note: The input string may contain letters other than the parentheses ( and
 * ).
 * 
 * Example 1:
 * 
 * 
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: ")("
 * Output: [""]
 * 
 */
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        Set<Integer> removed = new HashSet<>();
        dfs(s, res, removed, 0);
        Set<String> dedup = new HashSet<>();
        dedup.addAll(res);
        res.clear();
        res.addAll(dedup);
        return res;
    }
    public void dfs(String s, List<String> res, Set<Integer> removed, int ind) {
        if (isValid(s, removed)) {
            if (res.size() == 0) {
                res.add(delete(s, removed));
                return;
            }
            if (removed.size() == s.length() - res.get(0).length()) {
                res.add(delete(s, removed));
                return;
            } else if (removed.size() < s.length() - res.get(0).length()) {
                res.clear();
                res.add(delete(s, removed));
            }
        } else {
            if (res.size() > 0 && s.length() - res.get(0).length() <= removed.size()) {
                return;
            }
            for (int i = ind; i < s.length(); i++) {
                if (removed.contains(i)) {
                    continue;
                }
                if (s.charAt(i) != ')' && s.charAt(i) != '(') {
                    continue;
                }
                removed.add(i);
                dfs(s, res, removed, i + 1);
                removed.remove(i);
            }
        }
    }

    public boolean isValid(String s, Set<Integer> removed) {
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (removed.contains(i)) {
                continue;
            }
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                if (left == 0) {
                    return false;
                } else {
                    left--;
                }
            }
        }
        return left == 0;
    }

    public String delete(String s, Set<Integer> removed) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (removed.contains(i)) {
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

}
