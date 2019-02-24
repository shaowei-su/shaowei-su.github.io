/*
 * @lc app=leetcode id=169 lang=java
 *
 * [169] Majority Element
 *
 * https://leetcode.com/problems/majority-element/description/
 *
 * algorithms
 * Easy (50.86%)
 * Total Accepted:    345.6K
 * Total Submissions: 672.7K
 * Testcase Example:  '[3,2,3]'
 *
 * Given an array of size n, find the majority element. The majority element is
 * the element that appears more than ⌊ n/2 ⌋ times.
 * 
 * You may assume that the array is non-empty and the majority element always
 * exist in the array.
 * 
 * Example 1:
 * 
 * 
 * Input: [3,2,3]
 * Output: 3
 * 
 * Example 2:
 * 
 * 
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 * 
 * 
 */
class Solution {
    public int majorityElement(int[] nums) {
        int major = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                major = nums[i];
                count = 1;
            } else if (major == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return major;

    }
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        int ind = 0;
        int targetLen = nums.length % 2 == 0 ? nums.length / 2 : nums.length / 2 + 1;
        while (ind < nums.length) {
            int cur = ind;
            while (ind < nums.length - 1 && nums[ind] == nums[ind + 1]) {
                ind++;
            }
            if (ind - cur + 1 >= targetLen) {
                return nums[cur];
            }
            ind++;
        }
        return 0;
    }
}
