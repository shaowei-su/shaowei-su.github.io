/*
 * @lc app=leetcode id=973 lang=java
 *
 * [973] K Closest Points to Origin
 *
 * https://leetcode.com/problems/k-closest-points-to-origin/description/
 *
 * algorithms
 * Medium (64.40%)
 * Total Accepted:    42.9K
 * Total Submissions: 67.3K
 * Testcase Example:  '[[1,3],[-2,2]]\n1'
 *
 * We have a list of points on the plane.  Find the K closest points to the
 * origin (0, 0).
 * 
 * (Here, the distance between two points on a plane is the Euclidean
 * distance.)
 * 
 * You may return the answer in any order.  The answer is guaranteed to be
 * unique (except for the order that it is in.)
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation: 
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just
 * [[-2,2]].
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 * 
 * 
 * 
 */
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int left = 0, right = points.length - 1;
        while (left <= right) {
            int mid = helper(points, left, right);
            if (mid == K) {
                break;
            } else if (mid < K) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    public int helper(int[][] points, int left, int right) {
        int[] pivot = points[left];
        while (left < right) {
            while (left < right && compare(pivot, points[right]) <= 0) {
                right--;
            }
            points[left] = points[right];
            while (left < right && compare(pivot, points[left]) >= 0) {
                left++;
            }
            points[right] = points[left];
        }
        points[left] = pivot;
        return left;
    }

    public void swap(int[][] points, int left, int right) {
        int[] tmp = points[left];
        points[left] = points[right];
        points[right] = tmp;
    }

    public int compare(int[] left, int[] right) {
        return left[0] * left[0] + left[1] * left[1] - (right[0] * right[0] + right[1] * right[1]);
    }

    public int[][] kClosest2(int[][] points, int K) {
        Arrays.sort(points, (a, b) -> {
            return a[0] * a[0] + a[1] * a[1] - (b[0] * b[0] + b[1] * b[1]);
        });
        int[][] res = Arrays.copyOfRange(points, 0, K);
        return res;
    }
}
