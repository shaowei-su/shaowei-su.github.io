/*
 * @lc app=leetcode id=49 lang=java
 *
 * [49] Group Anagrams
 *
 * https://leetcode.com/problems/group-anagrams/description/
 *
 * algorithms
 * Medium (43.67%)
 * Total Accepted:    300.3K
 * Total Submissions: 668.3K
 * Testcase Example:  '["eat","tea","tan","ate","nat","bat"]'
 *
 * Given an array of strings, group anagrams together.
 * 
 * Example:
 * 
 * 
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 * ⁠ ["ate","eat","tea"],
 * ⁠ ["nat","tan"],
 * ⁠ ["bat"]
 * ]
 * 
 * Note:
 * 
 * 
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 * 
 * 
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return res;
        }
        Map<String, Integer> lookup = new HashMap<>();
        for (String str : strs) {
            String sortstr = sort(str);
            if (lookup.containsKey(sortstr)) {
                res.get(lookup.get(sortstr)).add(str);
            } else {
                List<String> tmp = new ArrayList<>();
                tmp.add(str);
                lookup.put(sortstr, res.size());
                res.add(tmp);
            }
        }
        return res;
    }
    public String sort(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
