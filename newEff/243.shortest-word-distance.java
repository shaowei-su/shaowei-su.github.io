/*
 * [243] Shortest Word Distance
 *
 * https://leetcode.com/problems/shortest-word-distance/description/
 *
 * algorithms
 * Easy (54.67%)
 * Total Accepted:    50.5K
 * Total Submissions: 92.4K
 * Testcase Example:  '["practice", "makes", "perfect", "coding", "makes"]\n"coding"\n"practice"'
 *
 * Given a list of words and two words word1 and word2, return the shortest
 * distance between these two words in the list.
 * 
 * Example:
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * 
 * 
 * Input: word1 = “coding”, word2 = “practice”
 * Output: 3
 * 
 * 
 * 
 * Input: word1 = "makes", word2 = "coding"
 * Output: 1
 * 
 * 
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are
 * both in the list.
 * 
 */
class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        Map<String, Integer> lastMap = new HashMap<>();
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                if (lastMap.containsKey(word2)) {
                    res = Math.min(res, i - lastMap.get(word2));
                }
                lastMap.put(word1, i);
            } else if (words[i].equals(word2)) {
                if (lastMap.containsKey(word1)) {
                    res = Math.min(res, i - lastMap.get(word1));
                }
                lastMap.put(word2, i);
            }
        }
        return res;

    }

    public int shortestDistance2(String[] words, String word1, String word2) {
        if (words == null || words.length == 0) {
            return 0;
        }
        Map<String, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            List<Integer> indexes = indexMap.computeIfAbsent(words[i], m -> new ArrayList<Integer>());
            indexes.add(i);
        }
        int distance = Integer.MAX_VALUE;
        for (int i : indexMap.get(word1)) {
            for (int j : indexMap.get(word2)) {
                distance = Math.min(distance, Math.abs(i - j));
            }
        }
        return distance;
    }
}
