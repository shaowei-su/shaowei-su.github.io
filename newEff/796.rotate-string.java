/*
 * @lc app=leetcode id=796 lang=java
 *
 * [796] Rotate String
 *
 * https://leetcode.com/problems/rotate-string/description/
 *
 * algorithms
 * Easy (48.83%)
 * Total Accepted:    37.2K
 * Total Submissions: 76.1K
 * Testcase Example:  '"abcde"\n"cdeab"'
 *
 * We are given two strings, A and B.
 * 
 * A shift on A consists of taking string A and moving the leftmost character
 * to the rightmost position. For example, if A = 'abcde', then it will be
 * 'bcdea' after one shift on A. Return True if and only if A can become B
 * after some number of shifts on A.
 * 
 * 
 * Example 1:
 * Input: A = 'abcde', B = 'cdeab'
 * Output: true
 * 
 * Example 2:
 * Input: A = 'abcde', B = 'abced'
 * Output: false
 * 
 * 
 * Note:
 * 
 * 
 * A and B will have length at most 100.
 * 
 * 
 */
class Solution {
    public boolean rotateString(String A, String B) {
        if (A == null || B == null) {
            return false;
        }
        if (A.length() != B.length()) {
            return false;
        }
        if (A.length() == 0) {
            return true;
        }
        for (int i = 0; i < A.length(); i++) {
            if (B.equals(A.substring(i) + A.substring(0, i))) {
                return true;
            }
        }
        return false;
    }
}
