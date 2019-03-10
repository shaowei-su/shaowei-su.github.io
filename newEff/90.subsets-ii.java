/*
 * @lc app=leetcode id=90 lang=java
 *
 * [90] Subsets II
 *
 * https://leetcode.com/problems/subsets-ii/description/
 *
 * algorithms
 * Medium (40.92%)
 * Total Accepted:    188.6K
 * Total Submissions: 455.2K
 * Testcase Example:  '[1,2,2]'
 *
 * Given a collection of integers that might contain duplicates, nums, return
 * all possible subsets (the power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * Example:
 * 
 * 
 * Input: [1,2,2]
 * Output:
 * [
 * ⁠ [2],
 * ⁠ [1],
 * ⁠ [1,2,2],
 * ⁠ [2,2],
 * ⁠ [1,2],
 * ⁠ []
 * ]
 * 
 * 
 */
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
         List<List<Integer>> res = new ArrayList<>();
         if (nums == null || nums.length == 0) {
             return res;
         }
         Arrays.sort(nums); 
         res.add(new ArrayList<Integer>());
         int begin = 0;
         for (int i = 0; i < nums.length; i++) {
             if (i == 0 || nums[i] != nums[i - 1]) begin = 0; 
             int size = res.size();
             for (int j = begin; j < size; j++) {
                ArrayList<Integer> temp = new ArrayList<>(res.get(j));
                temp.add(nums[i]);
                res.add(temp);
             }
             begin = size;
         }
         return res;



    }
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        dfs(res, nums, new ArrayList<Integer>(), 0);
        return res;
    }
    public void dfs(List<List<Integer>> res, int[] nums, List<Integer> cur, int pos) {
        if (pos > nums.length) {
            return;
        }
        res.add(new ArrayList<Integer>(cur));
        for (int i = pos; i < nums.length; i++) {
            if (i > pos && nums[i] == nums[i - 1]) {
                continue;
            }
            cur.add(nums[i]);
            dfs(res, nums, cur, i + 1);
            cur.remove(cur.size() - 1);
        }
    }
}
