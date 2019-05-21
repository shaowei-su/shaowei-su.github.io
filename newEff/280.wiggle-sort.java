/*
 * @lc app=leetcode id=280 lang=java
 *
 * [280] Wiggle Sort
 *
 * https://leetcode.com/problems/wiggle-sort/description/
 *
 * algorithms
 * Medium (60.21%)
 * Total Accepted:    61.3K
 * Total Submissions: 101.4K
 * Testcase Example:  '[3,5,2,1,6,4]'
 *
 * Given an unsorted array nums, reorder it in-place such that nums[0] <=
 * nums[1] >= nums[2] <= nums[3]....
 * 
 * Example:
 * 
 * 
 * Input: nums = [3,5,2,1,6,4]
 * Output: One possible answer is [3,5,1,6,2,4]
 * 
 */
class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if ((i % 2 == 1 && nums[i] < nums[i + 1]) || (i % 2 == 0 && nums[i] > nums[i + 1])) {
                swap(nums, i, i + 1);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void wiggleSort2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        Arrays.sort(nums);
        int[] res = new int[nums.length];
        int i = 0;
        int j = nums.length - 1;
        int pos = 0;
        while (pos < nums.length) {
            res[pos++] = nums[i++];
            if (pos == nums.length) break;
            res[pos++] = nums[j--];
        }
        for (int id = 0; id < nums.length; id++) {
            nums[id] = res[id];
        }
    }
}
