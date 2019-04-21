/*
 * @lc app=leetcode id=935 lang=java
 *
 * [935] Knight Dialer
 *
 * https://leetcode.com/problems/knight-dialer/description/
 *
 * algorithms
 * Medium (39.02%)
 * Total Accepted:    9.1K
 * Total Submissions: 23.2K
 * Testcase Example:  '1'
 *
 * A chess knight can move as indicated in the chess diagram below:
 * 
 * .           
 * 
 * 
 * 
 * This time, we place our chess knight on any numbered key of a phone pad
 * (indicated above), and the knight makes N-1 hops.  Each hop must be from one
 * key to another numbered key.
 * 
 * Each time it lands on a key (including the initial placement of the knight),
 * it presses the number of that key, pressing N digits total.
 * 
 * How many distinct numbers can you dial in this manner?
 * 
 * Since the answer may be large, output the answer modulo 10^9 + 7.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: 1
 * Output: 10
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 2
 * Output: 20
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: 3
 * Output: 46
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= N <= 5000
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    public int knightDialer(int N) {
        Map<Integer, int[]> jumps = new HashMap<>();
        jumps.put(0, new int[] {4, 6});
        jumps.put(1, new int[] {6, 8});
        jumps.put(2, new int[] {7, 9});
        jumps.put(3, new int[] {4, 8});
        jumps.put(4, new int[] {0, 3, 9});
        jumps.put(6, new int[] {0, 1, 7});
        jumps.put(7, new int[] {2, 6});
        jumps.put(8, new int[] {3, 1});
        jumps.put(9, new int[] {2, 4});

        int[][] dp = new int[N][10];
        int res = 0;
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < 10; j++) {
                if (dp[i][j] > 0 && jumps.containsKey(j)) {
                    for (int k : jumps.get(j)) {
                        dp[i + 1][k] += dp[i][j];
                        dp[i + 1][k] %= 1000000007;
                    }
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            res += dp[N - 1][i];
            res %= 1000000007;
        }
        return res;
    }
}
