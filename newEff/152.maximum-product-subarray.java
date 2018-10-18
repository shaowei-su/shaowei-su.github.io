/*
 * [152] Maximum Product Subarray
 *
 * https://leetcode.com/problems/maximum-product-subarray/description/
 *
 * algorithms
 * Medium (27.59%)
 * Total Accepted:    164.2K
 * Total Submissions: 595.3K
 * Testcase Example:  '[2,3,-2,4]'
 *
 * Given an integer array nums, find the contiguous subarray within an array
 * (containing at least one number) which has the largest product.
 * 
 * Example 1:
 * 
 * 
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * 
 */
class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int globalMax = nums[0];
        int prevMin = nums[0];
        int prevMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int a = prevMin * nums[i];
            int b = prevMax * nums[i];
            prevMin = Math.min(nums[i], Math.min(a, b));
            prevMax = Math.max(nums[i], Math.max(a, b));
            globalMax = Math.max(globalMax, prevMax);
        }
        return globalMax;
    }
}
