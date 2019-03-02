/*
 * @lc app=leetcode id=128 lang=java
 *
 * [128] Longest Consecutive Sequence
 *
 * https://leetcode.com/problems/longest-consecutive-sequence/description/
 *
 * algorithms
 * Hard (40.43%)
 * Total Accepted:    190.2K
 * Total Submissions: 465.9K
 * Testcase Example:  '[100,4,200,1,3,2]'
 *
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 * 
 * Your algorithm should run in O(n) complexity.
 * 
 * Example:
 * 
 * 
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4].
 * Therefore its length is 4.
 * 
 * 
 */
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> vals = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int res = 1;
        for (int num : nums) {
            if (vals.contains(num - 1)) {
                continue;
            }
            int right = num + 1;
            while (vals.contains(right)) {
                right = right + 1;
            }
            res = Math.max(res, right - num);
        }
        return res;


    }
    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int n : nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        int[] buckets = new int[max - min + 1];
        for (int n : nums) {
            buckets[n - min]++;
        }
        int res = 1;
        for (int i = min; i <= max; i++) {
            if (buckets[i - min] != 0) {
                count++;
                res = Math.max(res, count);
            } else {
                count = 0;
            }
        }
        return res;
    }
}
