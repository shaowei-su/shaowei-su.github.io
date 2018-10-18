/*
 * [633] Sum of Square Numbers
 *
 * https://leetcode.com/problems/sum-of-square-numbers/description/
 *
 * algorithms
 * Easy (32.45%)
 * Total Accepted:    29K
 * Total Submissions: 89.2K
 * Testcase Example:  '5'
 *
 * 
 * Given a non-negative integer c, your task is to decide whether there're two
 * integers a and b such that a2 + b2 = c.
 * 
 * 
 * Example 1:
 * 
 * Input: 5
 * Output: True
 * Explanation: 1 * 1 + 2 * 2 = 5
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: 3
 * Output: False
 * 
 * 
 * 
 */
class Solution {
    /*public boolean judgeSquareSum(int c) {
        if (c < 0) {
            return false;
        }
        if (c == 0) {
            return true;
        }
        int sqrtC = (int) Math.sqrt(c);
        for (int i = 0; i <= sqrtC; i++) {
            int sqr = i * i;
            int remain = c - sqr;
            if (isSquare(remain)) {
                return true;
            }
        }
        return false;
    }
    public boolean isSquare(int c) {
        int sqrt = (int) Math.sqrt(c);
        return sqrt * sqrt == c;
    } */

    public boolean judgeSquareSum(int c) {
         if (c < 0) {
             return false;
         }
         if (c == 0) {
             return true;
         }
         int left = 0;
         int right = (int) Math.sqrt(c);
         while (left <= right) {
            int target = left * left + right * right;
            if (target == c) {
                return true;
            } else if (target < c) {
                left++;
            } else if (target > c) {
                right--;
            }
         }
         return false;
    }



}
