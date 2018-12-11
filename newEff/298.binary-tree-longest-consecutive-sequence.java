/*
 * [298] Binary Tree Longest Consecutive Sequence
 *
 * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/description/
 *
 * algorithms
 * Medium (42.83%)
 * Total Accepted:    53.8K
 * Total Submissions: 125.7K
 * Testcase Example:  '[1,null,3,2,4,null,null,null,5]'
 *
 * Given a binary tree, find the length of the longest consecutive sequence
 * path.
 * 
 * The path refers to any sequence of nodes from some starting node to any node
 * in the tree along the parent-child connections. The longest consecutive path
 * need to be from parent to child (cannot be the reverse).
 * 
 * Example 1:
 * 
 * 
 * Input:
 * 
 * ⁠  1
 * ⁠   \
 * ⁠    3
 * ⁠   / \
 * ⁠  2   4
 * ⁠       \
 * ⁠        5
 * 
 * Output: 3
 * 
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 * 
 * Example 2:
 * 
 * 
 * Input:
 * 
 * ⁠  2
 * ⁠   \
 * ⁠    3
 * ⁠   / 
 * ⁠  2    
 * ⁠ / 
 * ⁠1
 * 
 * Output: 2 
 * 
 * Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return
 * 2.
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
        return res[1];
    }
    public int[] helper(TreeNode root) {
       if (root == null) {
           return new int[] {0, 0};
       }
       int[] left = helper(root.left);
       int[] right = helper(root.right);
       if (root.left == null && root.right == null) {
           return new int[] {1, 1};
       }
       int curMax = 1;
       if (root.left != null && root.left.val == root.val + 1) {
           curMax = Math.max(curMax, left[0] + 1);
       } 
       if (root.right != null && root.right.val == root.val + 1) {
           curMax = Math.max(curMax, right[0] + 1);
       } 
       int[] res = new int[2];
       res[0] = curMax;
       res[1] = Math.max(curMax, Math.max(left[1], right[1]));
       return res;
    }
}
