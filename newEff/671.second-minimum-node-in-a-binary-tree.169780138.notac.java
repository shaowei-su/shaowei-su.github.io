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
        if (root.left == null) {
            return -1;
        }
        int left = root.left.val == root.val ? findSecondMinimumValue(root.left) : root.left.val;
        int right = root.right.val == root.val ? findSecondMinimumValue(root.right) : root.right.val;
        if (left != -1 && right != -1) {
            return Math.min(left, right);
        } else if (left != -1) {
            return left;
        } else if (right != -1){
            return right;
        } else {
            return -1;
        }


    }   
    public int findSecondMinimumValue2(TreeNode root) {
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
