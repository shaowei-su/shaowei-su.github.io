/*
 * [524] Longest Word in Dictionary through Deleting
 *
 * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/description/
 *
 * algorithms
 * Medium (44.12%)
 * Total Accepted:    33K
 * Total Submissions: 74.7K
 * Testcase Example:  '"abpcplea"\n["ale","apple","monkey","plea"]'
 *
 * 
 * Given a string and a string dictionary, find the longest string in the
 * dictionary that can be formed by deleting some characters of the given
 * string. If there are more than one possible results, return the longest word
 * with the smallest lexicographical order. If there is no possible result,
 * return the empty string.
 * 
 * Example 1:
 * 
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * 
 * Output: 
 * "apple"
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * s = "abpcplea", d = ["a","b","c"]
 * 
 * Output: 
 * "a"
 * 
 * 
 * 
 * Note:
 * 
 * All the strings in the input will only contain lower-case letters.
 * The size of the dictionary won't exceed 1,000.
 * The length of all the strings in the input won't exceed 1,000.
 * 
 * 
 */
class Solution {
    public String findLongestWord(String s, List<String> d) {
        if (s == null || s.length() == 0) { 
            return "";
        }
        int max = 0;
        String res = "";
        for (int i = 0; i < d.size(); i++) {
            if (match(s, d.get(i))) {
                if (d.get(i).length() == res.length()) {
                    res = res.compareTo(d.get(i)) < 0 ? res : d.get(i);
                } else if (d.get(i).length() > res.length()) {
                    res = d.get(i);
                }
            }
        }
        return res;
    }
    public boolean match(String prev, String target) {
        int left1 = 0, left2 = 0, right1 = prev.length() -1, right2 = target.length() - 1;
        while (left1 <= right1 && left2 <= right2) {
            while(left1 <= right1 && prev.charAt(left1) != target.charAt(left2)) {
                left1++;
            }
            if (left1 > right1) {
                return false;
            }
            left1++;
            left2++;
        }
        return left2 == right2 + 1;
    }
}
