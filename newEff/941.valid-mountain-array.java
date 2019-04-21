/*
 * @lc app=leetcode id=941 lang=java
 *
 * [941] Valid Mountain Array
 *
 * https://leetcode.com/problems/valid-mountain-array/description/
 *
 * algorithms
 * Easy (34.82%)
 * Total Accepted:    16.3K
 * Total Submissions: 46.6K
 * Testcase Example:  '[2,1]'
 *
 * Given an array A of integers, return true if and only if it is a valid
 * mountain array.
 * 
 * Recall that A is a mountain array if and only if:
 * 
 * 
 * A.length >= 3
 * There exists some i with 0 < i < A.length - 1 such that:
 * 
 * A[0] < A[1] < ... A[i-1] < A[i] 
 * A[i] > A[i+1] > ... > A[B.length - 1]
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [2,1]
 * Output: false
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [3,5,5]
 * Output: false
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: [0,3,2,1]
 * Output: true
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    public boolean validMountainArray(int[] A) {
        if (A == null || A.length < 3) {
            return false;
        }
        boolean up = false;
        boolean down = false;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                if (down) {
                    return false;
                }
                up = true;
            } else if (A[i] < A[i - 1]) {
                if (!up) {
                    return false;
                }
                down = true;
            } else {
                return false;
            }
        }
        return up && down;
    }
}
