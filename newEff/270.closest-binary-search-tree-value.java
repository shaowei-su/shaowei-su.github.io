/*
 * [270] Closest Binary Search Tree Value
 *
 * https://leetcode.com/problems/closest-binary-search-tree-value/description/
 *
 * algorithms
 * Easy (41.58%)
 * Total Accepted:    62.8K
 * Total Submissions: 150.8K
 * Testcase Example:  '[4,2,5,1,3]\n3.714286'
 *
 * Given a non-empty binary search tree and a target value, find the value in
 * the BST that is closest to the target.
 * 
 * Note:
 * 
 * 
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest
 * to the target.
 * 
 * 
 * Example:
 * 
 * 
 * Input: root = [4,2,5,1,3], target = 3.714286
 * 
 * ⁠   4
 * ⁠  / \
 * ⁠ 2   5
 * ⁠/ \
 * 1   3
 * 
 * Output: 4
 * 
 * 
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int closestValue2(TreeNode root, double target) {
        if (root.val == target) {
            return root.val;
        }
        int est = root.val;
        if (target < root.val && root.left != null) {
            est = Math.abs(closestValue(root.left, target) - target) < Math.abs(est - target) ? closestValue(root.left, target) : est;
        }
        if (target > root.val && root.right != null) {
            est = Math.abs(closestValue(root.right, target) - target) < Math.abs(est - target) ? closestValue(root.right, target) : est;
        }
        return est;
    }
    public int closestValue(TreeNode root, double target) {
        double curMin = Double.MAX_VALUE;
        int res = root.val;
        while (root != null) {
            if (root.val < target) {
                double diff = Math.abs(root.val - target);
                if (diff < curMin) {
                    curMin = diff;
                    res = root.val;
                }
                root = root.right;
            } else if (root.val > target) {
                double diff = Math.abs(root.val - target);
                if (diff < curMin) {
                    curMin = diff;
                    res = root.val;
                }
                root = root.left;
            } else {
                return root.val;
            }
        }
        return res;
    }
}
