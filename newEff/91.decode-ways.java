/*
 * [91] Decode Ways
 *
 * https://leetcode.com/problems/decode-ways/description/
 *
 * algorithms
 * Medium (21.35%)
 * Total Accepted:    218.4K
 * Total Submissions: 1M
 * Testcase Example:  '"12"'
 *
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping:
 * 
 * 
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 
 * 
 * Given a non-empty string containing only digits, determine the total number
 * of ways to decode it.
 * 
 * Example 1:
 * 
 * 
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2
 * 6).
 * 
 */
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.startsWith("0")) {
            return 0;
        }
        //dp[i]: ends at i, how many ways to decode
        //dp[i] = dp[i - 1] + dp[i - 2] if i- 2 to i is under 26
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < len + 1; i++) {
            if (s.charAt(i - 1) - '0' > 0) {
                dp[i] = dp[i - 1];
            }
            int twoDig = Integer.parseInt(s.substring(i - 2, i));
            if (twoDig > 9 && twoDig < 27) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[len];
    }
}
