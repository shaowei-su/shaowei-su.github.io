/*
 * @lc app=leetcode id=44 lang=java
 *
 * [44] Wildcard Matching
 *
 * https://leetcode.com/problems/wildcard-matching/description/
 *
 * algorithms
 * Hard (22.15%)
 * Total Accepted:    163.8K
 * Total Submissions: 731.4K
 * Testcase Example:  '"aa"\n"a"'
 *
 * Given an input string (s) and a pattern (p), implement wildcard pattern
 * matching with support for '?' and '*'.
 * 
 * 
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * 
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * Note:
 * 
 * 
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters
 * like ? or *.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * s = "aa"
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not
 * match 'b'.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence, while the second '*'
 * matches the substring "dce".
 * 
 * 
 * Example 5:
 * 
 * 
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 * 
 * 
 */
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        if (s.length() == p.length() && s.length() == 0) {
            return true;
        }
        if (p.equals("*")) {
            return true;
        }
        p = p.replaceAll("\\*+", "*");
        int si = 0;
        int pi = 0;
        char[] schar = s.toCharArray();
        char[] pchar = p.toCharArray();
        while (si < schar.length && pi < pchar.length) {
            if (schar[si] == pchar[pi]) {
                si++;
                pi++;
            } else if (pchar[pi] == '?') {
                si++;
                pi++;
            } else if (pchar[pi] == '*') {
                if (pi == p.length() - 1) {
                    return true;
                }
                char next = pchar[pi + 1];

                for (int i = si; i < schar.length; i++) {
                    if ((schar[i] == next || next == '?') && isMatch(s.substring(i), p.substring(pi + 1))) {
                        return true;
                    }
                }
                return false;
            } else {
                return false;
            }
        
        }

        if (pi < pchar.length && p.substring(pi).equals("*")) {
            return true;
        }

        if (si < schar.length || pi < pchar.length) {
            return false;
        }

        return true;

    }
}
