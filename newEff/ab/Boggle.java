import java.util.*;
public class Boggle {
    
    int maxSize = 0;
    List<String> maxList = null;
    Trie root;
    public void boggle(char[][] board, String[] words) {
        int row = board.length;
        int col = board[0].length;
        Trie root = buildTrie(words);
        this.root = root;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boolean[][] visited = new boolean[row][col];
                dfs(board, root, i, j, row, col, visited, new ArrayList<String>());
            }
        }
    }

    public Trie buildTrie(String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            Trie cur = root;
            for (int i = 0; i < word.length(); i++) {
                int ind = word.charAt(i) - 'a';
                if (cur.children[ind] == null) {
                    cur.children[ind] = new Trie();
                }
                cur = cur.children[ind];
            }
            cur.word = word;
        }
        return root;
    }


    public void dfs(char[][] board, Trie node, int i, int j, int row, int col, boolean[][] visited, List<String> cur) {
        if (node.children[board[i][j] - 'a'] == null || visited[i][j]) {
            return;
        }
        Trie next = node.children[board[i][j] - 'a'];
        visited[i][j] = true;
        if (next.word != null) {
            cur.add(next.word);
            if (cur.size() > maxSize) {
                maxSize = cur.size();
                maxList = new ArrayList<>(cur);
            }
            for (int x = 0; x < row; x++) {
                for (int y = 0; y < col; y++) {
                    dfs(board, root, x, y, row, col, visited, cur);
                }
            }
            cur.remove(cur.size() - 1);
        } else {
            int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] dir : dirs) {
                int x = dir[0] + i;
                int y = dir[1] + j;
                if (x < 0 || x >= row || y < 0 || y >= col) {
                    continue;
                }
                if (visited[x][y]) {
                    continue;
                }
                dfs(board, next, x, y, row, col, visited, cur);
            }
        }
        visited[i][j] = false;
    }

    public static void main(String[] args) {
        Boggle b = new Boggle();
        char[][] board = {{'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}};
        String[] words = {"oath","ak","klf", "ii", "rena"};
        b.boggle(board, words);
        System.out.println(b.maxList);

    }

}

class Trie {
    Trie[] children;
    String word;
    public Trie() {
        children = new Trie[26];
        word = null;
    }
}
