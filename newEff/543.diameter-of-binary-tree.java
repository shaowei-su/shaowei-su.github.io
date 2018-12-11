/*
 * [543] Diameter of Binary Tree
 *
 * https://leetcode.com/problems/diameter-of-binary-tree/description/
 *
 * algorithms
 * Easy (45.50%)
 * Total Accepted:    96.1K
 * Total Submissions: 211.2K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * 
 * Given a binary tree, you need to compute the length of the diameter of the
 * tree. The diameter of a binary tree is the length of the longest path
 * between any two nodes in a tree. This path may or may not pass through the
 * root.
 * 
 * 
 * 
 * Example:
 * Given a binary tree 
 * 
 * ⁠         1
 * ⁠        / \
 * ⁠       2   3
 * ⁠      / \     
 * ⁠     4   5    
 * 
 * 
 * 
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * 
 * 
 * Note:
 * The length of path between two nodes is represented by the number of edges
 * between them.
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
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = findPath(root);
        return res[1] - 1;
    }
    
    public int[] findPath(TreeNode root) {
        if (root == null) {
            return new int[] {0, 0};
        }
        int[] lefts = findPath(root.left);
        int[] rights = findPath(root.right);
        int[] res = new int[2];
        res[0] = Math.max(lefts[0], rights[0]) + 1;
        res[1] = lefts[0] + rights[0] + 1;
        res[1] = Math.max(res[1], Math.max(lefts[1], rights[1]));
        return res;
    }
}
