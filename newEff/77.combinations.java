/*
 * @lc app=leetcode id=77 lang=java
 *
 * [77] Combinations
 *
 * https://leetcode.com/problems/combinations/description/
 *
 * algorithms
 * Medium (45.16%)
 * Total Accepted:    186.1K
 * Total Submissions: 404.4K
 * Testcase Example:  '4\n2'
 *
 * Given two integers n and k, return all possible combinations of k numbers
 * out of 1 ... n.
 * 
 * Example:
 * 
 * 
 * Input: n = 4, k = 2
 * Output:
 * [
 * ⁠ [2,4],
 * ⁠ [3,4],
 * ⁠ [2,3],
 * ⁠ [1,2],
 * ⁠ [1,3],
 * ⁠ [1,4],
 * ]
 * 
 * 
 */
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k < 0 || n < 1) {
            return res;
        }
        dfs(n, k, new ArrayList<Integer>(), res, 1);
        return res;
    }
    public void dfs(int n, int k, List<Integer> cur, List<List<Integer>> res, int pos) {
        if (cur.size() == k) {
            res.add(new ArrayList<Integer>(cur));
            return;
        }
        if (pos > n) {
            return;
        }
        for (int i = pos; i <= n; i++) {
            cur.add(i);
            dfs(n, k, cur, res, i + 1);
            cur.remove(cur.size() - 1);
        }
    }
}           
