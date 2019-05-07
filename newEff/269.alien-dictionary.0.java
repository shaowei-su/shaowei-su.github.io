import java.util.*;
/*
 * @lc app=leetcode id=269 lang=java
 *
 * [269] Alien Dictionary
 *
 * https://leetcode.com/problems/alien-dictionary/description/
 *
 * algorithms
 * Hard (29.38%)
 * Total Accepted:    65.8K
 * Total Submissions: 220.7K
 * Testcase Example:  '["wrt","wrf","er","ett","rftt"]'
 *
 * There is a new alien language which uses the latin alphabet. However, the
 * order among letters are unknown to you. You receive a list of non-empty
 * words from the dictionary, where words are sorted lexicographically by the
 * rules of this new language. Derive the order of letters in this language.
 * 
 * Example 1:
 * 
 * 
 * Input:
 * [
 * ⁠ "wrt",
 * ⁠ "wrf",
 * ⁠ "er",
 * ⁠ "ett",
 * ⁠ "rftt"
 * ]
 * 
 * Output: "wertf"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * [
 * ⁠ "z",
 * ⁠ "x"
 * ]
 * 
 * Output: "zx"
 * 
 * 
 * Example 3:
 * 
 * 
 * Input:
 * [
 * ⁠ "z",
 * ⁠ "x",
 * ⁠ "z"
 * ] 
 * 
 * Output: "" 
 * 
 * Explanation: The order is invalid, so return "".
 * 
 * 
 * Note:
 * 
 * 
 * You may assume all letters are in lowercase.
 * You may assume that if a is a prefix of b, then a must appear before b in
 * the given dictionary.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is
 * fine.
 * 
 * 
 */
class Solution {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                indegree.put(c, 0);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int len = Math.min(w1.length(), w2.length());
            int k = 0;
            while (k < len) {
                if (w1.charAt(k) == w2.charAt(k)) {
                    k++;
                    continue;
                }
                Set<Character> tmp = graph.computeIfAbsent(w1.charAt(k), s -> new HashSet<>());
                if (!tmp.contains(w2.charAt(k))) {
                    tmp.add(w2.charAt(k));
                    indegree.put(w2.charAt(k), indegree.get(w2.charAt(k)) + 1);
                }
                break;
            }
        }

        Deque<Character> queue = new LinkedList<>();

        for (Character c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character cur = queue.poll();
            sb.append(cur);
            if (!graph.containsKey(cur)) {
                continue;
            }
            for (Character next : graph.get(cur)) {
                indegree.put(next, indegree.getOrDefault(next, 0) - 1);
                if (indegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }

        if (sb.length() != indegree.size()) {
            return "";
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String[] w = {"wrt", "wrf", "er", "ett", "rftt"};
        Solution sol = new Solution();
        sol.alienOrder(w);
    }
}
