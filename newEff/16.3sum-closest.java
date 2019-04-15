/*
 * @lc app=leetcode id=16 lang=java
 *
 * [16] 3Sum Closest
 *
 * https://leetcode.com/problems/3sum-closest/description/
 *
 * algorithms
 * Medium (42.01%)
 * Total Accepted:    312.7K
 * Total Submissions: 729.1K
 * Testcase Example:  '[-1,2,1,-4]\n1'
 *
 * Given an array nums of n integers and an integer target, find three integers
 * in nums such that the sum is closest to target. Return the sum of the three
 * integers. You may assume that each input would have exactly one solution.
 * 
 * Example:
 * 
 * 
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 * 
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * 
 * 
 */
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int cur = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int t = target - nums[i];
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int tmp = nums[l] + nums[r];
                if (Math.abs(tmp - t) < diff) {
                    diff = Math.abs(tmp - t);
                    cur = tmp + nums[i];
                }
                if (tmp == t) {
                    return target;
                } else if (tmp > t) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return cur;
    }
}
