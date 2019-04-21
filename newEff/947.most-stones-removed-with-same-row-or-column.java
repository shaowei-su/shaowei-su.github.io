/*
 * @lc app=leetcode id=947 lang=java
 *
 * [947] Most Stones Removed with Same Row or Column
 *
 * https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/description/
 *
 * algorithms
 * Medium (53.91%)
 * Total Accepted:    16.7K
 * Total Submissions: 30.8K
 * Testcase Example:  '[[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]'
 *
 * On a 2D plane, we place stones at some integer coordinate points.  Each
 * coordinate point may have at most one stone.
 * 
 * Now, a move consists of removing a stone that shares a column or row with
 * another stone on the grid.
 * 
 * What is the largest possible number of moves we can make?
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: stones = [[0,0]]
 * Output: 0
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= stones.length <= 1000
 * 0 <= stones[i][j] < 10000
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    Map<Integer, Integer> f = new HashMap<>();
    int num = 0;
    public int removeStones(int[][] stones) {
        for (int[] stone : stones) {
            union(stone[0], 10001 + stone[1]);
        }
        return stones.length - num;
    }
    public void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px != py) {
            num--;
            f.put(px, py);
        }
    }
    public int find(int x) {
        if (!f.containsKey(x)) {
            num++;
            f.put(x, x);
        }
        if (x != f.get(x)) {
            f.put(x, find(f.get(x)));
        }
        return f.get(x);
    }
}
