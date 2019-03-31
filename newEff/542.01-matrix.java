/*
 * @lc app=leetcode id=542 lang=java
 *
 * [542] 01 Matrix
 *
 * https://leetcode.com/problems/01-matrix/description/
 *
 * algorithms
 * Medium (34.90%)
 * Total Accepted:    38.6K
 * Total Submissions: 110.5K
 * Testcase Example:  '[[0,0,0],[0,1,0],[0,0,0]]'
 *
 * 
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for
 * each cell.
 * 
 * The distance between two adjacent cells is 1.
 * 
 * Example 1: 
 * Input:
 * 
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 
 * Output:
 * 
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 
 * 
 * 
 * Example 2: 
 * Input:
 * 
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * 
 * Output:
 * 
 * 0 0 0
 * 0 1 0
 * 1 2 1
 * 
 * 
 * 
 * Note:
 * 
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 * 
 * 
 * 
 */
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] res = new int[row][col];
        Deque<int[]> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    res[i][j] = 0;
                    queue.offer(new int[] {i, j});
                } else {
                    res[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        bfs(queue, res, row, col);
        
        return res;
    }

    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void bfs(Deque<int[]> queue, int[][] res, int row, int col) {
        int dist = 0;
        boolean[][] visited = new boolean[row][col];
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                int[] cur = queue.poll();
                res[cur[0]][cur[1]] = Math.min(res[cur[0]][cur[1]], dist);
                for (int[] dir : dirs) {
                    int x = dir[0] + cur[0];
                    int y = dir[1] + cur[1];
                    if (x < 0 || x >= row || y < 0 || y >= col) {
                        continue;
                    }
                    if (res[x][y] <= dist) {
                        continue;
                    }
                    if (visited[x][y]) {
                        continue;
                    }
                    visited[x][y] = true;
                    queue.offer(new int[] {x, y});
                }
                size--;
            }
            dist++;
        }
        return;
    }
}
