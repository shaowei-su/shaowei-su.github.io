/*
 * @lc app=leetcode id=186 lang=java
 *
 * [186] Reverse Words in a String II
 *
 * https://leetcode.com/problems/reverse-words-in-a-string-ii/description/
 *
 * algorithms
 * Medium (35.20%)
 * Total Accepted:    59.7K
 * Total Submissions: 165.8K
 * Testcase Example:  '["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]'
 *
 * Given an input string , reverse the string word by word. 
 * 
 * Example:
 * 
 * 
 * Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * 
 * Note: 
 * 
 * 
 * A word is defined as a sequence of non-space characters.
 * The input string does not contain leading or trailing spaces.
 * The words are always separated by a single space.
 * 
 * 
 * Follow up: Could you do it in-place without allocating extra space?
 * 
 */
class Solution {
    public void reverseWords(char[] str) {
        if (str == null || str.length == 0) {
            return ;
        }
        reverse(str, 0, str.length - 1);
        int last = 0;
        int cur = 0;
        while (cur < str.length) {
            if (str[cur] != ' ') {
                cur++;
                continue;
            }
            reverse(str, last, cur - 1);
            cur++;
            last = cur;
        }
        reverse(str, last, cur - 1);

    }
    public void reverse(char[] str, int i, int j) {
        while (i < j) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
            i++;
            j--;
        }
    }
}
