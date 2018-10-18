/*
 * [259] 3Sum Smaller
 *
 * https://leetcode.com/problems/3sum-smaller/description/
 *
 * algorithms
 * Medium (43.00%)
 * Total Accepted:    41.4K
 * Total Submissions: 96.3K
 * Testcase Example:  '[-2,0,1,3]\n2'
 *
 * Given an array of n integers nums and a target, find the number of index
 * triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i]
 * + nums[j] + nums[k] < target.
 * 
 * Example:
 * 
 * 
 * Input: nums = [-2,0,1,3], and target = 2
 * Output: 2 
 * Explanation: Because there are two triplets which sums are less than
 * 2:
 * [-2,0,1]
 * ⁠            [-2,0,3]
 * 
 * 
 * Follow up: Could you solve it in O(n2) runtime?
 * 
 */
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            int remain = target - nums[i];
            while (left < right) {
                if (nums[left] + nums[right] < remain) {
                    res += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}
