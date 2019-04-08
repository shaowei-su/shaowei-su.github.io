/*
 * @lc app=leetcode id=267 lang=java
 *
 * [267] Palindrome Permutation II
 *
 * https://leetcode.com/problems/palindrome-permutation-ii/description/
 *
 * algorithms
 * Medium (33.32%)
 * Total Accepted:    25K
 * Total Submissions: 75.2K
 * Testcase Example:  '"aabb"'
 *
 * Given a string s, return all the palindromic permutations (without
 * duplicates) of it. Return an empty list if no palindromic permutation could
 * be form.
 * 
 * Example 1:
 * 
 * 
 * Input: "aabb"
 * Output: ["abba", "baab"]
 * 
 * Example 2:
 * 
 * 
 * Input: "abc"
 * Output: []
 * 
 */
class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        char[] chars = s.toCharArray();
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : chars) {
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }
        if (charMap.size() == 1) {
            res.add(s);
            return res;
        }
        int oddCount = 0;
        Character oddChar = null;
        Integer oddCharCount = 0;
        List<Character> candidates = new ArrayList<>(); 
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                oddCount++;
                oddChar = entry.getKey();
                oddCharCount = entry.getValue();
            }
            for (int i = 0; i < entry.getValue() / 2; i++) {
                candidates.add(entry.getKey());
            }
        }
        if (oddCount > 1) {
            return  res;
        }
        dfs(res, candidates, oddChar, oddCharCount, new StringBuilder(), 0, new HashSet<Integer>());
        return res;
    }

    public void dfs(List<String> res, List<Character> candidates, Character oddChar, Integer oddCharCount, StringBuilder sb, int index, Set<Integer> visited) {
        if (sb.length() == candidates.size()) {
            StringBuilder ret = new StringBuilder(sb);
            String reverse = new StringBuilder(sb).reverse().toString();
            if (oddChar != null) {
                ret.append(oddChar);
            }
            ret.append(reverse);
            res.add(ret.toString());
            return;
        }
        for (int i = 0; i < candidates.size(); i++) {
            if (i > 0 && candidates.get(i) == candidates.get(i - 1) && !visited.contains(i - 1)) {
                continue;
            }
            if (visited.contains(i)) {
                continue;
            }
            visited.add(i);
            sb.append(candidates.get(i));
            dfs(res, candidates, oddChar, oddCharCount, sb, i + 1, visited);
            sb.setLength(sb.length() - 1);
            visited.remove(i);
        }


    }

}
