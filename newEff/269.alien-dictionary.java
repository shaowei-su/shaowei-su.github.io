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
        if (words == null || words.length == 0) {
            return null;
        }
        Map<Character, List<Character>> splits = new HashMap<>();
        ranker(splits, Arrays.asList(words));
        List<Character> res = toposort(splits);
        StringBuilder sb = new StringBuilder();
        for (Character c : res) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void ranker(Map<Character, List<Character>> res, List<String> words) {
        if (words == null || words.size() <= 1) {
            return;
        }
        Map<Character, List<String>> splits = new HashMap<>();
        char prev = '0';
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).length() == 0) {
                continue;
            }
            char cur = words.get(i).charAt(0);
            List<String> temp = splits.computeIfAbsent(cur, l -> new ArrayList<String>());
            res.computeIfAbsent(cur, l -> new ArrayList<Character>());
            temp.add(words.get(i).substring(1));
            if (prev == '0') {
                prev = cur;
            } else if (prev == cur) {
                continue;
            } else {
                List<Character> lower = res.computeIfAbsent(prev, l -> new ArrayList<Character>());
                lower.add(cur);
                prev = cur;
            }
        }
        for (Character c : splits.keySet()) {
           ranker(res, splits.get(c));
        }
    }

    public static List<Character> toposort(Map<Character, List<Character>> splits) {
        List<Character> res = new ArrayList<>();
        if (splits == null || splits.size() == 0) {
            return res;
        }
        Map<Character, Integer> indegree = new HashMap<>();
        for (Character c : splits.keySet()) {
            for (Character sub : splits.get(c)) {
                indegree.put(sub, indegree.getOrDefault(sub, 0) + 1);
            }
        }
        Deque<Character> queue = new LinkedList<>();
        for (Character c : splits.keySet()) {
            if (indegree.containsKey(c)) {
                continue;
            }
            if (queue.size() > 0) {
                return res;
            }
            queue.offer(c);
            res.add(c);
        }
        while (queue.size() > 0) {
            Character cur = queue.poll();
            if (!splits.containsKey(cur)) {
                continue;
            }
            for (Character c : splits.get(cur)) {
                if (indegree.get(c) == 1) {
                    indegree.remove(c);
                    res.add(c);
                    queue.offer(c);
                } else {
                    indegree.put(c, indegree.get(c) - 1);
                }
            }
        }

        return res; 
    }

    public static void main(String[] args) {
        String[] input = new String[] {"z", "z"};
        Map<Character, List<Character>> splits = new HashMap<>();
        ranker(splits, Arrays.asList(input));
        System.out.println(splits.toString());
        System.out.println(toposort(splits));
    }

}



