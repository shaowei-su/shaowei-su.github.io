/*
 * @lc app=leetcode id=99 lang=java
 *
 * [99] Recover Binary Search Tree
 *
 * https://leetcode.com/problems/recover-binary-search-tree/description/
 *
 * algorithms
 * Hard (33.44%)
 * Total Accepted:    111K
 * Total Submissions: 327.9K
 * Testcase Example:  '[1,3,null,null,2]'
 *
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * 
 * Recover the tree without changing its structure.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,3,null,null,2]
 * 
 * 1
 * /
 * 3
 * \
 * 2
 * 
 * Output: [3,1,null,null,2]
 * 
 * 3
 * /
 * 1
 * \
 * 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [3,1,4,null,null,2]
 * 
 * ⁠ 3
 * ⁠/ \
 * 1   4
 * /
 * 2
 * 
 * Output: [2,1,4,null,null,3]
 * 
 * ⁠ 2
 * ⁠/ \
 * 1   4
 * /
 * ⁠ 3
 * 
 * 
 * Follow up:
 * 
 * 
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
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
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode prev = root;
        TreeNode prevNode = null;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode first = null;
        TreeNode second = null;
        int prevVal = Integer.MIN_VALUE;
        while (stack.size() > 0 || prev != null) {
            while (prev != null) {
                stack.push(prev);
                prev = prev.left;
            }
            TreeNode cur = stack.pop();
            if (prevNode == null) {
                prevNode = cur;
            } else {

                if (prevNode.val > cur.val) {
                    if (first == null) {
                        first = prevNode;
                        second = cur;
                    } else {
                        second = cur;
                        break;
                    }
                } else if (second == null) {
                    if (prevNode.val > cur.val) {
                        second = cur;
                        break;
                    }
                }
                prevNode = cur;
            }
            prev = cur.right;
        }
        if (second == null) {
            second = prevNode;
        }
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
        return;
    }
}
