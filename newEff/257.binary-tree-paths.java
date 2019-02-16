/*
 * [257] Binary Tree Paths
 *
 * https://leetcode.com/problems/binary-tree-paths/description/
 *
 * algorithms
 * Easy (42.93%)
 * Total Accepted:    181.2K
 * Total Submissions: 421.5K
 * Testcase Example:  '[1,2,3,null,5]'
 *
 * Given a binary tree, return all root-to-leaf paths.
 * 
 * Note: A leaf is a node with no children.
 * 
 * Example:
 * 
 * 
 * Input:
 * 
 * ⁠  1
 * ⁠/   \
 * 2     3
 * ⁠\
 * ⁠ 5
 * 
 * Output: ["1->2->5", "1->3"]
 * 
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs(root, res, "");
        return res;
    }

    public void dfs(TreeNode root, List<String> res, String cur) {
        String path = cur.length() == 0 ? String.valueOf(root.val) : cur + "->" + root.val;
        if (root.left == null && root.right == null) {
            res.add(path);
            return;
        }
        if (root.left != null) {
        dfs(root.left, res, path);
        }
        if (root.right != null) {
        dfs(root.right, res, path);
        }
    }
}
