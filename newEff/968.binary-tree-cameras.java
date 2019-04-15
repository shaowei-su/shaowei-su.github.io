/*
 * @lc app=leetcode id=968 lang=java
 *
 * [968] Binary Tree Cameras
 *
 * https://leetcode.com/problems/binary-tree-cameras/description/
 *
 * algorithms
 * Hard (34.30%)
 * Total Accepted:    5.3K
 * Total Submissions: 15.4K
 * Testcase Example:  '[0,0,null,0,0]'
 *
 * Given a binary tree, we install cameras on the nodes of the tree. 
 * 
 * Each camera at a node can monitor its parent, itself, and its immediate
 * children.
 * 
 * Calculate the minimum number of cameras needed to monitor all nodes of the
 * tree.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * Input: [0,0,null,0,0]
 * Output: 1
 * Explanation: One camera is enough to monitor all nodes if placed as
 * shown.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [0,0,null,0,null,0,null,null,0]
 * Output: 2
 * Explanation: At least two cameras are needed to monitor all nodes of the
 * tree. The above image shows one of the valid configurations of camera
 * placement.
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The number of nodes in the given tree will be in the range [1, 1000].
 * Every node has value 0.
 * 
 * 
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
    private static final int NOT_MONITOR = 0;
    private static final int MONITOR_WITHOUT = 1;
    private static final int MONITOR_WITH = 2;

    int count = 0;
    public int minCameraCover(TreeNode root) {
        //int top = dfs(root);
        return (dfs(root) == NOT_MONITOR ? 1 : 0) + count;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return MONITOR_WITHOUT;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        if (left == MONITOR_WITHOUT && right == MONITOR_WITHOUT) {
            return NOT_MONITOR;
        } else if (left == NOT_MONITOR || right == NOT_MONITOR) {
            count++;
            return MONITOR_WITH;
        } else {
            return MONITOR_WITHOUT;
        }
    }
}
