/*
 * [188] Best Time to Buy and Sell Stock IV
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/
 *
 * algorithms
 * Hard (25.43%)
 * Total Accepted:    73.4K
 * Total Submissions: 288.5K
 * Testcase Example:  '2\n[2,4,1]'
 *
 * Say you have an array for which the ith element is the price of a given
 * stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most k
 * transactions.
 * 
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 * 
 * Example 1:
 * 
 * 
 * Input: [2,4,1], k = 2
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit
 * = 4-2 = 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [3,2,6,5,0,3], k = 2
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit
 * = 6-2 = 4.
 * Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 =
 * 3.
 * 
 */
class Solution {
    public int maxProfit(int k, int[] prices) {
         if (prices == null || prices.length < 2) {
             return 0;
         }

         int len = prices.length;
         int res = 0;
        /*optimize for UNLIMITED TRADE case*/
         if (k >= len / 2) { 
            for (int i = 1; i < len; i++) {
                res += Math.max(0, prices[i] - prices[i - 1]);
            }
            return res;
         }
         int[][] dp = new int[k + 1][len];
         for (int num = 1; num <= k; num++) {
             int min = prices[0];
             for (int i = 1; i < len; i++) {
                 dp[num][i] = Math.max(dp[num][i - 1], prices[i] - min);
                 min = Math.min(min, prices[i] - dp[num - 1][i]);
             }
         }
         return dp[k][len - 1];        
    }
}
