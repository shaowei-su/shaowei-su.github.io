/*
 * [265] Paint House II
 *
 * https://leetcode.com/problems/paint-house-ii/description/
 *
 * algorithms
 * Hard (39.34%)
 * Total Accepted:    35.8K
 * Total Submissions: 91K
 * Testcase Example:  '[[1,5,3],[2,9,4]]'
 *
 * There are a row of n houses, each house can be painted with one of the k
 * colors. The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the
 * same color.
 * 
 * The cost of painting each house with a certain color is represented by a n x
 * k cost matrix. For example, costs[0][0] is the cost of painting house 0 with
 * color 0; costs[1][2] is the cost of painting house 1 with color 2, and so
 * on... Find the minimum cost to paint all houses.
 * 
 * Note:
 * All costs are positive integers.
 * 
 * Example:
 * 
 * 
 * Input: [[1,5,3],[2,9,4]]
 * Output: 5
 * Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum
 * cost: 1 + 4 = 5; 
 * Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 +
 * 2 = 5. 
 * 
 * 
 * Follow up:
 * Could you solve it in O(nk) runtime?
 * 
 */
class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int num = costs.length;
        int k = costs[0].length;
        int[] dp = new int[k];
        int min = 0, secMin = 0;
        for (int i = 0; i < num; i++) {
            int prevMin = min;
            int prevSecMin = secMin;
            min = Integer.MAX_VALUE;
            secMin = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                if (dp[j] != prevMin || prevMin == prevSecMin) {
                    dp[j] = prevMin + costs[i][j];
                } else {
                    dp[j] = prevSecMin + costs[i][j];
                }
                if (dp[j] < min) {
                    secMin = min;
                    min = dp[j];
                } else {
                    secMin = Math.min(dp[j], secMin);
                }
            }
        }
        return min;
    }
}
