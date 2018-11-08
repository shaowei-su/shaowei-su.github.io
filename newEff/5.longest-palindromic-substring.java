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
