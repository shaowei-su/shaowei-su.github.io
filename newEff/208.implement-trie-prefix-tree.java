/*
 * [208] Implement Trie (Prefix Tree)
 *
 * https://leetcode.com/problems/implement-trie-prefix-tree/description/
 *
 * algorithms
 * Medium (33.69%)
 * Total Accepted:    135K
 * Total Submissions: 400.7K
 * Testcase Example:  '["Trie","insert","search","search","startsWith","insert","search"]\n[[],["apple"],["apple"],["app"],["app"],["app"],["app"]]'
 *
 * Implement a trie with insert, search, and startsWith methods.
 * 
 * Example:
 * 
 * 
 * Trie trie = new Trie();
 * 
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");   
 * trie.search("app");     // returns true
 * 
 * 
 * Note:
 * 
 * 
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 * 
 * 
 */


class Trie {
    Map<Character, Trie> leaf;
    boolean end;
    /** Initialize your data structure here. */
    public Trie() {
        leaf = new HashMap<>();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word.length() == 0) {
            this.end = true;
            return;
        }
        char c = word.charAt(0);
        Trie t = leaf.computeIfAbsent(c, trie -> new Trie());
        t.insert(word.substring(1));
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word.length() == 0) {
            return this.end;
        }
        char c = word.charAt(0);
        if (!leaf.containsKey(c)) return false;
        return leaf.get(c).search(word.substring(1));
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix.length() == 0) {
            return true;
        }
         char c = prefix.charAt(0);
         if (!leaf.containsKey(c)) return false;
         return leaf.get(c).startsWith(prefix.substring(1));
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
