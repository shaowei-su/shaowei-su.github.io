/*
 * [746] Prefix and Suffix Search
 *
 * https://leetcode.com/problems/prefix-and-suffix-search/description/
 *
 * algorithms
 * Hard (27.97%)
 * Total Accepted:    9K
 * Total Submissions: 32.1K
 * Testcase Example:  '["WordFilter","f"]\n[[["apple"]], ["a","e"]]'
 *
 * 
 * Given many words, words[i] has weight i.
 * 
 * Design a class WordFilter that supports one function, WordFilter.f(String
 * prefix, String suffix).
 * It will return the word with given prefix and suffix with maximum weight.
 * If no word exists, return -1.
 * 
 * 
 * Examples:
 * 
 * Input:
 * WordFilter(["apple"])
 * WordFilter.f("a", "e") // returns 0
 * WordFilter.f("b", "") // returns -1
 * 
 * 
 * Note:
 * 
 * words has length in range [1, 15000].
 * For each test case, up to words.length queries WordFilter.f may be made.
 * words[i] has length in range [1, 10].
 * prefix, suffix have lengths in range [0, 10].
 * words[i] and prefix, suffix queries consist of lowercase letters only.
 * 
 * 
 */
class WordFilter {
    Map<String, Integer> prefixMap = new HashMap<>();

    public WordFilter(String[] words) {
        for (int w = 0; w < words.length; w++) {
            for (int i = 0; i <= 10 && i <= words[w].length(); i++) {
                for (int j = 0; j <= 10 && j <= words[w].length(); j++) {
                    prefixMap.put(words[w].substring(0, i) + "#" + words[w].substring(words[w].length() - j), w);
                }
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        if (prefixMap.containsKey(prefix + "#" + suffix)) {
            return prefixMap.get(prefix + "#" + suffix);
        } else {
            return -1;
        }
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */
