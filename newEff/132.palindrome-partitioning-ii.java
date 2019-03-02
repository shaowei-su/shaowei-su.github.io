/*
 * @lc app=leetcode id=132 lang=java
 *
 * [132] Palindrome Partitioning II
 *
 * https://leetcode.com/problems/palindrome-partitioning-ii/description/
 *
 * algorithms
 * Hard (26.40%)
 * Total Accepted:    96.8K
 * Total Submissions: 363.1K
 * Testcase Example:  '"aab"'
 *
 * Given a string s, partition s such that every substring of the partition is
 * a palindrome.
 * 
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * 
 * Example:
 * 
 * 
 * Input: "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using
 * 1 cut.
 * 
 * 
 */
class Solution {
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        boolean[][] isPan = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            isPan[i][i] = true;
            if (i > 0 && s.charAt(i - 1) == s.charAt(i)) {
                isPan[i - 1][i] = true;
            }
        }
        for (int i = 2; i < len; i++) {
            for (int j = 0; j + i < len; j++) {
                int right = j + i;
                if (isPan[j + 1][right - 1] && s.charAt(j) == s.charAt(right)) {
                    isPan[j][right] = true;
                }
            }
        }

        int[] dp = new int[len + 1];
        dp[0] = -1;
        for (int i = 1; i <= len; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (isPan[j][i - 1]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[len];
    }
}
