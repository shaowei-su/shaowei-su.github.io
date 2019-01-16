/*
 * @lc app=leetcode id=378 lang=java
 *
 * [378] Kth Smallest Element in a Sorted Matrix
 *
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
 *
 * algorithms
 * Medium (47.66%)
 * Total Accepted:    89.5K
 * Total Submissions: 187.6K
 * Testcase Example:  '[[1,5,9],[10,11,13],[12,13,15]]\n8'
 *
 * Given a n x n matrix where each of the rows and columns are sorted in
 * ascending order, find the kth smallest element in the matrix.
 * 
 * 
 * Note that it is the kth smallest element in the sorted order, not the kth
 * distinct element.
 * 
 * 
 * Example:
 * 
 * matrix = [
 * ⁠  [ 1,  5,  9],
 * ⁠  [10, 11, 13],
 * ⁠  [12, 13, 15]
 * ],
 * k = 8,
 * 
 * return 13.
 * 
 * 
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ n^2.
 */
class Solution {

    class Entry {
        int row;
        int col;
        int value;
        public Entry(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
    int left = matrix[0][0];
    int row = matrix.length;
    int col = matrix[0].length;
    int right = matrix[row - 1][col - 1] + 1;
    while (left < right) {
        int mid = left + (right - left) / 2;
        int count = 0, j = col - 1;
        for (int i = 0; i < row; i++) {
            while (j >= 0 && matrix[i][j] > mid) j--;
            count += (j + 1);
        }
        if (count < k) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }

    return left;

    }
    public int kthSmallest2(int[][] matrix, int k) {
        int[] index = new int[matrix.length];
        PriorityQueue<Entry> pq = new PriorityQueue<>((a, b) -> a.value - b.value);
        for (int i = 0; i < matrix.length; i++) {
            pq.offer(new Entry(i, 0, matrix[i][0]));
        }
        int count = 1;
        while (count < k) {
            Entry cur = pq.poll();
            int curRow = cur.row;
            int curCol = cur.col;
            if (curCol < matrix[curRow].length - 1) {
                pq.offer(new Entry(curRow, curCol + 1, matrix[curRow][curCol + 1]));
            }
            count++;
        }
        return pq.poll().value; 
    }
}
