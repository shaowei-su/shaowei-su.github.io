/*
 * @lc app=leetcode id=209 lang=java
 *
 * [209] Minimum Size Subarray Sum
 *
 * https://leetcode.com/problems/minimum-size-subarray-sum/description/
 *
 * algorithms
 * Medium (33.93%)
 * Total Accepted:    161.1K
 * Total Submissions: 471.8K
 * Testcase Example:  '7\n[2,3,1,2,4,3]'
 *
 * Given an array of n positive integers and a positive integer s, find the
 * minimal length of a contiguous subarray of which the sum ≥ s. If there isn't
 * one, return 0 instead.
 * 
 * Example: 
 * 
 * 
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem
 * constraint.
 * 
 * Follow up:
 * 
 * If you have figured out the O(n) solution, try coding another solution of
 * which the time complexity is O(n log n). 
 * 
 */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int sum = 0, i = 0, j = 0, res = Integer.MAX_VALUE;
        while (j < nums.length) {
           sum += nums[j++];
           while (sum >= s) {
               res = Math.min(res, j - i);
               sum -= nums[i++];
           }
        }
        return res == Integer.MAX_VALUE ? 0 : res;

    }
    public int minSubArrayLen3(int s, int[] nums) {
         if (nums == null || nums.length == 0) {
             return 0;
         }
         int[] pre = new int[nums.length + 1];
         pre[0] = 0;
         TreeMap<Integer, Integer> tmap = new TreeMap<>();
         tmap.put(0, 0);
         for (int i = 1; i < nums.length + 1; i++) {
             pre[i] = pre[i - 1] + nums[i - 1];
             tmap.put(pre[i], i);
         }
         int res = Integer.MAX_VALUE;
         for (Map.Entry<Integer, Integer> value : tmap.entrySet()) {
             if (tmap.ceilingKey(value.getKey() + s) != null) {
                 if (tmap.get(tmap.ceilingKey(value.getKey() + s)) - value.getValue() < res) {
                     res = tmap.get(tmap.ceilingKey(value.getKey() + s)) - value.getValue();
                 }
             }
         }
         return res == Integer.MAX_VALUE ? 0 : res;


    }
    public int minSubArrayLen2(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] pre = new int[nums.length + 1];
        pre[0] = 0;
        for (int i = 1; i < nums.length + 1; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length + 1; j++) {
                if (pre[j] - pre[i] >= s) {
                    res = Math.min(res, j - i);
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
