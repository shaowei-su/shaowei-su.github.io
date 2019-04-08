/*
 * @lc app=leetcode id=97 lang=java
 *
 * [97] Interleaving String
 *
 * https://leetcode.com/problems/interleaving-string/description/
 *
 * algorithms
 * Hard (26.87%)
 * Total Accepted:    104.2K
 * Total Submissions: 382.1K
 * Testcase Example:  '"aabcc"\n"dbbca"\n"aadbbcbcac"'
 *
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and
 * s2.
 * 
 * Example 1:
 * 
 * 
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 * 
 * 
 */
class Solution {
    Map<String, Boolean> cache = new HashMap<>();
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1 == null || s1.length() == 0) {
            return s2.equals(s3);
        }
        if (s2 == null || s2.length() == 0) {
            return s1.equals(s3);
        }
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        if (s3.length() == 0) {
            return false;
        }
        if (cache.containsKey(s1 + "_" + s2 + "_" + s3)) {
            return cache.get(s1 + "_" + s2 + "_" + s3);
        }
        int i1 = 0, i2 = 0, i3 = 0;
        while (i1 < s1.length() && i2 < s2.length()) {
            char c1 = s1.charAt(i1);
            char c2 = s2.charAt(i2);
            char c3 = s3.charAt(i1 + i2);
            if (c1 == c3 && c2 == c3) {
                return isInterleave(s1.substring(i1 + 1), s2.substring(i2), s3.substring(i1 + i2 + 1)) || isInterleave(s1.substring(i1), s2.substring(i2 + 1), s3.substring(i1 + i2 + 1));
            } else if (c1 == c3) {
                i1++;
            } else if (c2 == c3) {
                i2++;
            } else {
                cache.put(s1 + "_" + s2 + "_" + s3, false);
                return false;
            }
        }
        if (i1 < s1.length()) {
            return s1.substring(i1).equals(s3.substring(i1 + i2));
        }
        if (i2 < s2.length()) {
            return s2.substring(i2).equals(s3.substring(i1 + i2));
        }
        cache.put(s1 + "_" + s2 + "_" + s3, true);
        return true;
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        if (s1.length() == 0) {
            return s2.equals(s3);
        }
        if (s2.length() == 0) {
            return s1.equals(s3);
        }
        int len1 = s1.length();
        int len2 = s2.length();
        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        char[] char3 = s3.toCharArray();
        int[][] dp = new int[len2 + 1][len1 + 1];
        dp[0][0] = 0;
        // dp[i][j] = k : means, use s1's prev i chars, use s2's prev j chars, we can match up to kth char in s3
        for (int i = 1; i <= len1; i++) {
            if (char1[i - 1] == char3[i - 1]) {
                dp[0][i] = dp[0][i - 1] + 1;
            } else {
                dp[0][i] = 0;
            }
        }
        for (int i = 1; i <= len2; i++) {
            if (char2[i - 1] == char3[i - 1]) {
                dp[i][0] = dp[i - 1][0] + 1;
            } else {
                dp[i][0] = 0;
            }
        }
        for (int i = 1; i <= len2; i++) {
            for (int j = 1; j <= len1; j++) {
                    if (char2[i - 1] == char3[dp[i - 1][j]]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + 1);
                    }
                    if (char1[j - 1] == char3[dp[i][j - 1]]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][j - 1] + 1);
                    }
            }
        }
        return dp[len2][len1] == char3.length;
    }
}
