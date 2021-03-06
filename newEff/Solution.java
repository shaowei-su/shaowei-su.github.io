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
        List<Character> cands = new ArrayList<>();
        Set<Character> added = new HashSet<>();
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }
            for (int i = 0; i < word.length() - 1; i++) {
                Set<Character> tmp = graph.computeIfAbsent(word.charAt(i), s -> new HashSet<>());
                for (int j = i + 1; j < word.length(); j++) {
                    if (word.charAt(i) == word.charAt(j)) {
                        continue;
                    }
                    tmp.add(word.charAt(j));
                }
            }
            if (!added.contains(word.charAt(0))) { 
                cands.add(word.charAt(0));
                added.add(word.charAt(0));
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            Set<Character> tmp = graph.computeIfAbsent(words[i].charAt(0), s -> new HashSet<>());
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].charAt(0) == words[j].charAt(0)) {
                    continue;
                }
                tmp.add(words[j].charAt(0));
            }
        }
        System.out.println("graph = " + graph);
        for (Character k : graph.keySet()) {
            for (Character v : graph.get(k)) {
                indegree.put(v, indegree.getOrDefault(v, 0) + 1);
            }
        }
        System.out.println("indegree = "  +indegree);
        for (Character k : indegree.keySet()) {
            cands.remove(k);
        }
        Deque<Character> queue = new LinkedList<>(cands);
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

        return sb.toString();
    }

    public static void main(String[] args) {
        String[] w = {"wrt", "wrf", "er", "ett", "rftt"};
        Solution sol = new Solution();
        sol.alienOrder(w);
    }
}
