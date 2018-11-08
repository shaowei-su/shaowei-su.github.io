/*
 * [892] Shortest Subarray with Sum at Least K
 *
 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description/
 *
 * algorithms
 * Hard (19.55%)
 * Total Accepted:    5.8K
 * Total Submissions: 29.8K
 * Testcase Example:  '[1]\n1'
 *
 * Return the length of the shortest, non-empty, contiguous subarray of A with
 * sum at least K.
 * 
 * If there is no non-empty subarray with sum at least K, return -1.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: A = [1], K = 1
 * Output: 1
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: A = [1,2], K = 4
 * Output: -1
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: A = [2,-1,2], K = 3
 * Output: 3
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= A.length <= 50000
 * -10 ^ 5 <= A[i] <= 10 ^ 5
 * 1 <= K <= 10 ^ 9
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    public int shortestSubarray(int[] A, int K) {
        if (A == null || A.length == 0) return -1;
        TreeMap<Long, Integer> sumMap = new TreeMap<>();
        int minLen = Integer.MAX_VALUE;
        long curSum = 0;
        for (int i = 0; i < A.length; i++) {
            curSum += A[i];
            Long prevSum = sumMap.floorKey(curSum - K);
            if (curSum >= K && minLen == Integer.MAX_VALUE) {
                minLen = i + 1;
            }
            while (prevSum != null) {
                if (i - sumMap.get(prevSum) < minLen) {
                    minLen = i - sumMap.get(prevSum);
                }
                sumMap.remove(prevSum);
                prevSum = sumMap.floorKey(prevSum);
            }
            sumMap.put(curSum, i);
        }
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    
    }
    public int shortestSubarray2(int[] A, int K) {
        if (A == null || A.length == 0) return -1;
        if (A.length == 1) return A[0] >= K ? 1 : -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < A.length - 1; i++) {
            int curSum = A[i];
            if (curSum >= K) return 1;
            for (int j = i + 1; j < A.length; j++) {
                curSum += A[j];
                if (curSum >= K) {
                    min = Math.min(min, j - i + 1);
                    break;
                }
            }
        }
        return min != Integer.MAX_VALUE ? min : -1;
    }
}
