/*
 * [5] Longest Palindromic Substring
 *
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 *
 * algorithms
 * Medium (25.63%)
 * Total Accepted:    397.4K
 * Total Submissions: 1.5M
 * Testcase Example:  '"babad"'
 *
 * Given a string s, find the longest palindromic substring in s. You may
 * assume that the maximum length of s is 1000.
 * 
 * Example 1:
 * 
 * 
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "cbbd"
 * Output: "bb"
 * 
 * 
 */
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int maxLen = 0;
        String res = "";
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
            if (maxLen == 0) {
                maxLen = 1;
                res = s.substring(i, i + 1);
            }
            if (i > 0 && s.charAt(i) == s.charAt(i - 1)) {
                dp[i - 1][i] = true;
                maxLen = Math.max(maxLen, 2);
                res = s.substring(i - 1, i + 1);
            }
        }
        for (int i = 3; i <= len; i++) {
            for (int l = 0; l + i <= len; l++) {
                int r = l + i - 1;
                if (s.charAt(r) == s.charAt(l) && dp[l + 1][r - 1]) {
                    dp[l][r] = true;
                    maxLen = i;
                    res = s.substring(l, r + 1);
                }
            }
        }

        return res;


    }
    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return "";
        int maxLen = 0, left = 0, right = 0;
        String res = "";
        for (int i = 0; i < (2 * s.length() - 1); i++) {
            if (i % 2 == 0) {
                left = (i - 2) / 2;
                right = (i + 2) / 2;
            } else {
                left = (i - 1) / 2;
                right = (i + 1) / 2;
            }
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            if (right - left - 1 > maxLen) {
                maxLen = right - left - 1;
                res = s.substring(left + 1, right);
            }
        }
        return res;
    }
}
