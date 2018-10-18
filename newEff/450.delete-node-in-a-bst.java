/*
 * [450] Delete Node in a BST
 *
 * https://leetcode.com/problems/delete-node-in-a-bst/description/
 *
 * algorithms
 * Medium (37.80%)
 * Total Accepted:    41.1K
 * Total Submissions: 108.7K
 * Testcase Example:  '[5,3,6,2,4,null,7]\n3'
 *
 * Given a root node reference of a BST and a key, delete the node with the
 * given key in the BST. Return the root node reference (possibly updated) of
 * the BST.
 * 
 * Basically, the deletion can be divided into two stages:
 * 
 * Search for a node to remove.
 * If the node is found, delete the node.
 * 
 * 
 * 
 * Note: Time complexity should be O(height of tree).
 * 
 * Example:
 * 
 * root = [5,3,6,2,4,null,7]
 * key = 3
 * 
 * ⁠   5
 * ⁠  / \
 * ⁠ 3   6
 * ⁠/ \   \
 * 2   4   7
 * 
 * Given key to delete is 3. So we find the node with value 3 and delete it.
 * 
 * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
 * 
 * ⁠   5
 * ⁠  / \
 * ⁠ 4   6
 * ⁠/     \
 * 2       7
 * 
 * Another valid answer is [5,2,6,null,4,null,7].
 * 
 * ⁠   5
 * ⁠  / \
 * ⁠ 2   6
 * ⁠  \   \
 * ⁠   4   7
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        TreeNode dummy = new TreeNode(0);
        dummy.right = root;
        delete(dummy, root, key, false);
        return dummy.right;
    }
    public void delete(TreeNode parent, TreeNode root, int key, boolean isLeft) {
        if (root == null) {
            return;
        }
        if (root.val < key) {
            delete(root, root.right, key, false);
        } else if (root.val > key) {
            delete(root, root.left, key, true);
        } else {
         if (root.left == null && root.right == null) {
             if (isLeft) {
                 parent.left = null;
             } else {
                 parent.right = null;
             }
         } else if (root.left == null) {
             int leftmost = findLeftMostAndDel(root, root.right, false);
             root.val = leftmost;
         } else {
             int rightmost = findRightMostAndDel(root, root.left, true);
             root.val = rightmost;
         }
        }
    }
    public int findLeftMostAndDel(TreeNode parent, TreeNode root, boolean isLeft) {
        if (root.left == null) {
            if (root.right != null) {
                if (isLeft) {
                    parent.left = root.right;
                } else {
                    parent.right = root.right;
                }
            } else {
                if (isLeft) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            return root.val;
        } else {
            return findLeftMostAndDel(root, root.left, true);
        }
    }
    public int findRightMostAndDel(TreeNode parent, TreeNode root, boolean isLeft) {
        if (root.right == null) {
            if (root.left != null) {
                if (isLeft) {
                    parent.left = root.left;
                } else {
                    parent.right = root.left;
                }
            } else {
                if (isLeft) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            return root.val;
        } else {
            return findRightMostAndDel(root, root.right, false);
        }
    }
}
