/*
 * @lc app=leetcode id=125 lang=java
 *
 * [125] Valid Palindrome
 *
 * https://leetcode.com/problems/valid-palindrome/description/
 *
 * algorithms
 * Easy (29.66%)
 * Total Accepted:    320.7K
 * Total Submissions: 1.1M
 * Testcase Example:  '"A man, a plan, a canal: Panama"'
 *
 * Given a string, determine if it is a palindrome, considering only
 * alphanumeric characters and ignoring cases.
 * 
 * Note: For the purpose of this problem, we define empty string as valid
 * palindrome.
 * 
 * Example 1:
 * 
 * 
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "race a car"
 * Output: false
 * 
 * 
 */
class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char l = s.charAt(left);
            char r = s.charAt(right);
            boolean isLL = Character.isLetter(l);
            boolean isRL = Character.isLetter(r);
            boolean isLD = Character.isDigit(l);
            boolean isRD = Character.isDigit(r);
            if (!isLL && !isLD) {
                left++;
                continue;
            }
            if (!isRL && !isRD) {
                right--;
                continue;
            }
            if (isLL && isRL) {
                if (l == r || Math.abs(l - r) == 32) {
                    left++;
                    right--;
                    continue;
                }
            }
            if (isLD && isRD) {
                if (l == r) {
                    left++;
                    right--;
                    continue;
                }
            }
            return false;
        }
        return true;
    }
}
