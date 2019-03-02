/*
 * @lc app=leetcode id=108 lang=java
 *
 * [108] Convert Sorted Array to Binary Search Tree
 *
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
 *
 * algorithms
 * Easy (48.43%)
 * Total Accepted:    237.9K
 * Total Submissions: 483.5K
 * Testcase Example:  '[-10,-3,0,5,9]'
 *
 * Given an array where elements are sorted in ascending order, convert it to a
 * height balanced BST.
 * 
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more
 * than 1.
 * 
 * Example:
 * 
 * 
 * Given the sorted array: [-10,-3,0,5,9],
 * 
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following
 * height balanced BST:
 * 
 * ⁠     0
 * ⁠    / \
 * ⁠  -3   9
 * ⁠  /   /
 * ⁠-10  5
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
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int mid = nums.length / 2;
        TreeNode root = new TreeNode(0);
        Deque<TreeNode> nodes = new LinkedList<TreeNode>() {{push(root);}};
        Deque<Integer> lefts = new LinkedList<Integer>(){{push(0);}};
        Deque<Integer> rights = new LinkedList<Integer>(){{push(nums.length-  1);}};
        while (!nodes.isEmpty()) {
            TreeNode cur = nodes.pop();
            int left = lefts.pop();
            int right = rights.pop();
            mid = left + (right - left) / 2;
            cur.val = nums[mid];
            if (left < mid) {
                TreeNode t = new TreeNode(0);
                nodes.push(t);
                cur.left = t;
                lefts.push(left);
                rights.push(mid - 1);
            }
            if (right > mid) {
                TreeNode t = new TreeNode(0);
                nodes.push(t);
                cur.right = t;
                lefts.push(mid + 1);
                rights.push(right);
            }
        }
        return root;

    }
    public TreeNode sortedArrayToBST2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int mid = nums.length / 2;
        
        TreeNode root = new TreeNode(nums[mid]);
        TreeNode left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, mid));
        TreeNode right = sortedArrayToBST(Arrays.copyOfRange(nums, mid + 1, nums.length));
        root.left = left;
        root.right = right;
        return root;
    }
}
