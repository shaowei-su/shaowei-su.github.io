/*
 * [149] Max Points on a Line
 *
 * https://leetcode.com/problems/max-points-on-a-line/description/
 *
 * algorithms
 * Hard (15.27%)
 * Total Accepted:    102.8K
 * Total Submissions: 673.1K
 * Testcase Example:  '[[1,1],[2,2],[3,3]]'
 *
 * Given n points on a 2D plane, find the maximum number of points that lie on
 * the same straight line.
 * 
 * Example 1:
 * 
 * 
 * Input: [[1,1],[2,2],[3,3]]
 * Output: 3
 * Explanation:
 * ^
 * |
 * |        o
 * |     o
 * |  o  
 * +------------->
 * 0  1  2  3  4
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 * Explanation:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 * 
 * 
 */
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
class Solution {
    public int maxPoints(Point[] points) {
       if (points == null || points.length == 0) {
           return 0;
       }
       if (points.length < 3) return points.length;
       int count = 0;
       Map<Double, Integer> slope = new HashMap<>();
       for (int i = 0; i < points.length - 1; i++) {
           slope.clear();
           int samep = 0;
           for (int j = i + 1; j < points.length; j++) {
               if (j == i) {
                   continue;
               }
               if (points[i].x == points[j].x && points[i].y == points[j].y) {
                   samep++;
               }
               if (points[i].x == points[j].x) {
                   slope.put(Double.MAX_VALUE, slope.getOrDefault(Double.MAX_VALUE, 1) + 1);
                   continue;
               }
               double s = (points[i].y - points[j].y) / (points[i].x - points[j].x);
               slope.put(s, slope.getOrDefault(s, 1) + 1);
           }
           for (Map.Entry<Double, Integer> entry : slope.entrySet()) {
               if (entry.getKey().equals(Double.MAX_VALUE)) {
                   count = Math.max(count, entry.getValue());
               } else {
                   count = Math.max(count, entry.getValue() + samep);
               }
           }
       }
       return count;
    }
}
