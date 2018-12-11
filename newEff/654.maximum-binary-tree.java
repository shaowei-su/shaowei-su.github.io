/*
 * [654] Maximum Binary Tree
 *
 * https://leetcode.com/problems/maximum-binary-tree/description/
 *
 * algorithms
 * Medium (71.60%)
 * Total Accepted:    57.1K
 * Total Submissions: 79.7K
 * Testcase Example:  '[3,2,1,6,0,5]'
 *
 * 
 * Given an integer array with no duplicates. A maximum tree building on this
 * array is defined as follow:
 * 
 * The root is the maximum number in the array. 
 * The left subtree is the maximum tree constructed from left part subarray
 * divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray
 * divided by the maximum number. 
 * 
 * 
 * 
 * 
 * Construct the maximum tree by the given array and output the root node of
 * this tree.
 * 
 * 
 * Example 1:
 * 
 * Input: [3,2,1,6,0,5]
 * Output: return the tree root node representing the following tree:
 * 
 * ⁠     6
 * ⁠   /   \
 * ⁠  3     5
 * ⁠   \    / 
 * ⁠    2  0   
 * ⁠      \
 * ⁠       1
 * 
 * 
 * 
 * Note:
 * 
 * The size of the given array will be in the range [1,1000].
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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> stack = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            TreeNode cur = new TreeNode(nums[i]);
            while (stack.size() > 0 && stack.peek().val < nums[i]) {
                cur.left = stack.pop();
            }
            if (stack.size() > 0) {
                stack.peek().right = cur;
            }
            stack.push(cur);
        }
        return stack.size() == 0 ? null : stack.removeLast();

    }
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int ind = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                ind = i;
            }
        }
        TreeNode left = ind == 0 ? null : constructMaximumBinaryTree(Arrays.copyOfRange(nums, 0, ind));
        TreeNode right = (ind == nums.length - 1) ? null : constructMaximumBinaryTree(Arrays.copyOfRange(nums, ind + 1, nums.length));
        TreeNode root = new TreeNode(max);
        root.left = left;
        root.right = right;
        return root;
    }
}
