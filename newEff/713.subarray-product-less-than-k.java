/*
 * [713] Subarray Product Less Than K
 *
 * https://leetcode.com/problems/subarray-product-less-than-k/description/
 *
 * algorithms
 * Medium (34.38%)
 * Total Accepted:    19.3K
 * Total Submissions: 55.8K
 * Testcase Example:  '[10,5,2,6]\n100'
 *
 * Your are given an array of positive integers nums.
 * Count and print the number of (contiguous) subarrays where the product of
 * all the elements in the subarray is less than k.
 * 
 * Example 1:
 * 
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are: [10], [5],
 * [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly
 * less than k.
 * 
 * 
 * 
 * Note:
 * 0 < nums.length .
 * 0 < nums[i] < 1000.
 * 0 .
 * 
 */
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
             return 0;
        }
        int count = 0;
        int prd = 1;
        for (int i = 0, j = 0; j < nums.length; j++) {
            prd *= nums[j];
            while (i <= j && prd >= k) {
                prd /= nums[i++];
            }
            count += j - i + 1;
        }

        return count;
    }
    public int numSubarrayProductLessThanK3(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[][] dp = new int[len][k + 1];
        for (int i = 0; i <= k; i++) {
            if (nums[0] < i) {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < len; i++) {
            for (int prd = 0; prd <= k; prd++) {
                if (nums[i] < prd) {
                    dp[i][prd] = 1;
                }
                int div = prd % nums[i] == 0 ? prd / nums[i] : prd / nums[i] + 1;
                dp[i][prd] += dp[i - 1][div];
            }
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            res += dp[i][k];
        }
        return res;
    }
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            dfs(res, new ArrayList<Integer>(), 1, k, nums, i);
        }
        return res.size();
    }
    public void dfs(List<List<Integer>> res, List<Integer> cur, int sum, int k, int[] nums, int pos) {
        if (sum >= k) {
            return;
        }
        if (pos >= nums.length) {
            return;
        }
        if (sum * nums[pos] >= k) {
            return;
        }
        cur.add(nums[pos]);
        res.add(new ArrayList<Integer>(cur));
        dfs(res, cur, sum * nums[pos], k, nums, pos + 1);
    }

}
