/*
 * @lc app=leetcode id=51 lang=java
 *
 * [51] N-Queens
 *
 * https://leetcode.com/problems/n-queens/description/
 *
 * algorithms
 * Hard (36.92%)
 * Total Accepted:    130K
 * Total Submissions: 344.3K
 * Testcase Example:  '4'
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 * 
 * 
 * 
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * 
 * Each solution contains a distinct board configuration of the n-queens'
 * placement, where 'Q' and '.' both indicate a queen and an empty space
 * respectively.
 * 
 * Example:
 * 
 * 
 * Input: 4
 * Output: [
 * ⁠[".Q..",  // Solution 1
 * ⁠ "...Q",
 * ⁠ "Q...",
 * ⁠ "..Q."],
 * 
 * ⁠["..Q.",  // Solution 2
 * ⁠ "Q...",
 * ⁠ "...Q",
 * ⁠ ".Q.."]
 * ]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as
 * shown above.
 * 
 * 
 */
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n < 1) {
            return res;
        }
        List<List<Integer>> itm = new ArrayList<>();
        dfs(itm, new ArrayList<Integer>(), n);
        fillin(res, itm, n);
        return res;
    }

    public String convert(Integer i, int n) {
        StringBuilder sb = new StringBuilder();
        for (int id = 0; id < n; id++) {
            if (id == i) {
                sb.append('Q');
            } else {
                sb.append('.');
            }
        }
        return sb.toString();
    }
            

    public void fillin(List<List<String>> res, List<List<Integer>> itm, int n) {
        for (List<Integer> it : itm) {
            List<String> tmp = new ArrayList<>();
            for (Integer i : it) {
                tmp.add(convert(i, n));
            }
            res.add(tmp);
        }
    }

    public void dfs(List<List<Integer>> itm, List<Integer> cur, int n) {
        if (cur.size() == n) {
            itm.add(new ArrayList<Integer>(cur));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!isValid(cur, i)) {
                continue;
            }
            cur.add(i);
            dfs(itm, cur, n);
            cur.remove(cur.size() - 1);
        }
    }

    public boolean isValid(List<Integer> cur, int pos) {
        for (int i = 0; i < cur.size(); i++) {
            if (cur.get(i) == pos) {
                return false;
            }
            if (Math.abs(cur.get(i) - pos) == (cur.size() - i)) {
                return false;
            }
        }
        return true;
    }

}
