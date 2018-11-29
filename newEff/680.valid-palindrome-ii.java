/*
 * [680] Valid Palindrome II
 *
 * https://leetcode.com/problems/valid-palindrome-ii/description/
 *
 * algorithms
 * Easy (33.10%)
 * Total Accepted:    52.4K
 * Total Submissions: 158.3K
 * Testcase Example:  '"aba"'
 *
 * 
 * Given a non-empty string s, you may delete at most one character.  Judge
 * whether you can make it a palindrome.
 * 
 * 
 * Example 1:
 * 
 * Input: "aba"
 * Output: True
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * 
 * 
 * 
 * Note:
 * 
 * The string will only contain lowercase characters a-z.
 * The maximum length of the string is 50000.
 * 
 * 
 */
class Solution {
    public boolean validPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return isPan(s.substring(l, r)) || isPan(s.substring(l + 1, r + 1));
            }
            l++;
            r--;
        }
        return true;
    }

    public boolean validPalindrome3(String s) {
        if (isPan(s)) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                if (isPan(s.substring(i + 1))) {
                    return true;
                }
            } else if (i == s.length() - 1) {
                if (isPan(s.substring(0, s.length() - 1))) {
                    return true;
                }
            } else {
                if (isPan(s.substring(0, i) + s.substring(i + 1))) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean isPan(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public boolean validPalindrome2(String s) {
        if (s == null || s.length()  == 0) {
            return false;
        }
        boolean skip = false;
        return recur(s, skip);
    }
    public boolean recur(String s, boolean skip) {
        if (s.length() <= 1) {
            return true;
        }
        if (skip) {
            return s.charAt(0) == s.charAt(s.length() - 1) && recur(s.substring(1, s.length() - 1), skip);
        } else {
            if (s.charAt(0) == s.charAt(s.length() - 1)) {
                return recur(s.substring(1, s.length() - 1), skip);
            } else {
                return recur(s.substring(0, s.length() - 1), true) || recur(s.substring(1, s.length()), true);
            }
        }
    }
}
