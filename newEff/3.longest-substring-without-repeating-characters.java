/*
 * [3] Longest Substring Without Repeating Characters
 *
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *
 * algorithms
 * Medium (25.15%)
 * Total Accepted:    622.8K
 * Total Submissions: 2.5M
 * Testcase Example:  '"abcabcbb"'
 *
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: "abcabcbb"
 * Output: 3 
 * Explanation: The answer is "abc", with the length of 3. 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3. 
 * ⁠            Note that the answer must be a substring, "pwke" is a
 * subsequence and not a substring.
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charCount = new HashMap<>();
        int left = 0, right = 0, maxLen = 0;
    
        while (right < s.length()) {
            char c = s.charAt(right);
            if (!charCount.containsKey(c)) {
                charCount.put(c, 1);
                maxLen = Math.max(maxLen, right - left + 1);
                right++;
            } else {
                while (left < right) {
                    if (s.charAt(left) != c) {
                        charCount.remove(s.charAt(left++));
                    } else {
                        break;
                    }
                }
                left++;
                right++;
            }
        }
        return maxLen;
    }
}
