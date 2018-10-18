/*
 * [127] Word Ladder
 *
 * https://leetcode.com/problems/word-ladder/description/
 *
 * algorithms
 * Medium (21.30%)
 * Total Accepted:    195.2K
 * Total Submissions: 916.4K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * the length of shortest transformation sequence from beginWord to endWord,
 * such that:
 * 
 * 
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is
 * not a transformed word.
 * 
 * 
 * Note:
 * 
 * 
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * Output: 5
 * 
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" ->
 * "dog" -> "cog",
 * return its length 5.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * Output: 0
 * 
 * Explanation: The endWord "cog" is not in wordList, therefore no possible
 * transformation.
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) {
            return 0;
        }
        int level = 0;
        Deque<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        while (queue.size() > 0) {
            level++;
            int size = queue.size();
            while (size > 0) {
                String cur = queue.poll();
                char[] curChar = cur.toCharArray();
                for (int i = 0; i < cur.length(); i++) {
                    char temp = curChar[i];
                    for (int j = 0; j < 26; j++) {
                        if (temp - 'a' != j) {
                            curChar[i] = (char) ('a' + j);
                            String attemp = new String(curChar);
                            if (endWord.equals(attemp)) {
                                return level + 1;
                            }
                            if (!visited.contains(attemp) && wordSet.contains(attemp)) {
                                queue.offer(attemp);
                                visited.add(attemp);
                            }
                            curChar[i] = temp;
                        }
                    }
                }
                size--;
            }
        }
        return 0;
    }
}
