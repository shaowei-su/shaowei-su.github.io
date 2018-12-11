/*
 * [548] Split Array with Equal Sum
 *
 * https://leetcode.com/problems/split-array-with-equal-sum/description/
 *
 * algorithms
 * Medium (40.11%)
 * Total Accepted:    6.2K
 * Total Submissions: 15.4K
 * Testcase Example:  '[1,2,1,2,1,2,1]'
 *
 * 
 * Given an array with n integers, you need to find if there are triplets  (i,
 * j, k) which satisfies following conditions:
 * 
 * ⁠0 < i, i + 1 < j, j + 1 < k < n - 1 
 * ⁠Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n -
 * 1) should be equal. 
 * 
 * where we define that subarray (L, R) represents a slice of the original
 * array starting from the element indexed L to the element indexed R.
 * 
 * 
 * Example:
 * 
 * Input: [1,2,1,2,1,2,1]
 * Output: True
 * Explanation:
 * i = 1, j = 3, k = 5. 
 * sum(0, i - 1) = sum(0, 0) = 1
 * sum(i + 1, j - 1) = sum(2, 2) = 1
 * sum(j + 1, k - 1) = sum(4, 4) = 1
 * sum(k + 1, n - 1) = sum(6, 6) = 1
 * 
 * 
 * 
 * Note:
 * 
 * ⁠1 
 * ⁠Elements in the given array will be in range [-1,000,000, 1,000,000]. 
 * 
 */
class Solution {
    public boolean splitArray(int[] nums) {
        int len = nums.length;
        if (len < 7) {
            return false;
        }
        int[] preSum = new int[len];
        int curSum = 0;
        for (int i = 0; i < len; i++) {
            curSum += nums[i];
            preSum[i] = curSum;
        }
        for (int j = 3; j < len - 3; j++) {
            Set<Integer> sums = new HashSet<>();
            for (int i = 1; i < j - 1; i++) {
                if (preSum[i - 1] == preSum[j - 1] - preSum[i]) {
                    sums.add(preSum[i - 1]);
                }
            }
            for (int k = j + 2; k < len - 1; k++) {
                if (preSum[k - 1] - preSum[j] == preSum[len - 1] - preSum[k]) {
                    if (sums.contains(preSum[k - 1] - preSum[j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
