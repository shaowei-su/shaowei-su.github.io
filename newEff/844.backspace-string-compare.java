/*
 * [874] Backspace String Compare
 *
 * https://leetcode.com/problems/backspace-string-compare/description/
 *
 * algorithms
 * Easy (43.35%)
 * Total Accepted:    24.7K
 * Total Submissions: 57K
 * Testcase Example:  '"ab#c"\n"ad#c"'
 *
 * Given two strings S and T, return if they are equal when both are typed into
 * empty text editors. # means a backspace character.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 * 
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S and T only contain lowercase letters and '#' characters.
 * 
 * 
 * Follow up:
 * 
 * 
 * Can you solve it in O(N) time and O(1) space?
 * 
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    public boolean backspaceCompare(String S, String T) {
        if (S == null || T == null) {
            return false;
        }
        int siter = S.length() - 1;
        int titer = T.length() - 1;
        while (siter >= 0 || titer >= 0) {
            int scount = 0;
            while (siter >= 0 && (scount > 0 || S.charAt(siter) == '#')) {
                if (S.charAt(siter) == '#') {
                    scount++;
                } else {
                    scount--;
                }
                siter--;
            }
            scount = 0;
            while (titer >= 0 && (scount > 0 || T.charAt(titer) == '#')) {
                if (T.charAt(titer) == '#') {
                    scount++;
                } else {
                    scount--;
                }
                titer--;
            }
            if (titer < 0 && siter < 0) {
                return true;
            }
            if (titer < 0 || siter < 0) {
                return false;
            }
            if (T.charAt(titer--) != S.charAt(siter--)) {
                return false;
            }
        }
        return true;
    }
}
