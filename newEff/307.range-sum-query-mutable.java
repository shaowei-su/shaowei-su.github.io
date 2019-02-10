/*
 * @lc app=leetcode id=307 lang=java
 *
 * [307] Range Sum Query - Mutable
 *
 * https://leetcode.com/problems/range-sum-query-mutable/description/
 *
 * algorithms
 * Medium (26.66%)
 * Total Accepted:    63.7K
 * Total Submissions: 235.6K
 * Testcase Example:  '["NumArray","sumRange","update","sumRange"]\n[[[1,3,5]],[0,2],[1,2],[0,2]]'
 *
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i ≤ j), inclusive.
 * 
 * The update(i, val) function modifies nums by updating the element at index i
 * to val.
 * 
 * Example:
 * 
 * 
 * Given nums = [1, 3, 5]
 * 
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * 
 * 
 * Note:
 * 
 * 
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is
 * distributed evenly.
 * 
 * 
 */
class NumArray {
    class STree {
        STree left;
        STree right;
        int start;
        int end;
        int sum;
        public STree(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    STree root = null;
    public NumArray(int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);
    }
    public STree buildTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        } else {
            STree res = new STree(start, end);
            if (start == end) {
                res.sum = nums[start];
            } else {
                int mid = start + (end - start) / 2;
                res.left = buildTree(nums, start, mid);
                res.right = buildTree(nums, mid + 1, end);
                res.sum = res.left.sum + res.right.sum;
            }
            return res;
        }
    }
    
    public void update(int i, int val) {
        update(root, i, val);   
    }

    public void update(STree root, int i, int val) {
        if (root.start == root.end) {
            root.sum = val;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            if (i <= mid) {
                update(root.left, i, val);
            } else {
                update(root.right, i, val);
            }
            root.sum = root.left.sum + root.right.sum;
        }
    }
    
    public int sumRange(int i, int j) {
        return sumRange(root, i, j);    
    }

    public int sumRange(STree root, int i, int j) {
        if (root.start == i && root.end == j) {
            return root.sum;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            if (j <= mid) {
                return sumRange(root.left, i, j);
            } else if (i > mid) {
                return sumRange(root.right, i, j);
            } else {
                return sumRange(root.left, i, mid) + sumRange(root.right, mid + 1, j);
            }
        }
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
