/*
 * [205] Isomorphic Strings
 *
 * https://leetcode.com/problems/isomorphic-strings/description/
 *
 * algorithms
 * Easy (35.53%)
 * Total Accepted:    159.8K
 * Total Submissions: 449.7K
 * Testcase Example:  '"egg"\n"add"'
 *
 * Given two strings s and t, determine if they are isomorphic.
 * 
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * 
 * All occurrences of a character must be replaced with another character while
 * preserving the order of characters. No two characters may map to the same
 * character but a character may map to itself.
 * 
 * Example 1:
 * 
 * 
 * Input: s = "egg", t = "add"
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "foo", t = "bar"
 * Output: false
 * 
 * Example 3:
 * 
 * 
 * Input: s = "paper", t = "title"
 * Output: true
 * 
 * Note:
 * You may assume both s and t have the same length.
 * 
 */
class Solution {
    public boolean isIsomorphic2(String s, String t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (map.get(a) == null) {
                map.put(a, b);
            } else {
                if (map.get(a).charValue() != b) {
                    return false;
                }
            }
        }
        Set<Character> vSet = new HashSet<>();
        vSet.addAll(map.values());
        if (map.keySet().size() != vSet.size()) return false;
        return true;
    }

    public boolean isIsomorphic(String s, String t) {
        int[] bits = new int[512];
        for (int i = 0; i < s.length(); i++) {
            if (bits[s.charAt(i)] != bits[t.charAt(i) + 256]) return false;
            bits[s.charAt(i)] = bits[t.charAt(i) + 256] = i + 1;
        }
        return true;
    }
}
