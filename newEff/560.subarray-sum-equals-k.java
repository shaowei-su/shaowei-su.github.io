/*
 * [560] Subarray Sum Equals K
 *
 * https://leetcode.com/problems/subarray-sum-equals-k/description/
 *
 * algorithms
 * Medium (40.78%)
 * Total Accepted:    65.9K
 * Total Submissions: 161.5K
 * Testcase Example:  '[1,1,1]\n2'
 *
 * Given an array of integers and an integer k, you need to find the total
 * number of continuous subarrays whose sum equals to k.
 * 
 * Example 1:
 * 
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * 
 * 
 * 
 * Note:
 * 
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the
 * integer k is [-1e7, 1e7].
 * 
 * 
 * 
 */
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> presum = new HashMap<>();
        presum.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (presum.containsKey(sum - k)) {
                count += presum.get(sum - k);
            }
            presum.put(sum, presum.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
    public int subarraySum2(int[] nums, int k) {
        int len = nums.length;
        int[] presum = new int[len + 1];
        int prev = 0;
        presum[0] = 0;
        for (int i = 1; i <= len; i++) {
            prev += nums[i - 1];
            presum[i] = prev;
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                if (presum[j] - presum[i] == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
