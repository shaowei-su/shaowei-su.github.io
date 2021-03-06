/*
 * @lc app=leetcode id=287 lang=java
 *
 * [287] Find the Duplicate Number
 *
 * https://leetcode.com/problems/find-the-duplicate-number/description/
 *
 * algorithms
 * Medium (47.53%)
 * Total Accepted:    160.7K
 * Total Submissions: 335.1K
 * Testcase Example:  '[1,3,4,2,2]'
 *
 * Given an array nums containing n + 1 integers where each integer is between
 * 1 and n (inclusive), prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,3,4,2,2]
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [3,1,3,4,2]
 * Output: 3
 * 
 * Note:
 * 
 * 
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n^2).
 * There is only one duplicate number in the array, but it could be repeated
 * more than once.
 * 
 * 
 */
class Solution {
    public int findDuplicate(int[] nums) {
        int res = 0;
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            res = res ^ i;
        }
        for (int num : nums) {
            res = res ^ num;
        }

        return res;

    }
    public int findDuplicate3(int[] nums) {
        int left = 1;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) count++;
            }
            if (count <= mid) {
                left = mid;
            } else {
                right = mid;
            }
        }
        int count = 0;
        for (int num : nums) {
           if (num == left) count++;
        }
        if (count > 1) {
            return left;
        }
        count = 0;
        for (int num : nums) {
            if (num == right) count++;
        }
        if (count > 1) {
            return right;
        }
        return left;

    }
    public int findDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return 0;
    }
}
