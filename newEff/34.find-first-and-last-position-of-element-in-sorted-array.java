/*
 * [34] Find First and Last Position of Element in Sorted Array
 *
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 *
 * algorithms
 * Medium (32.08%)
 * Total Accepted:    231.1K
 * Total Submissions: 719K
 * Testcase Example:  '[5,7,7,8,8,10]\n8'
 *
 * Given an array of integers nums sorted in ascending order, find the starting
 * and ending position of a given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * 
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[] {-1, -1};
        }
        int[] res = new int[2];
        int left = 0;
        int right = nums.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) {
            res[0] = left;
        } else if (nums[right] == target) {
            res[0] = right;
        }

        left = 0;
        right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (nums[right] == target) {
            res[1] = right;
        } else if (nums[left] == target) {
            res[1] = left;
        }

        if (nums[res[0]] != target) {
            return new int[] {-1, -1};
        }


        return res;






    }

    public int[] searchRange2(int[] nums, int target) {
        int[] res = new int[] {-1, -1};
        if (nums == null || nums.length == 0) return res;
        int ind = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                ind = mid;
                break;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) {
            ind = left;
        }
        if (nums[right] == target) {
            ind = right;
        }
        if (ind == -1) {
        } else {
            int leftmost = ind;
            int rightmost = ind;
            while (leftmost >= 0 && nums[leftmost] == target) {
                leftmost--;
            }
            while (rightmost < nums.length && nums[rightmost] == target) {
                rightmost++;
            }
            res[0] = leftmost + 1;
            res[1] = rightmost - 1;
        }
        return res;
    }
}
