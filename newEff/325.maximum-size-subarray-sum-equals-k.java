/*
 * @lc app=leetcode id=325 lang=java
 *
 * [325] Maximum Size Subarray Sum Equals k
 *
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/description/
 *
 * algorithms
 * Medium (44.14%)
 * Total Accepted:    71K
 * Total Submissions: 160.5K
 * Testcase Example:  '[1,-1,5,-2,3]\n3'
 *
 * Given an array nums and a target value k, find the maximum length of a
 * subarray that sums to k. If there isn't one, return 0 instead.
 * 
 * Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit
 * signed integer range.
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1, -1, 5, -2, 3], k = 3
 * Output: 4 
 * Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [-2, -1, 2, 1], k = 1
 * Output: 2 
 * Explanation: The subarray [-1, 2] sums to 1 and is the longest.
 * 
 * Follow Up:
 * Can you do it in O(n) time?
 * 
 */
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int curSum = 0;
        Map<Integer, List<Integer>> sumMap = new HashMap<>();
        sumMap.put(0, new ArrayList<Integer>(Arrays.asList(-1)));
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            sumMap.computeIfAbsent(curSum, l -> new ArrayList<Integer>()).add(i);
        }
        int max = 0;
        for (Integer left : sumMap.keySet()) {
            if (left + k == left && sumMap.get(left).size() > 1) {
                int listMin = Integer.MAX_VALUE;
                int listMax = Integer.MIN_VALUE;
                for (Integer i : sumMap.get(left)) {
                    listMin = Math.min(listMin, i);
                    listMax = Math.max(listMax, i);
                }
                int curMax = listMax - listMin;
                max = Math.max(max, curMax);
            } else if (sumMap.containsKey(left + k)) {
                int listMin = Integer.MAX_VALUE;
                for (Integer i : sumMap.get(left)) {
                    listMin = Math.min(listMin, i);
                }
                int curMax = -1;
                for (Integer i : sumMap.get(left + k)) {
                    if (i < listMin) continue;
                    curMax = Math.max(curMax, i - listMin);
                }
                max = Math.max(curMax, max);
            }
        }
        return max;
    }
}
