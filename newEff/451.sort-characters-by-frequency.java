/*
 * @lc app=leetcode id=451 lang=java
 *
 * [451] Sort Characters By Frequency
 *
 * https://leetcode.com/problems/sort-characters-by-frequency/description/
 *
 * algorithms
 * Medium (55.35%)
 * Total Accepted:    87K
 * Total Submissions: 157.1K
 * Testcase Example:  '"tree"'
 *
 * Given a string, sort it in decreasing order based on the frequency of
 * characters.
 * 
 * Example 1:
 * 
 * Input:
 * "tree"
 * 
 * Output:
 * "eert"
 * 
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid
 * answer.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * "cccaaa"
 * 
 * Output:
 * "cccaaa"
 * 
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * 
 * 
 * 
 * Example 3:
 * 
 * Input:
 * "Aabb"
 * 
 * Output:
 * "bbAa"
 * 
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 * 
 * 
 */
class Solution {
    public String frequencySort(String s) {
        char[] chars = s.toCharArray();
        int len = s.length();
        List<List<Character>> buckets = new ArrayList<>();
        for (int i = 0; i <= len; i++) {
            buckets.add(new ArrayList<>());
        }
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : chars) {
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            buckets.get(entry.getValue()).add(entry.getKey());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = len; i > 0; i--) {
            List<Character> l = buckets.get(i);
            for (Character c : l) {
                for (int j = 0; j < i; j++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();


    }
    public String frequencySort2(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : chars) {
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            pq.offer(entry);
        }
        StringBuilder sb = new StringBuilder();
        while (pq.size() > 0) {
            Map.Entry<Character, Integer> entry = pq.poll();
            int count = entry.getValue();
            while (count > 0) {
                sb.append(entry.getKey());
                count--;
            }
        }
        return sb.toString();
    }
}
