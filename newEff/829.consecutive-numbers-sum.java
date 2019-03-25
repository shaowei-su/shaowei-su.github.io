/*
 * @lc app=leetcode id=829 lang=java
 *
 * [829] Consecutive Numbers Sum
 *
 * https://leetcode.com/problems/consecutive-numbers-sum/description/
 *
 * algorithms
 * Hard (31.28%)
 * Total Accepted:    9.8K
 * Total Submissions: 30.2K
 * Testcase Example:  '5'
 *
 * Given a positive integer N, how many ways can we write it as a sum of
 * consecutive positive integers?
 * 
 * Example 1:
 * 
 * 
 * Input: 5
 * Output: 2
 * Explanation: 5 = 5 = 2 + 3
 * 
 * Example 2:
 * 
 * 
 * Input: 9
 * Output: 3
 * Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
 * 
 * Example 3:
 * 
 * 
 * Input: 15
 * Output: 4
 * Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 * 
 * Note: 1 <= N <= 10 ^ 9.
 * 
 */
class Solution {
    public int consecutiveNumbersSum(int N) {
        if (N == 1) {
            return 1;
        }
        int count = 0;
        for (int m = 1; m < N; m++) {
            int mx = N - m * (m - 1) / 2;
            if (mx <= 0) {
                break;
            }
            if (mx % m == 0) {
                count++;
            }
        }

        return count;
        
    }
}
