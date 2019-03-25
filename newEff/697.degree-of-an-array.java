/*
 * @lc app=leetcode id=697 lang=java
 *
 * [697] Degree of an Array
 *
 * https://leetcode.com/problems/degree-of-an-array/description/
 *
 * algorithms
 * Easy (48.39%)
 * Total Accepted:    43.8K
 * Total Submissions: 88.4K
 * Testcase Example:  '[1,2,2,3,1]'
 *
 * Given a non-empty array of non-negative integers nums, the degree of this
 * array is defined as the maximum frequency of any one of its elements.
 * Your task is to find the smallest possible length of a (contiguous) subarray
 * of nums, that has the same degree as nums.
 * 
 * Example 1:
 * 
 * Input: [1, 2, 2, 3, 1]
 * Output: 2
 * Explanation: 
 * The input array has a degree of 2 because both elements 1 and 2 appear
 * twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [1,2,2,3,1,4,2]
 * Output: 6
 * 
 * 
 * 
 * Note:
 * nums.length will be between 1 and 50,000.
 * nums[i] will be an integer between 0 and 49,999.
 * 
 */
class Solution {
    class NumFreq {
        int num;
        int freq;
        int startInd;
        int endInd;
        public NumFreq(int num, int ind) {
            this.num = num;
            this.freq = 1;
            this.startInd = ind;
            this.endInd = ind;
        }
    }
     public int findShortestSubArray(int[] nums) {
         Map<Integer, NumFreq> freqMap = new HashMap<>();
         for (int i = 0; i < nums.length; i++) {
             if (!freqMap.containsKey(nums[i])) {
                 freqMap.put(nums[i], new NumFreq(nums[i], i));
             }
             freqMap.get(nums[i]).freq++;
             freqMap.get(nums[i]).endInd = i;
         }
         int maxFreq = -1;
         int minLen = Integer.MAX_VALUE;
         for (NumFreq nf : freqMap.values()) {
             if (nf.freq > maxFreq) {
                 maxFreq = nf.freq;
                 minLen = nf.endInd - nf.startInd + 1;
             } else if (nf.freq == maxFreq && (nf.endInd - nf.startInd + 1) < minLen) {
                 minLen = nf.endInd - nf.startInd + 1;
             }
         }
         return minLen;
     }
    public int findShortestSubArray2(int[] nums) {
        Map<Integer, NumFreq> freqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!freqMap.containsKey(nums[i])) {
                freqMap.put(nums[i], new NumFreq(nums[i], i));
            }
            freqMap.get(nums[i]).freq++;
            freqMap.get(nums[i]).endInd = i;
        }
        PriorityQueue<NumFreq> pq = new PriorityQueue<>((a, b) -> {
            if (a.freq == b.freq) {
                return (a.endInd - a.startInd) - (b.endInd - b.startInd);
            } else {
                return b.freq - a.freq;
            }
        });
        for (NumFreq nf : freqMap.values()) {
            pq.offer(nf);
        }
        NumFreq top = pq.poll();
        return top.endInd - top.startInd + 1;
    }
}
