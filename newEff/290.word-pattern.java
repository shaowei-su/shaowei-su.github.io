/*
 * @lc app=leetcode id=290 lang=java
 *
 * [290] Word Pattern
 *
 * https://leetcode.com/problems/word-pattern/description/
 *
 * algorithms
 * Easy (34.34%)
 * Total Accepted:    128.4K
 * Total Submissions: 373K
 * Testcase Example:  '"abba"\n"dog cat cat dog"'
 *
 * Given a pattern and a string str, find if str follows the same pattern.
 * 
 * Here follow means a full match, such that there is a bijection between a
 * letter in pattern and a non-empty word in str.
 * 
 * Example 1:
 * 
 * 
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 * 
 * Example 2:
 * 
 * 
 * Input:pattern = "abba", str = "dog cat cat fish"
 * Output: false
 * 
 * Example 3:
 * 
 * 
 * Input: pattern = "aaaa", str = "dog cat cat dog"
 * Output: false
 * 
 * Example 4:
 * 
 * 
 * Input: pattern = "abba", str = "dog dog dog dog"
 * Output: false
 * 
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains
 * lowercase letters separated by a single space.
 */
class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> c2s = new HashMap<>();
        Map<String, Character> s2c = new HashMap<>();
        char[] chars = pattern.toCharArray();
        String[] strs = str.split("\\s+");
        if (chars.length != strs.length) {
            return false;
        }
        for (int i = 0; i < chars.length; i++) {
            if (c2s.containsKey(chars[i])) {
                if (!c2s.get(chars[i]).equals(strs[i])) {
                    return false;
                }
            } else {
                if (c2s.values().contains(strs[i])) {
                    return false;
                }
                c2s.put(chars[i], strs[i]);
            }
        }
        return true;

            
    }
}
