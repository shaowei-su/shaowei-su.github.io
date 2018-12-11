/*
 * [525] Contiguous Array
 *
 * https://leetcode.com/problems/contiguous-array/description/
 *
 * algorithms
 * Medium (42.07%)
 * Total Accepted:    33.3K
 * Total Submissions: 79.3K
 * Testcase Example:  '[0,1]'
 *
 * Given a binary array, find the maximum length of a contiguous subarray with
 * equal number of 0 and 1. 
 * 
 * 
 * Example 1:
 * 
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of
 * 0 and 1.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal
 * number of 0 and 1.
 * 
 * 
 * 
 * Note:
 * The length of the given binary array will not exceed 50,000.
 * 
 */
class Solution {
    public int findMaxLength(int[] nums) {
        int len = nums.length;
        int[] onemore = new int[len];
        int cur = 0;
        int longest = 0;
        Map<Integer, Integer> posMap = new HashMap<>();
        posMap.put(0, -1);
        for (int i = 0; i < len; i++) {
            cur = nums[i] == 1 ? cur + 1 : cur - 1;
            if (posMap.containsKey(cur)) {
                longest = Math.max(longest, i - posMap.get(cur));
            } else {
                posMap.put(cur, i);
            }
        }
        return longest;
    }
}
