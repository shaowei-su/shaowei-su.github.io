/*
 * [151] Reverse Words in a String
 *
 * https://leetcode.com/problems/reverse-words-in-a-string/description/
 *
 * algorithms
 * Medium (15.73%)
 * Total Accepted:    225.7K
 * Total Submissions: 1.4M
 * Testcase Example:  '"the sky is blue"'
 *
 * Given an input string, reverse the string word by word.
 * 
 * Example:  
 * 
 * 
 * Input: "the sky is blue",
 * Output: "blue is sky the".
 * 
 * 
 * Note:
 * 
 * 
 * A word is defined as a sequence of non-space characters.
 * Input string may contain leading or trailing spaces. However, your reversed
 * string should not contain leading or trailing spaces.
 * You need to reduce multiple spaces between two words to a single space in
 * the reversed string.
 * 
 * 
 * Follow up: For C programmers, try to solve it in-place in O(1) space.
 * 
 */
public class Solution {
    public String reverseWords2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String[] parts = s.trim().split("\\s+");
        for (int i = parts.length - 1; i >= 0; i--) {
            sb.append(parts[i]);
            sb.append(" ");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public String reverseWords(String s) {
        String shat = s.trim();
        char[] arr = shat.toCharArray();
        int start = 0;
        for (int i = 0; i < arr.length; i++) {
           if (arr[i] == ' ') {
               if (i > 0 && arr[i - 1] == ' ') {
                   start = i + 1;
                   continue;
               }
               reverse(arr, start, i - 1);
               start = i + 1;
           }
        }
        if (start < shat.length() - 1) {
            reverse(arr, start, shat.length() - 1);
        }
        reverse(arr, 0, shat.length() - 1);
        return new String(arr);
    }

    public void reverse(char[] arr, int s, int e) {
        while (s < e) {
            char tmp = arr[s];
            arr[s] = arr[e];
            arr[e] = tmp;
            s++;
            e--;
        }
        return;
    }
}
