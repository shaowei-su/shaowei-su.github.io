import java.util.*;
/*
 * @lc app=leetcode id=317 lang=java
 *
 * [317] Shortest Distance from All Buildings
 *
 * https://leetcode.com/problems/shortest-distance-from-all-buildings/description/
 *
 * algorithms
 * Hard (36.38%)
 * Total Accepted:    35K
 * Total Submissions: 95.6K
 * Testcase Example:  '[[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]'
 *
 * You want to build a house on an empty land which reaches all buildings in
 * the shortest amount of distance. You can only move up, down, left and right.
 * You are given a 2D grid of values 0, 1 or 2, where:
 * 
 * 
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * 
 * 
 * Example:
 * 
 * 
 * Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * 
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * 
 * Output: 7 
 * 
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle
 * at (0,2),
 * ⁠            the point (1,2) is an ideal empty land to build a house, as the
 * total 
 * travel distance of 3+3+1=7 is minimal. So return 7.
 * 
 * Note:
 * There will be at least one building. If it is not possible to build such
 * house according to the above rules, return -1.
 * 
 */
class Solution {
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int r = grid.length;
        int c = grid[0].length;
        int[][] dist = new int[r][c];
        int[][] count = new int[r][c];
        int countOnes = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                countOnes++;
                bfs(grid, dist, i, j, r, c, count);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                    if (dist[i][j] > 0 && count[i][j] == countOnes) {
                        min = Math.min(min, dist[i][j]);
                    }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    private final static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public void bfs(int[][] grid, int[][] dist, int i, int j, int r, int c, int[][] count) {
        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});
        Set<String> visited = new HashSet<>();
        visited.add(i + "-" + j);
        int curDist = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            while (size > 0) {
                int[] cur = queue.poll();
                dist[cur[0]][cur[1]] += curDist;
                count[cur[0]][cur[1]]++;
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if (x < 0 || x >= r || y <0 || y >= c) {
                        continue;
                    }
                    if (grid[x][y] != 0) {
                        continue;
                    }
                    if (visited.contains(x + "-" + y)) {
                        continue;
                    }
                    visited.add(x + "-" + y);
                    queue.offer(new int[] {x, y});
                }
                size--;
            }
            curDist++;
        }

    }

    public static void main(String[] args) {
        int[][] grid = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        Solution sol = new Solution();
        int res = sol.shortestDistance(grid);
        System.out.println(res);
    }

}
