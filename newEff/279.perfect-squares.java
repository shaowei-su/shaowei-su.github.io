/*
 * @lc app=leetcode id=279 lang=java
 *
 * [279] Perfect Squares
 *
 * https://leetcode.com/problems/perfect-squares/description/
 *
 * algorithms
 * Medium (41.24%)
 * Total Accepted:    169.9K
 * Total Submissions: 411.8K
 * Testcase Example:  '12'
 *
 * Given a positive integer n, find the least number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 * 
 * Example 1:
 * 
 * 
 * Input: n = 12
 * Output: 3 
 * Explanation: 12 = 4 + 4 + 4.
 * 
 * Example 2:
 * 
 * 
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */
class Solution {
    public int numSquares(int n) {
        int maxSR = (int) Math.sqrt(n);
        int[] srArr = new int[maxSR + 1];
        Set<Integer> srSet = new HashSet<>();
        for (int i = 1; i <= maxSR; i++) {
            srSet.add(i * i);
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        /*
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (srSet.contains(i - j)) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        */
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < n; i++) {
            for (Integer sr : srSet) {
                if (i + sr <= n) {
                    dp[i + sr] = Math.min(dp[i + sr], dp[i] + 1);
                }
            }
        }

        return dp[n];
                

    }
}
