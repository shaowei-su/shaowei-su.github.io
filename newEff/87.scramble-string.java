/*
 * @lc app=leetcode id=87 lang=java
 *
 * [87] Scramble String
 *
 * https://leetcode.com/problems/scramble-string/description/
 *
 * algorithms
 * Hard (30.84%)
 * Total Accepted:    86.2K
 * Total Submissions: 277.4K
 * Testcase Example:  '"great"\n"rgeat"'
 *
 * Given a string s1, we may represent it as a binary tree by partitioning it
 * to two non-empty substrings recursively.
 * 
 * Below is one possible representation of s1 = "great":
 * 
 * 
 * ⁠   great
 * ⁠  /    \
 * ⁠ gr    eat
 * ⁠/ \    /  \
 * g   r  e   at
 * ⁠          / \
 * ⁠         a   t
 * 
 * 
 * To scramble the string, we may choose any non-leaf node and swap its two
 * children.
 * 
 * For example, if we choose the node "gr" and swap its two children, it
 * produces a scrambled string "rgeat".
 * 
 * 
 * ⁠   rgeat
 * ⁠  /    \
 * ⁠ rg    eat
 * ⁠/ \    /  \
 * r   g  e   at
 * ⁠          / \
 * ⁠         a   t
 * 
 * 
 * We say that "rgeat" is a scrambled string of "great".
 * 
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it
 * produces a scrambled string "rgtae".
 * 
 * 
 * ⁠   rgtae
 * ⁠  /    \
 * ⁠ rg    tae
 * ⁠/ \    /  \
 * r   g  ta  e
 * ⁠      / \
 * ⁠     t   a
 * 
 * 
 * We say that "rgtae" is a scrambled string of "great".
 * 
 * Given two strings s1 and s2 of the same length, determine if s2 is a
 * scrambled string of s1.
 * 
 * Example 1:
 * 
 * 
 * Input: s1 = "great", s2 = "rgeat"
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s1 = "abcde", s2 = "caebd"
 * Output: false
 * 
 */
class Solution {
    Map<String, Boolean> mem = new HashMap<>();
    public boolean isScramble2(String s1, String s2) {
         if (s1 == null || s1 == null || s1.length() != s2.length() || s1.length() == 0 || s2.length() == 0) {
             return false;
         }
         if (s1.length() == 1 && s1.equals(s2)) {
             return true;
         }
         if (s1.length() == 1 && !s1.equals(s2)) {
             return false;
         }
         if (mem.containsKey(s1 + "-" + s2)) {
             return mem.get(s1 + "-" + s2);
         }
         int len = s1.length();
         for (int i = 1; i < len; i++) {
             if ((isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i, len), s2.substring(i, len))) ||
                     (isScramble(s1.substring(len - i, len), s2.substring(0, i)) && isScramble(s1.substring(0, len - i), s2.substring(i, len)))) {
                 mem.put(s1 + "-" + s2, true);
                 return true;
                     }
         }
         mem.put(s1 + "-" + s2, false);
         return false;
    }
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s1 == null || s1.length() != s2.length() || s1.length() == 0 || s2.length() == 0) {
            return false;
        }
        if (s1.length() == 1 && s1.equals(s2)) {
            return true;
        }
        int len = s1.length();
        boolean[][][] dp = new boolean[len][len][len + 1];
        // dp[i][j][k]: substring[i, i + k) can match substring[j, j + k)
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        for (int sub = 2; sub <= len; sub++) {
            for (int i = 0; i < len - sub + 1; i++) {
                for (int j = 0; j < len - sub + 1; j++) {
                    for (int k = 1; k < sub; k++) {
                        dp[i][j][sub] |= (dp[i][j][k] && dp[i + k][j + k][sub - k]) || (dp[i][j + sub - k][k] && dp[i + k][j][sub - k]);
                    }
                }
            }
        }
        return dp[0][0][len];
    }

    public boolean sameChars(String s1, String s2) {
        int[] count = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        boolean res = true;
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return res;
    }
}
