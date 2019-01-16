/*
 * @lc app=leetcode id=395 lang=java
 *
 * [395] Longest Substring with At Least K Repeating Characters
 *
 * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/description/
 *
 * algorithms
 * Medium (37.39%)
 * Total Accepted:    38.7K
 * Total Submissions: 103.6K
 * Testcase Example:  '"aaabb"\n3'
 *
 * 
 * Find the length of the longest substring T of a given string (consists of
 * lowercase letters only) such that every character in T appears no less than
 * k times.
 * 
 * 
 * Example 1:
 * 
 * Input:
 * s = "aaabb", k = 3
 * 
 * Output:
 * 3
 * 
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * s = "ababbc", k = 2
 * 
 * Output:
 * 5
 * 
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is
 * repeated 3 times.
 * 
 * 
 */
class Solution {
    public int longestSubstring(String s, int k) {
        return helper(s.toCharArray(), 0, s.length() - 1, k);
    }
    public int helper(char[] chars, int start, int end, int k) {
        if (end - start + 1< k) {
            return 0;
        }

        int[] counts = new int[26];
        for (int i = start; i <= end; i++) {
            counts[chars[i] - 'a']++;
        }
        for (int i = start; i <= end; i++) {
            if (counts[chars[i] - 'a'] > 0 && counts[chars[i] - 'a'] < k) {
                int left = helper(chars, start, i - 1, k);
                int right = helper(chars, i + 1, end, k);
                return Math.max(left, right);
            }
        }
        return end - start + 1;
    }   

    public int longestSubstring2(String s, int k) {
        if (s == null || s.length() < k) {
            return 0;
        }
        int[] counts;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            counts = new int[256];
            for (int j = i; j < s.length(); j++) {
                counts[s.charAt(j)]++;
                if (isFit(counts, k)) {
                    max = Math.max(j - i + 1, max);
                }
            }
        }
        return max;
    }

    public boolean isFit(int[] counts, int k) {
        for (int c : counts) {
            if (c > 0 && c < k) {
                return false;
            }
        }
        return true;
    }
}
