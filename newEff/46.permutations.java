/*
 * [46] Permutations
 *
 * https://leetcode.com/problems/permutations/description/
 *
 * algorithms
 * Medium (50.43%)
 * Total Accepted:    291K
 * Total Submissions: 573.8K
 * Testcase Example:  '[1,2,3]'
 *
 * Given a collection of distinct integers, return all possible permutations.
 * 
 * Example:
 * 
 * 
 * Input: [1,2,3]
 * Output:
 * [
 * ⁠ [1,2,3],
 * ⁠ [1,3,2],
 * ⁠ [2,1,3],
 * ⁠ [2,3,1],
 * ⁠ [3,1,2],
 * ⁠ [3,2,1]
 * ]
 * 
 * 
 */
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfs(res, new ArrayList(), nums, visited);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> cur, int[] nums, Set<Integer> visited) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i : nums) {
            if (!visited.contains(i)) {
                visited.add(i);
                cur.add(i);
                dfs(res, cur, nums, visited);
                cur.remove(cur.size() - 1);
                visited.remove(i);
            }
        }
    }
}
