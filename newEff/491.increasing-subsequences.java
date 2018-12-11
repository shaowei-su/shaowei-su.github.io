/*
 * [491] Increasing Subsequences
 *
 * https://leetcode.com/problems/increasing-subsequences/description/
 *
 * algorithms
 * Medium (40.68%)
 * Total Accepted:    26.9K
 * Total Submissions: 66.1K
 * Testcase Example:  '[4,6,7,7]'
 *
 * 
 * Given an integer array, your task is to find all the different possible
 * increasing subsequences of the given array, and the length of an increasing
 * subsequence should be at least 2 .
 * 
 * 
 * Example:
 * 
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7],
 * [4,7,7]]
 * 
 * 
 * 
 * Note:
 * 
 * The length of the given array will not exceed 15.
 * The range of integer in the given array is [-100,100].
 * The given array may contain duplicates, and two equal integers should also
 * be considered as a special case of increasing sequence.
 * 
 * 
 */
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        dfs(res, new ArrayList<Integer>(), 0, nums);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> cur, int pos, int[] nums) {
        if (cur.size() >= 2) {
            res.add(new ArrayList<Integer>(cur));
        }
        if (pos == nums.length) {
            return;
        }
        int last = cur.size() > 0 ? cur.get(cur.size() - 1) : Integer.MIN_VALUE;
        Set<Integer> visited = new HashSet<>();
        for (int i = pos; i < nums.length; i++) {
            if (nums[i] < last) {
                continue;
            }
            if (visited.contains(nums[i])) {
                continue;
            }
            visited.add(nums[i]);
            cur.add(nums[i]);
            dfs(res, cur, i + 1, nums);
            cur.remove(cur.size() - 1);
        }
    }
}
