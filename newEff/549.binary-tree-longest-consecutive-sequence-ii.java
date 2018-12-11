/*
 * [549] Binary Tree Longest Consecutive Sequence II
 *
 * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/description/
 *
 * algorithms
 * Medium (43.17%)
 * Total Accepted:    13.1K
 * Total Submissions: 30.3K
 * Testcase Example:  '[1,2,3,4]'
 *
 * Given a binary tree, you need to find the length of Longest Consecutive Path
 * in Binary Tree. 
 * 
 * Especially, this path can be either increasing or decreasing. For example,
 * [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is
 * not valid. On the other hand, the path can be in the child-Parent-child
 * order, where not necessarily be parent-child order.
 * 
 * Example 1:
 * 
 * Input:
 * ⁠       1
 * ⁠      / \
 * ⁠     2   3
 * Output: 2
 * Explanation: The longest consecutive path is [1, 2] or [2, 1].
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * ⁠       2
 * ⁠      / \
 * ⁠     1   3
 * Output: 3
 * Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 * 
 * 
 * 
 * Note:
 * All the values of tree nodes are in the range of [-1e7, 1e7].
 * ⁠
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
    public int longestConsecutive(TreeNode root) {
        int[] res = helper(root);
        return res[2];
    }
    public int[] helper(TreeNode root) {
        // res[3] : 0: inc ends ; 1: dec ends; 2: global 
        if (root == null) {
            return new int[] {0, 0, 0};
        }
        int[] lefts = helper(root.left);
        int[] rights = helper(root.right);
        int[] res = new int[3];
        Arrays.fill(res, 1);
        if (root.left != null) {
            if (root.left.val == root.val + 1) {
                res[1] = Math.max(res[1], lefts[1] + 1);
            } else if (root.left.val == root.val - 1) {
                res[0] = Math.max(res[0], lefts[0] + 1);
            }
        }
        if (root.right != null) {
            if (root.right.val == root.val + 1) {
                res[1] = Math.max(res[1], rights[1] + 1);
            } else if (root.right.val == root.val - 1) {
                res[0] = Math.max(res[0], rights[0] + 1);
            }
        }
        if (root.left != null && root.right != null) {
            if (root.left.val + 1 == root.val && root.val + 1 == root.right.val) {
                res[2] = Math.max(res[2], lefts[0] + rights[1] + 1);
            }
            if (root.left.val - 1 == root.val && root.val - 1 == root.right.val) {
                res[2] = Math.max(res[2], lefts[1] + rights[0] + 1);
            }
        }
        res[2] = Math.max(res[2], Math.max(res[0], res[1]));
        res[2] = Math.max(res[2], Math.max(lefts[2], rights[2]));
        return res;
    }
}
