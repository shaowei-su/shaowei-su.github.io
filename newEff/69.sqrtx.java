/*
 * [69] Sqrt(x)
 *
 * https://leetcode.com/problems/sqrtx/description/
 *
 * algorithms
 * Easy (29.58%)
 * Total Accepted:    280.4K
 * Total Submissions: 947.3K
 * Testcase Example:  '4'
 *
 * Implement int sqrt(int x).
 * 
 * Compute and return the square root of x, where x is guaranteed to be a
 * non-negative integer.
 * 
 * Since the return type is an integer, the decimal digits are truncated and
 * only the integer part of the result is returned.
 * 
 * Example 1:
 * 
 * 
 * Input: 4
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since 
 * the decimal part is truncated, 2 is returned.
 * 
 * 
 */
class Solution {
    public int mySqrt(int x) {
        long left = 0;
        long right = x / 2 + 1;
        if (x <= 0 ) {
            return 0;
        }
        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            long pow1 = mid * mid;
            long pow2 = (mid + 1) * (mid + 1);
            if (pow1 == x) {
                return (int) mid;
            } else if (pow2 == x) {
                return (int) mid + 1;
            } else if (pow1 < x && pow2 > x) {
                return (int) mid;
            }
            if (pow1 < x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (right * right == x) {
            return (int) right;
        }
        return (int) left;
    }
    public int mySqrt2(int x) {
        int left = 0;
        int right = x;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            double cur = (double) mid * mid;
            double nextCur = (double) (mid + 1) * (mid + 1);
            if (cur == x) {
                return mid;
            }
            if (cur < x && nextCur > x) {
                return mid;
            }
            if (cur > x) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (left * left == x) return left;
        if (right * right == x) return right;
        return left;
    }
}
