/*
 * [245] Shortest Word Distance III
 *
 * https://leetcode.com/problems/shortest-word-distance-iii/description/
 *
 * algorithms
 * Medium (51.86%)
 * Total Accepted:    31.4K
 * Total Submissions: 60.6K
 * Testcase Example:  '["practice", "makes", "perfect", "coding", "makes"]\n"makes"\n"coding"'
 *
 * Given a list of words and two words word1 and word2, return the shortest
 * distance between these two words in the list.
 * 
 * word1 and word2 may be the same and they represent two individual words in
 * the list.
 * 
 * Example:
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * 
 * 
 * Input: word1 = “makes”, word2 = “coding”
 * Output: 1
 * 
 * 
 * 
 * Input: word1 = "makes", word2 = "makes"
 * Output: 3
 * 
 * 
 * Note:
 * You may assume word1 and word2 are both in the list.
 * 
 */
class Solution {
     public int shortestWordDistance(String[] words, String word1, String word2) {
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
                 if (i - j != 0) {
                 distance = Math.min(distance, Math.abs(i - j));
                 }
             }
         }
         return distance;
     }
}
