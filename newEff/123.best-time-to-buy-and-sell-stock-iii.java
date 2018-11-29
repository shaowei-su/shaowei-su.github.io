/*
 * [123] Best Time to Buy and Sell Stock III
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
 *
 * algorithms
 * Hard (31.89%)
 * Total Accepted:    126.9K
 * Total Submissions: 397.3K
 * Testcase Example:  '[3,3,5,0,0,3,1,4]'
 *
 * Say you have an array for which the ith element is the price of a given
 * stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most two
 * transactions.
 * 
 * Note: You may not engage in multiple transactions at the same time (i.e.,
 * you must sell the stock before you buy again).
 * 
 * Example 1:
 * 
 * 
 * Input: [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit
 * = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 =
 * 3.
 * 
 * Example 2:
 * 
 * 
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit
 * = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you
 * are
 * engaging multiple transactions at the same time. You must sell before buying
 * again.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * 
 */
class Solution {
    public int maxProfit(int[] prices) {
          if (prices == null || prices.length < 2) {
              return 0;
          }
          int len = prices.length;
          int firstIn = Integer.MAX_VALUE;
          int firstOut = 0;
          int secIn = Integer.MAX_VALUE;
          int secOut = 0;
          for (int i = 0; i < len; i++) {
              firstIn = Math.min(firstIn, prices[i]);
              firstOut = Math.max(firstOut, prices[i] - firstIn);
              secIn = Math.min(secIn, prices[i] - firstOut);
              secOut = Math.max(secOut, prices[i] - secIn);
          }
          return secOut;
    }
    public int maxProfit3(int[] prices) {
         if (prices == null || prices.length < 2) {
             return 0;
         }
         int len = prices.length;
         int[][] dp = new int[3][len];
         for (int k = 1; k <= 2; k++) {
             int min = prices[0];
             for (int i = 1; i < len; i++) {
                 dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
                 min = Math.min(min, prices[i] - dp[k - 1][i]);
             }
         }
         return dp[2][len - 1];
     }
    
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int len = prices.length;
        int[][] dp = new int[3][len];
        for (int k = 1; k <= 2; k++) {
            for (int i = 1; i < len; i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {
                    min = Math.min(min, prices[j] - dp[k - 1][j]);
                }
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
            }
        }
        return dp[2][len - 1];
    }
}
