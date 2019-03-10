/*
 * @lc app=leetcode id=66 lang=java
 *
 * [66] Plus One
 *
 * https://leetcode.com/problems/plus-one/description/
 *
 * algorithms
 * Easy (40.44%)
 * Total Accepted:    355.5K
 * Total Submissions: 873.4K
 * Testcase Example:  '[1,2,3]'
 *
 * Given a non-empty array of digits representing a non-negative integer, plus
 * one to the integer.
 * 
 * The digits are stored such that the most significant digit is at the head of
 * the list, and each element in the array contain a single digit.
 * 
 * You may assume the integer does not contain any leading zero, except the
 * number 0 itself.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 * 
 * 
 */
class Solution {
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return null;
        }
        int len = digits.length;
        int[] res = new int[len + 1];
        int pos = len - 1;
        int carry = 1;
        while (pos >= 0) {
            int val = digits[pos] + carry;
            res[pos + 1] = val % 10;
            carry = val / 10;
            pos--;
        }
        if (carry != 0) {
            res[0] = 1;
            return res;
        } else {
            return Arrays.copyOfRange(res, 1, len + 1);
        }
    }
}
