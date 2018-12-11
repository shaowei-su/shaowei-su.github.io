/*
 * [523] Continuous Subarray Sum
 *
 * https://leetcode.com/problems/continuous-subarray-sum/description/
 *
 * algorithms
 * Medium (23.81%)
 * Total Accepted:    51.2K
 * Total Submissions: 215K
 * Testcase Example:  '[23,2,4,6,7]\n6'
 *
 * 
 * Given a list of non-negative numbers and a target integer k, write a
 * function to check if the array has a continuous subarray of size at least 2
 * that sums up to the multiple of k, that is, sums up to n*k where n is also
 * an integer.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up
 * to 6.
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5
 * and sums up to 42.
 * 
 * 
 * 
 * Note:
 * 
 * The length of the array won't exceed 10,000.
 * You may assume the sum of all the numbers is in the range of a signed 32-bit
 * integer.
 * 
 * 
 */
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, -1);
        int runningSum = 0;
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            if (k != 0) {
                runningSum %= k;
            }
            if (sumMap.containsKey(runningSum)) {
                if (i - sumMap.get(runningSum) > 1) {
                    return true;
                }
            } else {
                sumMap.put(runningSum, i);
            }
        }
        return false;

    }
    public boolean checkSubarraySum2(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        int len = nums.length;
        int[] presum = new int[len + 1];
        presum[0] = 0;
        for (int i = 1; i < len + 1; i++) {
            presum[i] = presum[i - 1] + nums[i - 1];
        }
        for (int i = 2; i < len + 1; i++) {
            for (int j = 0; j < i - 1; j++) {
                if (k == 0) {
                    if (presum[i] == presum[j]) {
                        return true;
                    }
                    continue;
                }
                if ((presum[i] - presum[j]) % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
