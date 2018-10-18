/*
 * [300] Longest Increasing Subsequence
 *
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 *
 * algorithms
 * Medium (39.23%)
 * Total Accepted:    151K
 * Total Submissions: 384.9K
 * Testcase Example:  '[10,9,2,5,3,7,101,18]'
 *
 * Given an unsorted array of integers, find the length of longest increasing
 * subsequence.
 * 
 * Example:
 * 
 * 
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4 
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore
 * the length is 4. 
 * 
 * Note: 
 * 
 * 
 * There may be more than one LIS combination, it is only necessary for you to
 * return the length.
 * Your algorithm should run in O(n2) complexity.
 * 
 * 
 * Follow up: Could you improve it to O(n log n) time complexity?
 * 
 */
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] sorted = new int[nums.length];
        sorted[0] = nums[0];
        int cur = 0;
        for (int i = 1; i < nums.length; i++) {
            int pos = Arrays.binarySearch(sorted, 0, cur, nums[i]);
            if (pos < 0) {
                pos = -pos - 1;
            }
            if (sorted[pos] > nums[i]) {
                sorted[pos] = nums[i];
            }
            if (pos > cur) {
                cur = pos;
                sorted[cur] = nums[i];
            }
        }
        return cur + 1;
    }
    public int bs(int[] sorted, int cur, int target) {
        int left = 0;
        int right = cur;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (sorted[mid] == target) {
                return mid;
            } else if (sorted[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (sorted[right] < target) {
            return cur + 1;
        } else if (sorted[left] >= target) {
            return left;
        } else {
            return right;
        }
    }
}
