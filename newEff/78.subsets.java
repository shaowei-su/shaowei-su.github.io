/*
 * @lc app=leetcode id=78 lang=java
 *
 * [78] Subsets
 *
 * https://leetcode.com/problems/subsets/description/
 *
 * algorithms
 * Medium (49.93%)
 * Total Accepted:    330.7K
 * Total Submissions: 649K
 * Testcase Example:  '[1,2,3]'
 *
 * Given a set of distinct integers, nums, return all possible subsets (the
 * power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * Example:
 * 
 * 
 * Input: nums = [1,2,3]
 * Output:
 * [
 * ⁠ [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 * 
 */
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
         List<List<Integer>> res = new ArrayList<>();
         if (nums == null || nums.length == 0) {
             return res;
         }
         res.add(new ArrayList<Integer>());
         for (int num : nums) {
             List<List<Integer>> curLevel = new ArrayList<>();
             for (List<Integer> prev : res) {
                 List<Integer> temp = new ArrayList<Integer>(prev);
                 temp.add(num);
                 curLevel.add(temp);
             }
             res.addAll(curLevel);
         }
         return res;


    }
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        dfs(res, nums, new ArrayList<Integer>(), 0);
        return res;
    }
    public void dfs(List<List<Integer>> res, int[] nums, List<Integer> cur, int pos) {
        if (pos > nums.length) {
            return;
        }
        res.add(new ArrayList<Integer>(cur));
        for (int i = pos; i < nums.length; i++) {
            cur.add(nums[i]);
            dfs(res, nums, cur, i + 1);
            cur.remove(cur.size() - 1);
        }
    }
}
