/*
 * @lc app=leetcode id=75 lang=java
 *
 * [75] Sort Colors
 *
 * https://leetcode.com/problems/sort-colors/description/
 *
 * algorithms
 * Medium (40.86%)
 * Total Accepted:    292.5K
 * Total Submissions: 707.7K
 * Testcase Example:  '[2,0,2,1,1,0]'
 *
 * Given an array with n objects colored red, white or blue, sort them in-place
 * so that objects of the same color are adjacent, with the colors in the order
 * red, white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red,
 * white, and blue respectively.
 * 
 * Note: You are not suppose to use the library's sort function for this
 * problem.
 * 
 * Example:
 * 
 * 
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * 
 * Follow up:
 * 
 * 
 * A rather straight forward solution is a two-pass algorithm using counting
 * sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then
 * overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 * 
 * 
 */
class Solution {
    public void sortColors(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        int iter = 0;
        while (iter <= right) {
            if (nums[iter] == 0 && iter > left) {
                swap(nums, left, iter);
                left++;
                iter--;
            } else if (nums[iter] == 2) {
                swap(nums, right, iter);
                right--;
                iter--;
            }
            iter++;
        }

    }






    public void sortColors2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int left = 0;
        int right = nums.length - 1;
        int index = 0;

        while (index <= right) {
            if (nums[index] == 0) {
                nums[index] = nums[left];
                nums[left] = 0;
                left++;
            }
            if (nums[index] == 2) {
                nums[index] = nums[right];
                nums[right] = 2;
                right--;
                index--;
            }
            index++;
        }
        
    }

    public void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
