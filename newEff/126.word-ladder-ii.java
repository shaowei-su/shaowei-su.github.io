/*
 * [126] Word Ladder II
 *
 * https://leetcode.com/problems/word-ladder-ii/description/
 *
 * algorithms
 * Hard (15.79%)
 * Total Accepted:    95.8K
 * Total Submissions: 605.8K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * all shortest transformation sequence(s) from beginWord to endWord, such
 * that:
 * 
 * 
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is
 * not a transformed word.
 * 
 * 
 * Note:
 * 
 * 
 * Return an empty list if there is no such transformation sequence.
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
 * Output:
 * [
 * ⁠ ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
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
 * Output: []
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
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (wordList == null || wordList.size() == 0) {
            return res;
        }
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return res;
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        List<String> cur = new ArrayList<>();
        cur.add(beginWord);
        Map<String, Set<String>> neighbors = new HashMap<>();
        ladderLength(beginWord, endWord, wordList, neighbors);
        if (!isConnected) return res;
        dfs(res, cur, visited, wordSet, endWord, shortest, 1, beginWord, neighbors);
        return res;
    }

    public void dfs(List<List<String>> res, List<String> cur, Set<String> visited, Set<String> wordSet, String endWord, int targetLevel, int curLevel, String curWord, Map<String, Set<String>> neighbors) {
        if (curLevel > targetLevel) return;
        if (curLevel == targetLevel && curWord.equals(endWord)) {
            res.add(new ArrayList<String>(cur));
            return;
        }
        if (curLevel == targetLevel) {
            return;
        }
        if (!neighbors.containsKey(curWord)) return;
        for (String nei : neighbors.get(curWord)) {
            if (!visited.contains(nei)) {
                cur.add(nei);
                visited.add(nei);
                dfs(res, cur, visited, wordSet, endWord, targetLevel, curLevel + 1, nei, neighbors);
                cur.remove(cur.size() -1);
                visited.remove(nei);
            }
        }

    }

    boolean isConnected = false;
    int shortest = Integer.MAX_VALUE;

    public void ladderLength(String beginWord, String endWord, List<String> wordList, Map<String, Set<String>> neighbors) {
        if (wordList == null || wordList.size() == 0) {
            return ;
        }
        int level = 0;
        Deque<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return ;
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        while (queue.size() > 0) {
            level++;
            int size = queue.size();
            while (size > 0) {
                String cur = queue.poll();
                Set<String> neis = neighbors.computeIfAbsent(cur, m -> new HashSet<String>());
                char[] curChar = cur.toCharArray();
                for (int i = 0; i < cur.length(); i++) {
                    char temp = curChar[i];
                    for (int j = 0; j < 26; j++) {
                        if (temp - 'a' != j) {
                            curChar[i] = (char) ('a' + j);
                            String attemp = new String(curChar);
                            if (endWord.equals(attemp)) {
                                isConnected = true;
                                shortest = Math.min(shortest, level + 1);
                            }
                            if (!visited.contains(attemp) && wordSet.contains(attemp)) {
                                neis.add(attemp);
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
    }
}
