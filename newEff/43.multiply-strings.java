/*
 * @lc app=leetcode id=43 lang=java
 *
 * [43] Multiply Strings
 *
 * https://leetcode.com/problems/multiply-strings/description/
 *
 * algorithms
 * Medium (29.59%)
 * Total Accepted:    185.7K
 * Total Submissions: 618.5K
 * Testcase Example:  '"2"\n"3"'
 *
 * Given two non-negative integers num1 and num2 represented as strings, return
 * the product of num1 and num2, also represented as a string.
 * 
 * Example 1:
 * 
 * 
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * 
 * Example 2:
 * 
 * 
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * 
 * 
 * Note:
 * 
 * 
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0
 * itself.
 * You must not use any built-in BigInteger library or convert the inputs to
 * integer directly.
 * 
 * 
 */
class Solution {
    public String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int[] res = new int[len1 + len2];
        char[] arr1 = num1.toCharArray();
        char[] arr2 = num2.toCharArray();
        int pos2 = len2 - 1;
        while (pos2 >= 0) {
            int cur2 = arr2[pos2] - '0';
            int carry = 0;
            int pos1 = len1 - 1;
            int pos3 = pos1 + pos2 + 1;
            while (pos1 >= 0) {
                int cur1 = arr1[pos1] - '0';
                int tmp = carry + cur1 * cur2 + res[pos3];
                res[pos3--] = tmp % 10;
                carry = tmp / 10;
                pos1--;
            }
            while (carry > 0) {
                int tmp = res[pos3] + carry;
                res[pos3] = tmp % 10;
                carry = tmp / 10;
                pos3--;
            }
            pos2--;
        }
        StringBuilder sb = new StringBuilder();
        boolean started = false;
        for (int i = 0; i < len1 + len2; i++) {
            if (res[i] == 0 && !started) {
                continue;
            } else {
                started = true;
                sb.append(res[i]);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
