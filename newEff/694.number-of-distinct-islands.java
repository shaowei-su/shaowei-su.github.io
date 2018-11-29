/*
 * [694] Number of Distinct Islands
 *
 * https://leetcode.com/problems/number-of-distinct-islands/description/
 *
 * algorithms
 * Medium (47.58%)
 * Total Accepted:    15.4K
 * Total Submissions: 32.5K
 * Testcase Example:  '[[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]'
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's
 * (representing land) connected 4-directionally (horizontal or vertical.)  You
 * may assume all four edges of the grid are surrounded by water.
 * 
 * Count the number of distinct islands.  An island is considered to be the
 * same as another if and only if one island can be translated (and not rotated
 * or reflected) to equal the other.
 * 
 * Example 1:
 * 
 * 11000
 * 11000
 * 00011
 * 00011
 * 
 * Given the above grid map, return 1.
 * 
 * 
 * Example 2:
 * 11011
 * 10000
 * 00001
 * 11011
 * Given the above grid map, return 3.
 * Notice that:
 * 
 * 11
 * 1
 * 
 * and
 * 
 * ⁠1
 * 11
 * 
 * are considered different island shapes, because we do not consider
 * reflection / rotation.
 * 
 * 
 * Note:
 * The length of each dimension in the given grid does not exceed 50.
 * 
 */
class Solution {
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        Set<String> visited = new HashSet<>();
        Set<List<Integer>> paths = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 || visited.contains(i + "#" + j)) {
                    continue;
                }
                List<Integer> path = new ArrayList<Integer>();                
                dfs(grid, visited, i, j, m, n, path, 0);
                paths.add(path);
            }
        }
        return paths.size();
    }
    public void dfs(int[][] grid, Set<String> visited, int i, int j, int m, int n, List<Integer> path, int p) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        if (visited.contains(i + "#" + j) || grid[i][j] == 0) {
            return;
        }
        visited.add(i + "#" + j);
        path.add(p);
        dfs(grid, visited, i + 1, j, m, n, path, 1);
        dfs(grid, visited, i - 1, j, m, n, path, 2);
        dfs(grid, visited, i, j - 1, m, n, path, 3);
        dfs(grid, visited, i, j + 1, m, n, path, 4);
        path.add(0);
    }        
}
