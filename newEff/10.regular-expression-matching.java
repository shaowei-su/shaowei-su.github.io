/*
 * @lc app=leetcode id=10 lang=java
 *
 * [10] Regular Expression Matching
 *
 * https://leetcode.com/problems/regular-expression-matching/description/
 *
 * algorithms
 * Hard (24.77%)
 * Total Accepted:    281.4K
 * Total Submissions: 1.1M
 * Testcase Example:  '"aa"\n"a"'
 *
 * Given an input string (s) and a pattern (p), implement regular expression
 * matching with support for '.' and '*'.
 * 
 * 
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * 
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * Note:
 * 
 * 
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters
 * like . or *.
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
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the precedeng element, 'a'.
 * Therefore, by repeating 'a' once, it becomes "aa".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * 
 * 
 * Example 4:
 * 
 * 
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore
 * it matches "aab".
 * 
 * 
 * Example 5:
 * 
 * 
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 * 
 * 
 */
class Solution {
    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[pLen + 1][sLen + 1];
        dp[0][0] = true;
        for (int i = 1; i <= pLen; i++) {
            if (i > 1 && p.charAt(i - 1) == '*') {
                dp[i][0] = dp[i - 2][0];
            }
        }
        for (int i = 1; i <= pLen; i++) {
            for (int j = 1; j <= sLen; j++) {
                if (s.charAt(j - 1) == p.charAt(i - 1) || p.charAt(i - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (i >= 2 && p.charAt(i - 1) == '*' && (p.charAt(i - 2) == s.charAt(j - 1) || p.charAt(i - 2) == '.')) {
                    dp[i][j] = (dp[i][j] || dp[i - 1][j] || dp[i][j - 1]); // match one, or more
                }
                if (p.charAt(i - 1) == '*' && i >= 2) {
                    dp[i][j] = dp[i][j] || dp[i - 2][j]; //worst case: match nothing
                }
            }
        }


        return dp[pLen][sLen];



    }
    public boolean isMatch3(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        for (int i = 0; i < pLen; i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true;
            }
        }
        for (int i = 0; i < sLen; i++) {
            for (int j = 0; j < pLen; j++) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    if (j > 0 && (p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.')) {
                        dp[i + 1][j + 1] = dp[i + 1][j] || dp[i + 1][j - 1] || dp[i][j + 1];
                    } else {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    }
                }
            }
        }


        return dp[sLen][pLen];


    }   
    public boolean isMatch2(String s, String p) {
        if (s == null || p == null || s.length() == 0) {
            return false;
        }
        char[] schar = s.toCharArray();
        char[] pchar = p.toCharArray();
        boolean isStar = false;
        int sInd = 0;
        int pInd = 0;
        while (sInd < s.length() && (pInd < p.length() || isStar)) {
            if (pInd < p.length() && schar[sInd] == pchar[pInd]) {
                sInd++;
                pInd++;
                isStar = false;
            } else if (pInd < p.length() && pchar[pInd] == '.') {
                sInd++;
                pInd++;
            } else if (pInd < p.length() && pchar[pInd] == '*') {
                if (pInd == 0) {
                    return false;
                }
                int lastInd = pInd;
                while (lastInd >= 0 && pchar[lastInd] == '*') {
                    lastInd--;
                }
                if (pchar[lastInd] == '.' || pchar[lastInd] == schar[sInd]) {
                    pInd++;
                    sInd++;
                } else {
                    return false;
                }
                isStar = true;
            } else if (isStar) {
                 int lastInd = pInd - 1;
                 while (lastInd >= 0 && pchar[lastInd] == '*') {
                     lastInd--;
                 }   
                 if (lastInd == '.' || pchar[lastInd] == schar[sInd]) {
                     sInd++;
                 } else {
                     return false;
                 } 
            } else if (pInd < p.length() - 1 && pchar[pInd + 1] == '*') {
                pInd = pInd + 2;
            } else  {
                return false;
            }
        }
        if (sInd < s.length() || pInd < p.length()) {
            return false;
        }
        return true;
    }
}
