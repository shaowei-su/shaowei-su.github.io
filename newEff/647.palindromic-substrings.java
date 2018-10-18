/*
 * [647] Palindromic Substrings
 *
 * https://leetcode.com/problems/palindromic-substrings/description/
 *
 * algorithms
 * Medium (54.44%)
 * Total Accepted:    53.5K
 * Total Submissions: 98.2K
 * Testcase Example:  '"abc"'
 *
 * 
 * Given a string, your task is to count how many palindromic substrings in
 * this string.
 * 
 * 
 * 
 * The substrings with different start indexes or end indexes are counted as
 * different substrings even they consist of same characters. 
 * 
 * 
 * Example 1:
 * 
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * 
 * 
 * 
 * Note:
 * 
 * The input string length won't exceed 1000.
 * 
 * 
 */
class Solution {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) { 
            return 0;
        }
        char[] chs = s.toCharArray();
        int sum = chs.length;
        for (int i = 0; i < (chs.length * 2 - 1); i++) {
            sum += pld(chs, i);
        }
        return sum;
    }
    public int pld(char[] chs, int ind) {
        int count = 0;
        boolean middle = ind % 2 == 1;
        int left = middle ? ind - 1 : ind - 2;
        int right = middle ? ind + 1 : ind + 2;
        while (left >= 0 && right <= (chs.length * 2 - 1)) {
            if (chs[left / 2] == chs[right / 2]) {
                count++;
            } else {
                break;
            }
            left -= 2;
            right += 2;
        }
        return count;
    }
}
