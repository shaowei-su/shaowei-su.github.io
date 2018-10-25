/*
 * [47] Permutations II
 *
 * https://leetcode.com/problems/permutations-ii/description/
 *
 * algorithms
 * Medium (37.12%)
 * Total Accepted:    195K
 * Total Submissions: 522.6K
 * Testcase Example:  '[1,1,2]'
 *
 * Given a collection of numbers that might contain duplicates, return all
 * possible unique permutations.
 * 
 * Example:
 * 
 * 
 * Input: [1,1,2]
 * Output:
 * [
 * ⁠ [1,1,2],
 * ⁠ [1,2,1],
 * ⁠ [2,1,1]
 * ]
 * 
 * 
 */
class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        dfs2(res, new ArrayList<Integer>(), nums, visited);
        return res;
    }

    public void dfs2(List<List<Integer>> res, List<Integer> cur, int[] nums, boolean[] visited) {
         if (cur.size() == nums.length) {
             res.add(new ArrayList<>(cur));
             return;
         }
         for (int i = 0; i < nums.length; i++) {
             if (visited[i]) continue;
             if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
             visited[i] = true;
             cur.add(nums[i]);
             dfs2(res, cur, nums, visited);
             visited[i] = false;
             cur.remove(cur.size() - 1);
         }
    }

    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> numCount = new HashMap<>();
        for (int i : nums) {
            numCount.put(i, numCount.getOrDefault(i, 0) + 1);
        }
        StringBuilder sb = new StringBuilder();
        Set<String> visited = new HashSet<>();
        dfs(res, new ArrayList(), nums, numCount, sb, visited);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> cur, int[] nums, Map<Integer, Integer> numCount, StringBuilder sb, Set<String> visited) {
        if (cur.size() == nums.length) {
            if (!visited.contains(sb.toString())) {
            res.add(new ArrayList<>(cur));
            visited.add(sb.toString());
            }
            return;
        }
        for (int i : nums) {
            if (numCount.get(i) > 0) {
                numCount.put(i, numCount.get(i) - 1);
                cur.add(i);
                sb.append(i);
                dfs(res, cur, nums, numCount, sb, visited);
                cur.remove(cur.size() - 1);
                numCount.put(i, numCount.get(i) + 1);
                sb.setLength(sb.length() - 1);
            }
        }
    }
}
