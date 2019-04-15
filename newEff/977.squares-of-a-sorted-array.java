/*
 * @lc app=leetcode id=977 lang=java
 *
 * [977] Squares of a Sorted Array
 *
 * https://leetcode.com/problems/squares-of-a-sorted-array/description/
 *
 * algorithms
 * Easy (72.57%)
 * Total Accepted:    52K
 * Total Submissions: 71.8K
 * Testcase Example:  '[-4,-1,0,3,10]'
 *
 * Given an array of integers A sorted in non-decreasing order, return an array
 * of the squares of each number, also in sorted non-decreasing order.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A is sorted in non-decreasing order.
 * 
 * 
 * 
 */
class Solution {
    public int[] sortedSquares(int[] A) {
        int len = A.length;
        int[] res = new int[len];
        int left = 0, right = len - 1;
        for (int i = len - 1; i >= 0; i--) {
            if (Math.abs(A[left]) > Math.abs(A[right])) {
                res[i] = A[left] * A[left];
                left++;
            } else {
                res[i] = A[right] * A[right];
                right--;
            }
        }
        return res;


    }

    public int[] sortedSquares2(int[] A) {
        if (A == null) {
            return null;
        }
        int len = A.length;
        int[] res = new int[len];
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (A[i] < 0) {
                left.add(A[i] * A[i]);
            } else {
                right.add(A[i] * A[i]);
            }
        }
        int li = left.size() - 1;
        int ri = 0;
        int si = 0;
        while (li >= 0 && ri < right.size()) {
            if (left.get(li) < right.get(ri)) {
                res[si++] = left.get(li--);
            } else {
                res[si++] = right.get(ri++);
            }
        }
        while (li >= 0) {
            res[si++] = left.get(li--);
        }
        while (ri < right.size()) {
            res[si++] = right.get(ri++);
        }
        return res;
    }
}
