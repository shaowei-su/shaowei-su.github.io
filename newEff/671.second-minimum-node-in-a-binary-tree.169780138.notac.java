/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 *
 * '[2,2,5,null,null,5,7]'
 */
class Solution {
    int min;
    int sedMin = Integer.MAX_VALUE;
    
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null || root.left == null || root.right == null) {
            return -1;
        }
        min = root.val;
        dfs(root);
        return sedMin < Integer.MAX_VALUE ? sedMin : -1;
    }
    public void dfs(TreeNode root) {
        if (root != null) {
            if (root.val > min && root.val < sedMin) {
                sedMin = root.val;
            } else if (root.val == min) {
                dfs(root.left);
                dfs(root.right);
            }
        }
    }
}
