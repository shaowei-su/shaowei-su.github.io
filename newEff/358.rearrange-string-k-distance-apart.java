/*
 * @lc app=leetcode id=358 lang=java
 *
 * [358] Rearrange String k Distance Apart
 *
 * https://leetcode.com/problems/rearrange-string-k-distance-apart/description/
 *
 * algorithms
 * Hard (32.32%)
 * Total Accepted:    19.8K
 * Total Submissions: 61.3K
 * Testcase Example:  '"aabbcc"\n3'
 *
 * Given a non-empty string s and an integer k, rearrange the string such that
 * the same characters are at least distance k from each other.
 * 
 * All input strings are given in lowercase letters. If it is not possible to
 * rearrange the string, return an empty string "".
 * 
 * Example 1:
 * 
 * 
 * 
 * Input: s = "aabbcc", k = 3
 * Output: "abcabc" 
 * Explanation: The same letters are at least distance 3 from each other.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aaabc", k = 3
 * Output: "" 
 * Explanation: It is not possible to rearrange the string.
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "aaadbbcc", k = 2
 * Output: "abacabcd"
 * Explanation: The same letters are at least distance 2 from each other.
 * 
 * 
 * 
 * 
 */
class Solution {
    public String rearrangeString(String s, int k) {
        if (k <= 1) {
            return s;
        }
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue() ? a.getKey() - b.getKey() : b.getValue() - a.getValue());
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            pq.offer(entry);
        }
        StringBuilder sb = new StringBuilder();
        while (pq.size() > 0) {
            PriorityQueue<Map.Entry<Character, Integer>> temp = new PriorityQueue<>(
                    (a, b) -> a.getValue() == b.getValue() ? a.getKey() - b.getKey() : b.getValue() - a.getValue());
            int count = 0;
            while (count < k && pq.size() > 0) {
                Map.Entry<Character, Integer> cur = pq.poll();
                sb.append(cur.getKey());
                if (cur.getValue() > 1) {
                    cur.setValue(cur.getValue() - 1);
                    temp.offer(cur);
                }
                count++;
            }
            if (temp.size() > 0 && count != k) {
                return "";
            }
            while (temp.size() > 0) {
                pq.offer(temp.poll());
            }
        }
        return sb.toString();
    }
}
