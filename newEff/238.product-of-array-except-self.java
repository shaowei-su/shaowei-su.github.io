/*
 * [238] Product of Array Except Self
 *
 * https://leetcode.com/problems/product-of-array-except-self/description/
 *
 * algorithms
 * Medium (51.74%)
 * Total Accepted:    180.9K
 * Total Submissions: 349.6K
 * Testcase Example:  '[1,2,3,4]'
 *
 * Given an array nums of n integers where n > 1,  return an array output such
 * that output[i] is equal to the product of all the elements of nums except
 * nums[i].
 * 
 * Example:
 * 
 * 
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * 
 * 
 * Note: Please solve it without division and in O(n).
 * 
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does
 * not count as extra space for the purpose of space complexity analysis.)
 * 
 */
class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] res = new int[nums.length];
        res[0] = 1;
        int temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res[i] = temp;
            temp = temp * nums[i];
        }
        int len = nums.length;
        temp = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            res[i] = temp * res[i];
            temp = temp * nums[i];
        }
        return res;
    }
}
