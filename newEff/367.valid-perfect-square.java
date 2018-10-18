/*
 * [367] Valid Perfect Square
 *
 * https://leetcode.com/problems/valid-perfect-square/description/
 *
 * algorithms
 * Easy (38.85%)
 * Total Accepted:    82.7K
 * Total Submissions: 212.9K
 * Testcase Example:  '16'
 *
 * Given a positive integer num, write a function which returns True if num is
 * a perfect square else False.
 * 
 * Note: Do not use any built-in library function such as sqrt.
 * 
 * Example 1:
 * 
 * 
 * 
 * Input: 16
 * Output: true
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 14
 * Output: false
 * 
 * 
 * 
 * 
 */
class Solution {
    public boolean isPerfectSquare(int num) {
        if (num < 0) {
            return false;
        }
        if (num == 0) {
            return true;
        }
        long left = 0;
        long right = num;
        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            long sqr = mid * mid;
            if (sqr < num) {
                left = mid;
            } else if (sqr > num) {
                right = mid;
            } else if (sqr == num) {
                return true;
            }
        }
        if (left * left == num) {
            return true;
        }
        if (right * right == num) {
            return true;
        }
        return false;
    }
}
