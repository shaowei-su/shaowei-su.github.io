/*
 * [113] Path Sum II
 *
 * https://leetcode.com/problems/path-sum-ii/description/
 *
 * algorithms
 * Medium (38.49%)
 * Total Accepted:    200.6K
 * Total Submissions: 521.1K
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,5,1]\n22'
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's
 * sum equals the given sum.
 * 
 * Note: A leaf is a node with no children.
 * 
 * Example:
 * 
 * Given the below binary tree and sum = 22,
 * 
 * 
 * ⁠     5
 * ⁠    / \
 * ⁠   4   8
 * ⁠  /   / \
 * ⁠ 11  13  4
 * ⁠/  \    / \
 * 7    2  5   1
 * 
 * 
 * Return:
 * 
 * 
 * [
 * ⁠  [5,4,11,2],
 * ⁠  [5,8,4,5]
 * ]
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> temp = new ArrayList<>();
        temp.add(root.val);
        dfs(res, temp, root, sum, root.val);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> temp, TreeNode root, int sum, int cur) {
        if (cur == sum && root.left == null && root.right == null) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (root.left != null) {
            temp.add(root.left.val);
            dfs(res, temp, root.left, sum, cur + root.left.val);
            temp.remove(temp.size() - 1);
        }
        if (root.right != null) {
            temp.add(root.right.val);
            dfs(res, temp, root.right, sum, cur + root.right.val);
            temp.remove(temp.size() - 1);
        }
    }
}
