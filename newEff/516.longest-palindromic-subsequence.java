/*
 * [516] Longest Palindromic Subsequence
 *
 * https://leetcode.com/problems/longest-palindromic-subsequence/description/
 *
 * algorithms
 * Medium (43.51%)
 * Total Accepted:    37.8K
 * Total Submissions: 86.9K
 * Testcase Example:  '"bbbab"'
 *
 * 
 * Given a string s, find the longest palindromic subsequence's length in s.
 * You may assume that the maximum length of s is 1000.
 * 
 * 
 * Example 1:
 * Input: 
 * 
 * "bbbab"
 * 
 * Output: 
 * 
 * 4
 * 
 * One possible longest palindromic subsequence is "bbbb".
 * 
 * 
 * Example 2:
 * Input:
 * 
 * "cbbd"
 * 
 * Output:
 * 
 * 2
 * 
 * One possible longest palindromic subsequence is "bb".
 * 
 */
class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        return palinWithin(chars, 0, chars.length - 1);
    }
    public int palinWithin(char[] chars, int s, int e) {
        if (s > e) {
            return 0;
        }
        if (e == s) {
            return 1;
        }
        if (chars[s] == chars[e]){
            return palinWithin(chars, s + 1, e - 1) + 2;
        } else {
            return Math.max(palinWithin(chars, s + 1, e), palinWithin(chars, s, e - 1));
        }
    }
}
