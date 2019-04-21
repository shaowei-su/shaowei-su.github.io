/*
 * @lc app=leetcode id=879 lang=java
 *
 * [879] Profitable Schemes
 *
 * https://leetcode.com/problems/profitable-schemes/description/
 *
 * algorithms
 * Hard (35.83%)
 * Total Accepted:    5.1K
 * Total Submissions: 14.2K
 * Testcase Example:  '5\n3\n[2,2]\n[2,3]'
 *
 * There are G people in a gang, and a list of various crimes they could
 * commit.
 * 
 * The i-th crime generates a profit[i] and requires group[i] gang members to
 * participate.
 * 
 * If a gang member participates in one crime, that member can't participate in
 * another crime.
 * 
 * Let's call a profitable scheme any subset of these crimes that generates at
 * least P profit, and the total number of gang members participating in that
 * subset of crimes is at most G.
 * 
 * How many schemes can be chosen?  Since the answer may be very large, return
 * it modulo 10^9 + 7.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: G = 5, P = 3, group = [2,2], profit = [2,3]
 * Output: 2
 * Explanation: 
 * To make a profit of at least 3, the gang could either commit crimes 0 and 1,
 * or just crime 1.
 * In total, there are 2 schemes.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: G = 10, P = 5, group = [2,3,5], profit = [6,7,8]
 * Output: 7
 * Explanation: 
 * To make a profit of at least 5, the gang could commit any crimes, as long as
 * they commit one.
 * There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and
 * (0,1,2).
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= G <= 100
 * 0 <= P <= 100
 * 1 <= group[i] <= 100
 * 0 <= profit[i] <= 100
 * 1 <= group.length = profit.length <= 100
 * 
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    int res = 0;
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int len = group.length;
        int[][] gps = new int[len][2];
        for (int i = 0; i < len; i++) {
            gps[i][0] = group[i];
            gps[i][1] = profit[i];
        }
        Arrays.sort(gps, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });
        dfs2(0, 0, 0, gps, G, P);
        return res;
    }

    public boolean dfs2(int pos, int sumP, int sumG, int[][] gps, int G, int P) {
        if (sumG > G) {
            return false;
        }
        if (sumP >= P) {
            res++;
            res %= 1000000007;
        }
        for (int i = pos; i < gps.length; i++) {
            if (!dfs2(i + 1, sumP + gps[i][1], sumG + gps[i][0], gps, G, P)) {
                break;
            }
        }
        return true;
    }

    public int profitableSchemes2(int G, int P, int[] group, int[] profit) {
        int len = group.length;
        if (len == 0) {
            return 0;
        }
        dfs(0, 0, 0, group, profit, G, P);
        return res;
    }

    public void dfs(int pos, int sumP, int sumG, int[] group, int[] profit, int G, int P) {
        if (sumG > G) {
            return;
        }
        if (sumP >= P) {
            res++;
            res %= 1000000007;
        }
        for (int i = pos; i < group.length; i++) {
            dfs(i + 1, sumP + profit[i], sumG + group[i], group, profit, G, P);
        }
    }
}
