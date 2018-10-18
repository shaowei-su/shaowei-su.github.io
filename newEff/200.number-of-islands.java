/*
 * [200] Number of Islands
 *
 * https://leetcode.com/problems/number-of-islands/description/
 *
 * algorithms
 * Medium (38.07%)
 * Total Accepted:    225.8K
 * Total Submissions: 592.9K
 * Testcase Example:  '[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]'
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
 * islands. An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically. You may assume all four edges of
 * the grid are all surrounded by water.
 * 
 * Example 1:
 * 
 * 
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 * 
 * Output: 1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 * 
 * Output: 3
 * 
 * 
 */
class Solution {
    class UF {
        int count = 0;
        int[] p;
        public UF(char[][] grid) {
            int t = grid.length * grid[0].length;
            p = new int[t];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') count++;
                    p[i * grid[0].length + j] = i * grid[0].length + j;
                }
            }
        }
        public int find(int t) {
            while (t != p[t]) {
                p[t] = p[p[t]];
                t = p[t];
            }
            return t;
        }
        public void union(int a, int b) {
            int ap = find(a);
            int bp = find(b);
            if (ap != bp) {
                p[ap] = bp;
                count--;
            }
        }
    }

    public int numIslands(char[][] grid) {
         if (grid == null || grid.length == 0 || grid[0].length == 0) {
             return 0;
         }
         int r = grid.length;
         int c = grid[0].length;
         UF uf = new UF(grid);
         for (int i = 0; i < grid.length; i++) {
             for (int j = 0; j < grid[0].length; j++) {
                 if (grid[i][j] == '0') continue;
                 if (i > 1 && grid[i - 1][j] == '1') uf.union(i * grid[0].length + j, (i - 1) * grid[0].length + j);
                 if (i < (r - 1) && grid[i + 1][j] == '1') uf.union(i * grid[0].length + j, (i + 1) * grid[0].length + j);
                 if (j > 1 && grid[i][j - 1] == '1') uf.union(i * grid[0].length + j, i * grid[0].length + j - 1);
                 if (j < (c - 1) && grid[i][j + 1] == '1') uf.union(i * grid[0].length + j, i * grid[0].length + j + 1);
             }
         }
         return uf.count;
    }

                    
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int r = grid.length;
        int c = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == '0' || visited[i][j]) continue;
                dfs(grid, visited, i, j);
                count++;
            }
        }
        return count;
    }
    public void dfs(char[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }
        if (grid[i][j] == '0' || visited[i][j]) return;
        visited[i][j] = true;
        dfs(grid, visited, i + 1, j);
        dfs(grid, visited, i - 1, j);
        dfs(grid, visited, i, j + 1);
        dfs(grid, visited, i, j - 1);
    }
}
