/*
 * @lc app=leetcode id=106 lang=java
 *
 * [106] Construct Binary Tree from Inorder and Postorder Traversal
 *
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
 *
 * algorithms
 * Medium (37.25%)
 * Total Accepted:    140.8K
 * Total Submissions: 371.4K
 * Testcase Example:  '[9,3,15,20,7]\n[9,15,7,20,3]'
 *
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * 
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * 
 * For example, given
 * 
 * 
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * 
 * Return the following binary tree:
 * 
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) {
            return null;
        }
        if (inorder.length == 0 || postorder.length == 0) {
            return null;
        }
        int len = inorder.length;
        int rootVal = postorder[len - 1];

        TreeNode root = new TreeNode(rootVal);
        if (len == 1) {
            return root;
        }
        int inRootIndex = -1;
        for (int i = 0; i < len; i++) {
            if (inorder[i] == rootVal) {
                inRootIndex = i;
                break;
            }
        }
        root.left = buildTree(Arrays.copyOfRange(inorder, 0, inRootIndex), Arrays.copyOfRange(postorder, 0, inRootIndex));
        root.right = buildTree(Arrays.copyOfRange(inorder, inRootIndex + 1, len), Arrays.copyOfRange(postorder, inRootIndex, len - 1));
        return root;
    }
}
