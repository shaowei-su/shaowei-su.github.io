/*
 * [438] Find All Anagrams in a String
 *
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
 *
 * algorithms
 * Easy (35.34%)
 * Total Accepted:    94.8K
 * Total Submissions: 268.2K
 * Testcase Example:  '"cbaebabacd"\n"abc"'
 *
 * Given a string s and a non-empty string p, find all the start indices of p's
 * anagrams in s.
 * 
 * Strings consists of lowercase English letters only and the length of both
 * strings s and p will not be larger than 20,100.
 * 
 * The order of output does not matter.
 * 
 * Example 1:
 * 
 * Input:
 * s: "cbaebabacd" p: "abc"
 * 
 * Output:
 * [0, 6]
 * 
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of
 * "abc".
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * s: "abab" p: "ab"
 * 
 * Output:
 * [0, 1, 2]
 * 
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 * 
 * 
 */
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return res;
        }
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int right = 0; 
        while (right < s.length()) {
            char cur = s.charAt(right);
            if (countMap.containsKey(cur)) {
                countMap.put(cur, countMap.get(cur) - 1);
                if (countMap.get(cur) == 0) {
                    countMap.remove(cur);
                }
                if (countMap.size() == 0) {
                    res.add(left);
                    countMap.put(s.charAt(left), 1);
                    left++;
                }
                right++;
            } else {
                while (left < right && !countMap.containsKey(s.charAt(right))) {
                    countMap.put(s.charAt(left), countMap.getOrDefault(s.charAt(left), 0) + 1);
                    left++;
                }
                if (!countMap.containsKey(s.charAt(right))) {
                    right++;
                    left = right;
                }
            }
        }
        return res;
    }
}
