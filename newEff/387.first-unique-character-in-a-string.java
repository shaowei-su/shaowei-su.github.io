/*
 * @lc app=leetcode id=387 lang=java
 *
 * [387] First Unique Character in a String
 *
 * https://leetcode.com/problems/first-unique-character-in-a-string/description/
 *
 * algorithms
 * Easy (48.43%)
 * Total Accepted:    207.1K
 * Total Submissions: 427.6K
 * Testcase Example:  '"leetcode"'
 *
 * 
 * Given a string, find the first non-repeating character in it and return it's
 * index. If it doesn't exist, return -1.
 * 
 * Examples:
 * 
 * s = "leetcode"
 * return 0.
 * 
 * s = "loveleetcode",
 * return 2.
 * 
 * 
 * 
 * 
 * Note: You may assume the string contain only lowercase letters.
 * 
 */
class Solution {
    public int firstUniqChar(String s) {
        LinkedHashMap<Character, Integer> orderMap = new LinkedHashMap<>();
        Set<Character> dups = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (dups.contains(s.charAt(i))) {
                orderMap.remove(s.charAt(i));
            } else {
                dups.add(s.charAt(i));
                orderMap.put(s.charAt(i), i);
            }
        }
        for (Map.Entry<Character, Integer> entry : orderMap.entrySet()) {
            return entry.getValue();
        }
        return -1;
    }
    public int firstUniqChar2(String s) {
        Map<Character, Integer> cMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            cMap.put(s.charAt(i), cMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        for(int i = 0; i < s.length(); i++) {
            if (cMap.containsKey(s.charAt(i)) && cMap.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}
