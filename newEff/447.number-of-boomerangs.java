/*
 * [447] Number of Boomerangs
 *
 * https://leetcode.com/problems/number-of-boomerangs/description/
 *
 * algorithms
 * Easy (48.45%)
 * Total Accepted:    47.4K
 * Total Submissions: 97.8K
 * Testcase Example:  '[[0,0],[1,0],[2,0]]'
 *
 * Given n points in the plane that are all pairwise distinct, a "boomerang" is
 * a tuple of points (i, j, k) such that the distance between i and j equals
 * the distance between i and k (the order of the tuple matters).
 * 
 * Find the number of boomerangs. You may assume that n will be at most 500 and
 * coordinates of points are all in the range [-10000, 10000] (inclusive).
 * 
 * Example:
 * 
 * Input:
 * [[0,0],[1,0],[2,0]]
 * 
 * Output:
 * 2
 * 
 * Explanation:
 * The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 * 
 * 
 */
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int count = 0 ;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> subMap = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (j == i) continue;
                    int dist = computeDist(points[i], points[j]);
                    subMap.put(dist, subMap.getOrDefault(dist, 0) + 1);
            }
            for (Integer val : subMap.values()) {
                if (val > 1) {
                    count += val * (val - 1);
                }
            }
        }
        return count;
    }
    public int computeDist(int[] i, int[] j) {
        return (int)(Math.pow(i[0] - j[0], 2) + Math.pow(i[1] - j[1], 2));
    }
}
