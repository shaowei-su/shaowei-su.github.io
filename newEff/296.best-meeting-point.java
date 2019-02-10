/*
 * @lc app=leetcode id=296 lang=java
 *
 * [296] Best Meeting Point
 *
 * https://leetcode.com/problems/best-meeting-point/description/
 *
 * algorithms
 * Hard (53.67%)
 * Total Accepted:    22.1K
 * Total Submissions: 41K
 * Testcase Example:  '[[1,0,0,0,1],[0,0,0,0,0],[0,0,1,0,0]]'
 *
 * A group of two or more people wants to meet and minimize the total travel
 * distance. You are given a 2D grid of values 0 or 1, where each 1 marks the
 * home of someone in the group. The distance is calculated using Manhattan
 * Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 * 
 * Example:
 * 
 * 
 * Input: 
 * 
 * 1 - 0 - 0 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * 
 * Output: 6 
 * 
 * Explanation: Given three people living at (0,0), (0,4), and
 * (2,2):
 * The point (0,2) is an ideal meeting point, as the total travel
 * distance 
 * of 2+2+2=6 is minimal. So return 6.
 * 
 */
class Solution {
    public int minTotalDistance(int[][] grid) {
         if (grid == null || grid.length == 0 || grid[0].length == 0) {
             return 0;
         }
         int m = grid.length;
         int n = grid[0].length;
         List<Integer> is = new ArrayList<>();
         List<Integer> js = new ArrayList<>();
         for (int i = 0; i < m; i++) {
             for (int j = 0; j < n; j++) {
                 if (grid[i][j] == 1) {
                     is.add(i);
                     js.add(j);
                 }
             }
         }
         int sum = 0;
         for (int i = 0; i < is.size(); i++) {
             sum += Math.abs(is.get(i) - is.get(is.size() / 2));
         }
         Collections.sort(js);
         for (int j = 0; j < js.size(); j++) {
             sum += Math.abs(js.get(j) - js.get(js.size() / 2));
         }

         return sum;
    }
    public int minTotalDistance2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] count = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, count, m, n, i, j);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (count[i][j] > 0) {
                min = Math.min(min, count[i][j]);
                }
            }
        }
        return min;
    }

    public final static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void bfs(int[][] grid, int[][] count, int m, int n, int i, int j) {
        int dist = 0;
        Set<String> visited = new HashSet<String>();
        Deque<int[]> queue = new LinkedList<>();
        visited.add(i + "-" + j);
        queue.offer(new int[]{i, j});
        while (queue.size() > 0) {
            int size = queue.size();
            while (size > 0) {
                int[] cur = queue.poll();
                count[cur[0]][cur[1]] += dist;
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if (x < 0 || x >= m || y < 0 || y >= n) {
                        continue;
                    }
                    if (visited.contains(x + "-" + y)) {
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

    public void dfs(int[][] grid, int[][] count, int m, int n, int i, int j, int dist, Set<String> visited) {
        count[i][j] += dist;
        visited.add(i + "-" + j);
        for (int[] dir : dirs) {
            int x = dir[0] + i;
            int y = dir[1] + j;
            if (visited.contains(x + "-" + y)) {
                continue;
            }
            if (x < 0 || x >= m || y < 0 || y >= n) {
                continue;
            }
            if (grid[x][y] == 1) {
                continue;
            }
            dfs(grid, count, m, n, x, y, dist++, visited);
        }
    }
            
}
