/*
 * @lc app=leetcode id=161 lang=java
 *
 * [161] One Edit Distance
 *
 * https://leetcode.com/problems/one-edit-distance/description/
 *
 * algorithms
 * Medium (31.36%)
 * Total Accepted:    70K
 * Total Submissions: 222.6K
 * Testcase Example:  '"ab"\n"acb"'
 *
 * Given two strings s and t, determine if they are both one edit distance
 * apart.
 * 
 * Note: 
 * 
 * There are 3 possiblities to satisify one edit distance apart:
 * 
 * 
 * Insert a character into s to get t
 * Delete a character from s to get t
 * Replace a character of s to get t
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "ab", t = "acb"
 * Output: true
 * Explanation: We can insert 'c' into s to get t.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "cab", t = "ad"
 * Output: false
 * Explanation: We cannot get t from s by only one step.
 * 
 * Example 3:
 * 
 * 
 * Input: s = "1203", t = "1213"
 * Output: true
 * Explanation: We can replace '0' with '1' to get t.
 * 
 */
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        }
        int si = 0;
        int ti = 0;
        while (si < s.length() && ti < t.length()) {
            if (s.charAt(si) == t.charAt(ti)) {
                si++;
                ti++;
            } else {
                if (s.length() == t.length()) {
                    return s.substring(si + 1).equals(t.substring(ti + 1));
                } else if (s.length() > t.length()) {
                    return s.substring(si + 1).equals(t.substring(ti));
                } else {
                    return s.substring(si).equals(t.substring(ti + 1));
                }
            }
        }
        return Math.abs(s.length() - t.length()) == 1;

    }
    public boolean isOneEditDistance2(String s, String t) {
        return isOneEditDistanceHelper(s, t, true); 
    }
    public boolean isOneEditDistanceHelper(String s, String t, boolean first) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        int left = 0;
        int right = 0;
        while (left < s.length() && right < t.length()) {
            if (s.charAt(left) == t.charAt(right)) {
                left++;
                right++;
            } else if (first) {
                return isOneEditDistanceHelper(s.substring(left), t.substring(right + 1), false) || 
                    isOneEditDistanceHelper(s.substring(left + 1), t.substring(right), false) ||
                    isOneEditDistanceHelper(s.substring(left + 1), t.substring(right + 1), false);
            } else {
                return false;
            }
        }
        if (!first && left == right && s.length() == t.length()) {
            return true;
        }
        if (first && Math.abs(s.length() - t.length()) == 1) {
            return true;
        }
        return false;
    }
}
