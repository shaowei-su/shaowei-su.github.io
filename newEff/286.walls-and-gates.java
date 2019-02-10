/*
 * @lc app=leetcode id=286 lang=java
 *
 * [286] Walls and Gates
 *
 * https://leetcode.com/problems/walls-and-gates/description/
 *
 * algorithms
 * Medium (47.87%)
 * Total Accepted:    66.6K
 * Total Submissions: 138.3K
 * Testcase Example:  '[[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]'
 *
 * You are given a m x n 2D grid initialized with these three possible
 * values.
 * 
 * 
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647
 * to represent INF as you may assume that the distance to a gate is less than
 * 2147483647.
 * 
 * 
 * Fill each empty room with the distance to its nearest gate. If it is
 * impossible to reach a gate, it should be filled with INF.
 * 
 * Example: 
 * 
 * Given the 2D grid:
 * 
 * 
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 * ⁠ 0  -1 INF INF
 * 
 * 
 * After running your function, the 2D grid should be:
 * 
 * 
 * ⁠ 3  -1   0   1
 * ⁠ 2   2   1  -1
 * ⁠ 1  -1   2  -1
 * ⁠ 0  -1   3   4
 * 
 * 
 */
class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }
        int m = rooms.length;
        int n = rooms[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    bfs(rooms, i, j, m, n);
                }
            }
        }
    }

    public static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void bfs(int[][] rooms, int i, int j, int m, int n) {
        Deque<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new int[] {i, j});
        visited.add(i + "-" + j);
        int dist = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            while (size > 0) {
                int[] cur = queue.poll();
                rooms[cur[0]][cur[1]] = Math.min(dist, rooms[cur[0]][cur[1]]);
                for (int[] dir : dirs) {
                    int x = dir[0] + cur[0];
                    int y = dir[1] + cur[1];
                    if (x < 0 || x >= m || y < 0 || y >= n) {
                        continue;
                    }
                    if (visited.contains(x + "-" + y)) {
                        continue;
                    }
                    if (rooms[x][y] == 0 || rooms[x][y] == -1) {
                        continue;
                    }
                    visited.add(x + "-" + y);
                    queue.offer(new int[]{x, y});
                }
                size--;
            }
            dist++;
        }
    }
                
}
