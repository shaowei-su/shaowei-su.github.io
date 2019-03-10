/*
 * @lc app=leetcode id=52 lang=java
 *
 * [52] N-Queens II
 *
 * https://leetcode.com/problems/n-queens-ii/description/
 *
 * algorithms
 * Hard (50.04%)
 * Total Accepted:    93.3K
 * Total Submissions: 183.7K
 * Testcase Example:  '4'
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 * 
 * 
 * 
 * Given an integer n, return the number of distinct solutions to the n-queens
 * puzzle.
 * 
 * Example:
 * 
 * 
 * Input: 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as
 * shown below.
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * 
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 
 * 
 */
class Solution {
    public int totalNQueens(int n) {
        int count = dfs(0, n, 0, new HashSet<Integer>(), new HashSet<Integer>(), new HashSet<Integer>());
        return count;
    }
    public int dfs(int row, int n, int count, Set<Integer> col, Set<Integer> diag1, Set<Integer> diag2) {
        if (row >= n) {
            return count;
        }
        for (int i = 0; i <n; i++) {
            if (col.contains(i)) {
                continue;
            }
            int d1 = row + i;
            if (diag1.contains(d1)) {
                continue;
            }
            int d2 = row - i;
            if (diag2.contains(d2)) {
                continue;
            }
            if (row == n - 1) {
                count++;
            }

            col.add(i);
            diag1.add(d1);
            diag2.add(d2);
            count = dfs(row + 1, n, count, col, diag1, diag2);
            col.remove(i);
            diag1.remove(d1);
            diag2.remove(d2);
        }
        return count;
    }
}
