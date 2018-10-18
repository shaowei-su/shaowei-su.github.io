/*
 * [156] Binary Tree Upside Down
 *
 * https://leetcode.com/problems/binary-tree-upside-down/description/
 *
 * algorithms
 * Medium (47.81%)
 * Total Accepted:    39.7K
 * Total Submissions: 82.9K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * Given a binary tree where all the right nodes are either leaf nodes with a
 * sibling (a left node that shares the same parent node) or empty, flip it
 * upside down and turn it into a tree where the original right nodes turned
 * into left leaf nodes. Return the new root.
 * 
 * Example:
 * 
 * 
 * Input: [1,2,3,4,5]
 * 
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   3
 * ⁠/ \
 * 4   5
 * 
 * Output: return the root of the binary tree [4,5,2,#,#,3,1]
 * 
 * ⁠  4
 * ⁠ / \
 * ⁠5   2
 * ⁠   / \
 * ⁠  3   1  
 * 
 * 
 * Clarification:
 * 
 * Confused what [4,5,2,#,#,3,1] means? Read more below on how binary tree is
 * serialized on OJ.
 * 
 * The serialization of a binary tree follows a level order traversal, where
 * '#' signifies a path terminator where no node exists below.
 * 
 * Here's an example:
 * 
 * 
 * ⁠  1
 * ⁠ / \
 * ⁠2   3
 * ⁠   /
 * ⁠  4
 * ⁠   \
 * ⁠    5
 * 
 * 
 * The above binary tree is serialized as [1,2,3,#,#,4,#,#,5].
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

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode prev = null;
        TreeNode prevRight = null;
        TreeNode cur = root;
        TreeNode next = null;
        while (cur != null) {
            next = cur.left;
            cur.left = prevRight;
            prevRight = cur.right;
            cur.right = prev;
            prev = cur;
            cur = next;
        }
        return prev;

    }
    public TreeNode upsideDownBinaryTree2(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (right == null && left == null) {
            return root;
        }
        TreeNode newLeft = upsideDownBinaryTree(root.left);
        left.left = right;
        left.right = root;
        root.left = null;
        root.right = null;
        return newLeft;
    }
}
