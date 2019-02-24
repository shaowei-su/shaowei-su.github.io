/*
 * @lc app=leetcode id=211 lang=java
 *
 * [211] Add and Search Word - Data structure design
 *
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/description/
 *
 * algorithms
 * Medium (28.50%)
 * Total Accepted:    103.5K
 * Total Submissions: 356.8K
 * Testcase Example:  '["WordDictionary","addWord","addWord","addWord","search","search","search","search"]\n[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]'
 *
 * Design a data structure that supports the following two operations:
 * 
 * 
 * void addWord(word)
 * bool search(word)
 * 
 * 
 * search(word) can search a literal word or a regular expression string
 * containing only letters a-z or .. A . means it can represent any one
 * letter.
 * 
 * Example:
 * 
 * 
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * 
 * 
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 * 
 */
class WordDictionary {
    
    class Trie {
        Map<Character, Trie> children;
        boolean isWord;
        public Trie() {
            children = new HashMap<>();
            isWord = false;
        }
    }

    Trie root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Trie(); 
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
       Trie node = root;
       int pos = 0;
       while (pos < word.length()) {
           node = node.children.computeIfAbsent(word.charAt(pos), t -> new Trie());
           pos++;
       }
       node.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(root, word);
    }

    public boolean search(Trie node, String word) {
        if (word == null) {
            return true;
        }
        int pos = 0;
        while (pos < word.length()) {
            if (word.charAt(pos) == '.') {
                for (Trie child : node.children.values()) {
                    if (search(child, word.substring(pos + 1))) {
                        return true;
                    }
                }
                return false;
            } else {
                if (!node.children.containsKey(word.charAt(pos))) {
                    return false;
                }
                node = node.children.get(word.charAt(pos));
            }
            pos++;
        }
        return node.isWord;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
