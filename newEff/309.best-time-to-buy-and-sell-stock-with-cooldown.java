/*
 * [309] Best Time to Buy and Sell Stock with Cooldown
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
 *
 * algorithms
 * Medium (42.79%)
 * Total Accepted:    75.3K
 * Total Submissions: 175.9K
 * Testcase Example:  '[1,2,3,0,2]'
 *
 * Say you have an array for which the ith element is the price of a given
 * stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many
 * transactions as you like (ie, buy one and sell one share of the stock
 * multiple times) with the following restrictions:
 * 
 * 
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1
 * day)
 * 
 * 
 * Example:
 * 
 * 
 * Input: [1,2,3,0,2]
 * Output: 3 
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * 
 */
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int len = prices.length;
        int[] buy = new int[len];
        int[] sell = new int[len];
        sell[1] = Math.max(0, prices[1] - prices[0]);
        buy[0] = -prices[0];
        buy[1] = Math.max(-prices[1], -prices[0]);
        for (int i = 2; i < len; i++) {
           sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
           buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
        }
        return sell[len - 1];
    }
}
