/*
 * @lc app=leetcode id=939 lang=java
 *
 * [939] Minimum Area Rectangle
 *
 * https://leetcode.com/problems/minimum-area-rectangle/description/
 *
 * algorithms
 * Medium (49.88%)
 * Total Accepted:    14.5K
 * Total Submissions: 28.9K
 * Testcase Example:  '[[1,1],[1,3],[3,1],[3,3],[2,2]]'
 *
 * Given a set of points in the xy-plane, determine the minimum area of a
 * rectangle formed from these points, with sides parallel to the x and y
 * axes.
 * 
 * If there isn't any rectangle, return 0.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
 * Output: 4
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 * Output: 2
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= points.length <= 500
 * 0 <= points[i][0] <= 40000
 * 0 <= points[i][1] <= 40000
 * All points are distinct.
 * 
 * 
 * 
 */
class Solution {
    public int minAreaRect(int[][] points) {
        Set<String> pset = new HashSet<>();
        for (int[] p : points) {
            pset.add("x" + p[0] + "y" + p[1]);
        }
        int minA = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i][0] == points[j][0] || points[i][1] == points[j][1]) {
                    continue;
                }
                if (!pset.contains("x" + points[i][0] + "y" + points[j][1])) {
                    continue;
                }
                if (!pset.contains("x" + points[j][0] + "y" + points[i][1])) {
                    continue;
                }
                minA = Math.min(minA, Math.abs((points[i][0] - points[j][0]) * (points[i][1] - points[j][1])));
            }
        }
        return minA == Integer.MAX_VALUE ? 0 : minA;
    }
}
