/*
 * [415] Add Strings
 *
 * https://leetcode.com/problems/add-strings/description/
 *
 * algorithms
 * Easy (42.64%)
 * Total Accepted:    79.1K
 * Total Submissions: 185.4K
 * Testcase Example:  '"0"\n"0"'
 *
 * Given two non-negative integers num1 and num2 represented as string, return
 * the sum of num1 and num2.
 * 
 * Note:
 * 
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to
 * integer directly.
 * 
 * 
 */
class Solution {
    public String addStrings(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return "";
        }
        char[] arr1 = new StringBuilder(num1).reverse().toString().toCharArray();
        char[] arr2 = new StringBuilder(num2).reverse().toString().toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        int carry = 0;
        while (i < arr1.length && j < arr2.length) {
            int left = arr1[i] - '0';
            int right = arr2[j] - '0';
            int cur = left + right + carry;
            sb.append(cur % 10);
            carry = cur / 10;
            i++;
            j++;
        }
        while (i < arr1.length) {
            int left = arr1[i] - '0';
            int cur = left + carry;
            sb.append(cur % 10);
            carry = cur / 10;
            i++;
        }
        while (j < arr2.length) {
             int left = arr2[j] - '0';
             int cur = left + carry;
             sb.append(cur % 10);
             carry = cur / 10;
             j++;
         }
         if (carry != 0) {
             sb.append(carry);
         }
         return sb.reverse().toString();
    }
}
