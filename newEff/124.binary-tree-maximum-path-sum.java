/*
 * @lc app=leetcode id=124 lang=java
 *
 * [124] Binary Tree Maximum Path Sum
 *
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
 *
 * algorithms
 * Hard (28.92%)
 * Total Accepted:    172.6K
 * Total Submissions: 589.6K
 * Testcase Example:  '[1,2,3]'
 *
 * Given a non-empty binary tree, find the maximum path sum.
 * 
 * For this problem, a path is defined as any sequence of nodes from some
 * starting node to any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the
 * root.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,2,3]
 * 
 * ⁠      1
 * ⁠     / \
 * ⁠    2   3
 * 
 * Output: 6
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [-10,9,20,null,null,15,7]
 * 
 * -10
 * / \
 * 9  20
 * /  \
 * 15   7
 * 
 * Output: 42
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
    public int maxPathSum(TreeNode root) {
        int[] res = helper(root);
        return res[2];
    }

    public int[] helper(TreeNode root) {
        if (root == null) {
            return new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        }
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        int[] res = new int[3];
        res[0] = Math.max(0, Math.max(left[0], right[0])) + root.val;
        res[1] = Math.max(0, left[0]) + root.val + Math.max(0, right[0]);
        int max = Math.max(res[0], res[1]);
        res[2] = Math.max(max, Math.max(left[2], right[2]));
        return res;
    }
}
