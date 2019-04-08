/*
 * [79] Word Search
 *
 * https://leetcode.com/problems/word-search/description/
 *
 * algorithms
 * Medium (29.06%)
 * Total Accepted:    213.9K
 * Total Submissions: 735.1K
 * Testcase Example:  '[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]\n"ABCCED"'
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once.
 * 
 * Example:
 * 
 * 
 * board =
 * [
 * ⁠ ['A','B','C','E'],
 * ⁠ ['S','F','C','S'],
 * ⁠ ['A','D','E','E']
 * ]
 * 
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 * 
 * 
 */
class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        //sol.exist(new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCCED");
        sol.exist(new char[][] {{'a', 'a'}}, "aaa");
    }

    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boolean[][] visited = new boolean[n][m];
                visited[i][j] = true;
                if (board[i][j] == word.charAt(0) && dfs2(board, i, j, n, m, word, new StringBuilder().append(board[i][j]), visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public boolean dfs2(char[][] board, int i, int j, int n, int m, String word, StringBuilder sb, boolean[][] visited) {
        System.out.println(" i = " + i + " j = " + j + " sb = " + sb.toString());
        if (sb.toString().equals(word)) {
            return true;
        }
        if (sb.length() >= word.length()) {
            return false;
        }
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= n || y < 0 || y >= m) {
                continue;
            }
            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;
            sb.append(board[x][y]);
            if (dfs2(board, x, y, n, m, word, sb, visited)) {
                return true;
            }
            sb.setLength(sb.length() - 1);
            visited[x][y] = false;
        }

        return false;
    }
 
    public boolean exist2(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        char[] targets = word.toCharArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boolean[][] visited = new boolean[n][m];
                if (board[i][j] == targets[0] && dfs(visited, board, targets, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean dfs(boolean[][] visited, char[][] board, char[] targets, int i, int j, int cur) {
        int n = board.length;
        int m = board[0].length;
        if (i < 0 || i >= n) return false;
        if (j < 0 || j >= m) return false;
        if (visited[i][j]) return false;
        if (board[i][j] != targets[cur]) return false;
        if (cur == (targets.length - 1) && targets[cur] == board[i][j]) {
            return true;
        }
        visited[i][j] = true;
        if (dfs(visited, board, targets, i + 1, j, cur + 1) || dfs(visited, board, targets, i - 1, j, cur + 1) || dfs(visited, board, targets, i, j + 1, cur + 1) || dfs(visited, board, targets, i, j - 1, cur + 1)) {
            return true;
        }
        visited[i][j] = false;
        return false;
    }

}
