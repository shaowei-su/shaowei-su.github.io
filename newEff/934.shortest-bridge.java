import java.util.*;
/*
 * @lc app=leetcode id=934 lang=java
 *
 * [934] Shortest Bridge
 *
 * https://leetcode.com/problems/shortest-bridge/description/
 *
 * algorithms
 * Medium (42.61%)
 * Total Accepted:    7.5K
 * Total Submissions: 17.1K
 * Testcase Example:  '[[0,1],[1,0]]'
 *
 * In a given 2D binary array A, there are two islands.  (An island is a
 * 4-directionally connected group of 1s not connected to any other 1s.)
 * 
 * Now, we may change 0s to 1s so as to connect the two islands together to
 * form 1 island.
 * 
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed
 * that the answer is at least 1.)
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [[0,1],[1,0]]
 * Output: 1
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= A.length = A[0].length <= 100
 * A[i][j] == 0 or A[i][j] == 1
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.shortestBridge(new int[][] {{0,1,0},{0,0,0},{0,0,1}});
    }
    public int shortestBridge(int[][] A) {
        int row = A.length;
        int col = A[0].length;
        int count = Integer.MAX_VALUE;
        Deque<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            boolean found = false;
            for (int j = 0; j < col; j++) {
                if (A[i][j] == 1) {
                    dfs(A, i, j, row, col, visited, queue);
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }

         int step = 0;
         while (queue.size() > 0) {
             int size = queue.size();
             while (size > 0) {
                 int[] cur = queue.poll();
                 for (int[] dir : dirs) {
                     int x = cur[0] + dir[0];
                     int y = cur[1] + dir[1];
                     if (x < 0 || x >= row || y < 0 || y >= col) {
                         continue;
                     }
                     if (visited[x][y]) {
                         continue;
                     }
                     if (A[x][y] == 1) {
                         return step;
                     }
                     visited[x][y] = true;
                     int[] next = new int[]{x, y};
                     queue.offer(next);
                 }
                 size--;
             }
             step++;
         }
         return 0;
    }

    public void dfs(int[][] A, int i, int j, int row, int col, boolean[][] visited, Deque<int[]> queue) {
        visited[i][j] = true;
        queue.offer(new int[] {i, j});
       for (int[] dir : dirs) {
           int x = i + dir[0];
           int y = j + dir[1];
           if (x < 0 || x >= row || y < 0 || y >= col) {
               continue;
           }
           if (A[x][y] == 0) {
               continue;
           }
           if (visited[x][y]) {
               continue;
           }
           dfs(A, x, y, row, col, visited, queue);
       }
    }


    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
/*
    public int bfs(int[][] A, int i, int j, int row, int col, ) {
        queue.offer(new int[]{i, j});
        Set<String> visited = new HashSet<>();
        int step = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            while (size > 0) {
                int[] cur = queue.poll();
                visited.add(cur[0] + "#" + cur[1]);
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if (x < 0 || x >= row || y < 0 || y >= col) {
                        continue;
                    }
                    if (visited.contains(x + "#" + y)) {
                        continue;
                    }
                    if (oldVisit.contains(x + "#" + y)) {
                        continue;
                    }
                    if (A[x][y] == 1) {
                        return step;
                    }
                    int[] next = new int[]{x, y};
                    queue.offer(next);
                }
                size--;
            }
            step++;
        }
        return Integer.MAX_VALUE;
    }
                   */

}
