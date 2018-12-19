/*
 * [567] Permutation in String
 *
 * https://leetcode.com/problems/permutation-in-string/description/
 *
 * algorithms
 * Medium (36.90%)
 * Total Accepted:    34.7K
 * Total Submissions: 94.1K
 * Testcase Example:  '"ab"\n"eidbaooo"'
 *
 * Given two strings s1 and s2, write a function to return true if s2 contains
 * the permutation of s1. In other words, one of the first string's
 * permutations is the substring of the second string.
 * 
 * Example 1:
 * 
 * Input:s1 = "ab" s2 = "eidbaooo"
 * Output:True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 * 
 * 
 * 
 * Note:
 * 
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 * 
 * 
 */
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1 == null || s2 == null) {
            return false;
        }
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s1.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 > len2) {
            return false;
        }
        int left = 0, right = 0;
        while (right < len2) {
            if (charCount.containsKey(s2.charAt(right))) {
                if (charCount.get(s2.charAt(right)) == 1) {
                    charCount.remove(s2.charAt(right));
                } else {
                    charCount.put(s2.charAt(right), charCount.get(s2.charAt(right)) - 1);
                }
                if (charCount.size() == 0) {
                    return true;
                }
            } else {
                char miss = s2.charAt(right);
                while (left < right && s2.charAt(left) != miss) {
                    charCount.put(s2.charAt(left), charCount.getOrDefault(s2.charAt(left), 0) + 1);
                    left++;
                }
                left++;
            }
            right++;
        }

        return false;
    }
}
