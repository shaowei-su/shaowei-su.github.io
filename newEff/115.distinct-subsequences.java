/*
 * @lc app=leetcode id=115 lang=java
 *
 * [115] Distinct Subsequences
 *
 * https://leetcode.com/problems/distinct-subsequences/description/
 *
 * algorithms
 * Hard (34.22%)
 * Total Accepted:    100.6K
 * Total Submissions: 291.7K
 * Testcase Example:  '"rabbbit"\n"rabbit"'
 *
 * Given a string S and a string T, count the number of distinct subsequences
 * of S which equals T.
 * 
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (ie, "ACE" is a
 * subsequence of "ABCDE" while "AEC" is not).
 * 
 * Example 1:
 * 
 * 
 * Input: S = "rabbbit", T = "rabbit"
 * Output: 3
 * Explanation:
 * 
 * As shown below, there are 3 ways you can generate "rabbit" from S.
 * (The caret symbol ^ means the chosen letters)
 * 
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: S = "babgbag", T = "bag"
 * Output: 5
 * Explanation:
 * 
 * As shown below, there are 5 ways you can generate "bag" from S.
 * (The caret symbol ^ means the chosen letters)
 * 
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 * ⁠ ^  ^^
 * babgbag
 * ⁠   ^^^
 * 
 * 
 */
class Solution {
    public int numDistinct(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return 0;
        }
        int sLen = s.length();
        int tLen = t.length();
        int[][] dp = new int[sLen][tLen];
        for (int i = 0; i < sLen; i++) {
            if (s.charAt(i) == t.charAt(0)) {
                dp[i][0] = 1;
            }
        }
        for (int i = 0; i < sLen; i++) {
            for (int j = 1; j < tLen; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    int prevMax = 0;
                    for (int m = 0; m < i; m++) {
                        prevMax += dp[m][j - 1];
                    }
                    dp[i][j] = prevMax;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < sLen; i++) {
            sum += dp[i][tLen - 1];
        }
        return sum;

    }
}
