/*
 * [76] Minimum Window Substring
 *
 * https://leetcode.com/problems/minimum-window-substring/description/
 *
 * algorithms
 * Hard (28.36%)
 * Total Accepted:    179.4K
 * Total Submissions: 631.2K
 * Testcase Example:  '"ADOBECODEBANC"\n"ABC"'
 *
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 * 
 * Example:
 * 
 * 
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * 
 * 
 * Note:
 * 
 * 
 * If there is no such window in S that covers all characters in T, return the
 * empty string "".
 * If there is such window, you are guaranteed that there will always be only
 * one unique minimum window in S.
 * 
 * 
 */
class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            Integer count = countMap.getOrDefault(c, 0);
            countMap.put(c, count + 1);
        }
        int start = 0;
        int end = 0;
        int leftMin = 0;
        int minLen = Integer.MAX_VALUE;
        int count = 0;
        while (end < s.length()) {
            if (countMap.containsKey(s.charAt(end))) {
                int n = countMap.get(s.charAt(end)) - 1;
                countMap.put(s.charAt(end), n);
                if (n >= 0) {
                    count++;
                }
            }
            while (count == t.length()) {
                if (end - start + 1 < minLen) {
                    minLen = end - start + 1;
                    leftMin = start;
                }
                if (countMap.containsKey(s.charAt(start))) {
                    int n = countMap.get(s.charAt(start)) + 1;
                    countMap.put(s.charAt(start), n);
                    if (n > 0) {
                        count--;
                    }
                }
                start++;
            }
            end++;
        }
        return (leftMin + minLen) > s.length() ? "" : s.substring(leftMin, leftMin + minLen);
    }
}
