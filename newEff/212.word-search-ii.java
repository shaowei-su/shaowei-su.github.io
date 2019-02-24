/*
 * @lc app=leetcode id=212 lang=java
 *
 * [212] Word Search II
 *
 * https://leetcode.com/problems/word-search-ii/description/
 *
 * algorithms
 * Hard (27.01%)
 * Total Accepted:    97.5K
 * Total Submissions: 355.4K
 * Testcase Example:  '[["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]\n["oath","pea","eat","rain"]'
 *
 * Given a 2D board and a list of words from the dictionary, find all words in
 * the board.
 * 
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once in a word.
 * 
 * Example:
 * 
 * 
 * Input: 
 * words = ["oath","pea","eat","rain"] and board =
 * [
 * ⁠ ['o','a','a','n'],
 * ⁠ ['e','t','a','e'],
 * ⁠ ['i','h','k','r'],
 * ⁠ ['i','f','l','v']
 * ]
 * 
 * Output: ["eat","oath"]
 * 
 * 
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 */
class Solution {
    class Trie {
        boolean isWord;
        Map<Character, Trie> children;
        public Trie() {
            isWord = false;
            children = new HashMap<>();
        }
    }

    public Trie buildTrie(String[] words) {
        Trie root = new Trie();
        for (String s : words) {
            Trie cur = root;
            int pos = 0;
            while (pos < s.length()) {
                cur = cur.children.computeIfAbsent(s.charAt(pos), n -> new Trie());
                pos++;
            }
            cur.isWord = true;
        }
        return root;
    }

    public List<String> findWords(char[][] board, String[] words) {
        Trie root = buildTrie(words);           
        List<String> res = new ArrayList<>();
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boolean[][] visited = new boolean[n][m];
                dfs(board, root, res, i, j, m, n, visited, "");
            }
        }
        return res;
    }

    public boolean isWord(Trie root, String cur) {
        Trie node = root;
        int pos = 0;
        while (pos < cur.length()) {
            if (!node.children.containsKey(cur.charAt(pos))) {
                return false;
            }
            node = node.children.get(cur.charAt(pos));
            pos++;
        }
        return node.isWord;
    }

     public boolean withPath(Trie root, String cur) {
         Trie node = root;
         int pos = 0;
         while (pos < cur.length()) {
             if (!node.children.containsKey(cur.charAt(pos))) {
                 return false;
             }   
             node = node.children.get(cur.charAt(pos));
             pos++;
         }   
         return true;
     }   

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void dfs(char[][] board, Trie root, List<String> res, int i, int j, int m, int n, boolean[][] visited, String cur) {
        if (!root.children.containsKey(board[i][j])) {
            return;
        }
        root = root.children.get(board[i][j]);
        cur = cur + board[i][j];
        if (root.isWord) {
            root.isWord = false;
            res.add(cur);
        }
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= n || y < 0 || y >= m) {
                continue;
            }
            if (visited[x][y]) {
                continue;
            }
            dfs(board, root, res, x, y, m, n, visited, cur);
        }
        visited[i][j] = false;
    }
}
