/*
 * @lc app=leetcode id=340 lang=java
 *
 * [340] Longest Substring with At Most K Distinct Characters
 *
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/
 *
 * algorithms
 * Hard (38.78%)
 * Total Accepted:    60.9K
 * Total Submissions: 156.8K
 * Testcase Example:  '"eceba"\n2'
 *
 * Given a string, find the length of the longest substring T that contains at
 * most k distinct characters.
 * 
 * Example 1:
 * 
 * 
 * 
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: T is "aa" which its length is 2.
 * 
 * 
 * 
 * 
 */
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int maxLen = Integer.MIN_VALUE;
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }
        Map<Character, Integer> charMap = new HashMap<>();
        int left = 0, right = 0;
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (charMap.containsKey(s.charAt(right))) {
                int curLen = right - left + 1;
                maxLen = Math.max(curLen, maxLen);
                charMap.put(rightChar, charMap.get(rightChar) + 1);
                right++;
            } else {
                if (charMap.size() >= k) {
                    while (left < right && charMap.size() >= k) {
                        if (charMap.get(s.charAt(left)) > 1) {
                            charMap.put(s.charAt(left), charMap.get(s.charAt(left)) - 1);
                        } else {
                            charMap.remove(s.charAt(left));
                        }
                        left++;
                    }
                }
                charMap.put(rightChar, 1);
                int curLen = right - left + 1;
                maxLen = Math.max(curLen, maxLen);
                right++;
            }
        }
        return maxLen;
    }
}
