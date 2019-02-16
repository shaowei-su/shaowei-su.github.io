/*
 * @lc app=leetcode id=246 lang=java
 *
 * [246] Strobogrammatic Number
 *
 * https://leetcode.com/problems/strobogrammatic-number/description/
 *
 * algorithms
 * Easy (41.51%)
 * Total Accepted:    47.7K
 * Total Submissions: 114.5K
 * Testcase Example:  '"69"'
 *
 * A strobogrammatic number is a number that looks the same when rotated 180
 * degrees (looked at upside down).
 * 
 * Write a function to determine if a number is strobogrammatic. The number is
 * represented as a string.
 * 
 * Example 1:
 * 
 * 
 * Input:  "69"
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:  "88"
 * Output: true
 * 
 * Example 3:
 * 
 * 
 * Input:  "962"
 * Output: false
 * 
 */
class Solution {
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
            return true;
        }
        int left = 0;
        int right = num.length() - 1;
        while (left < right) {
            if ((num.charAt(left) == num.charAt(right) && num.charAt(left) == '8')
                    || (num.charAt(left) == '6' && num.charAt(right) == '9')
                    || (num.charAt(left) == '9' && num.charAt(right) == '6')
                    || (num.charAt(left) == num.charAt(right) && num.charAt(left) == '1')
                    || (num.charAt(left) == num.charAt(right) && num.charAt(left) == '0')) {
                left++;
                right--;
                continue;
                    }
            return false;
        }
        if (left == right) {
            if (num.charAt(left) != '8' && num.charAt(left) != '1' && num.charAt(left) != '0') {
                return false;
            }
        }
        return true;
    }
}
