/*
 * @lc app=leetcode id=305 lang=java
 *
 * [305] Number of Islands II
 *
 * https://leetcode.com/problems/number-of-islands-ii/description/
 *
 * algorithms
 * Hard (41.32%)
 * Total Accepted:    50.3K
 * Total Submissions: 121.1K
 * Testcase Example:  '3\n3\n[[0,0],[0,1],[1,2],[2,1]]'
 *
 * A 2d grid map of m rows and n columns is initially filled with water. We may
 * perform an addLand operation which turns the water at position (row, col)
 * into a land. Given a list of positions to operate, count the number of
 * islands after each addLand operation. An island is surrounded by water and
 * is formed by connecting adjacent lands horizontally or vertically. You may
 * assume all four edges of the grid are all surrounded by water.
 * 
 * Example:
 * 
 * 
 * Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
 * Output: [1,1,2,3]
 * 
 * 
 * Explanation:
 * 
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water
 * and 1 represents land).
 * 
 * 
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * 
 * 
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 * 
 * 
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * 
 * 
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 * 
 * 
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * 
 * 
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 * 
 * 
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 * 
 * 
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 * 
 * 
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 * 
 * 
 * Follow up:
 * 
 * Can you do it in time complexity O(k log mn), where k is the length of the
 * positions?
 * 
 */
class Solution {
    class Union {
        int[] uf;
        int count;
        public Union(int m, int n) {
            uf = new int[m * n];
            count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    uf[i * n + j] = i * n + j;
                }
            }
        }
        public boolean union(int i, int j) {
            int p1 = find(i);
            int p2 = find(j);
            if (p1 != p2) {
                uf[p1] = p2;
                return true;
            }
            return false;
        }
        public int find(int i) {
            while (uf[i] != i) {
                uf[i] = uf[uf[i]];
                i = uf[i];
            }
            return i;
        }
    }
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (positions == null || positions.length == 0 || positions[0].length == 0) {
            return res;
        }
        Union u = new Union(m, n);
        Set<String> visited = new HashSet<>();
        int count = 0;
        for (int[] pos : positions) {
            count++;
            visited.add(pos[0] + "-" + pos[1]);
            if(visited.contains((pos[0] - 1) + "-" + pos[1])) {
                if (u.union(pos[0] * n + pos[1], (pos[0] - 1) * n + pos[1])) count--;
            }
            if(visited.contains((pos[0] + 1) + "-" + pos[1])) {
                if (u.union(pos[0] * n + pos[1], (pos[0] + 1) * n + pos[1])) count--;
            } 
            if(visited.contains(pos[0] + "-" + (pos[1] + 1))) {
                 if (u.union(pos[0] * n + pos[1], pos[0] * n + pos[1] + 1)) count--;
            }
            if(visited.contains(pos[0] + "-" + (pos[1] - 1))) {
                  if (u.union(pos[0] * n + pos[1], pos[0] * n + pos[1] - 1)) count--;
            }
            res.add(count);
        }
        return res;
    }
}
