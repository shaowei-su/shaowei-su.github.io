/*
 * [653] Two Sum IV - Input is a BST
 *
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/
 *
 * algorithms
 * Easy (51.26%)
 * Total Accepted:    65.4K
 * Total Submissions: 127.4K
 * Testcase Example:  '[5,3,6,2,4,null,7]\n9'
 *
 * Given a Binary Search Tree and a target number, return true if there exist
 * two elements in the BST such that their sum is equal to the given target.
 * 
 * Example 1:
 * 
 * Input: 
 * ⁠   5
 * ⁠  / \
 * ⁠ 3   6
 * ⁠/ \   \
 * 2   4   7
 * 
 * Target = 9
 * 
 * Output: True
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: 
 * ⁠   5
 * ⁠  / \
 * ⁠ 3   6
 * ⁠/ \   \
 * 2   4   7
 * 
 * Target = 28
 * 
 * Output: False
 * 
 * 
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
    Set<Integer> visited = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        Deque<TreeNode> dec = new LinkedList<>();
        Deque<TreeNode> inc = new LinkedList<>();
        for (TreeNode cur = root; cur != null; cur = cur.left) {
            dec.push(cur);
        }
        for (TreeNode cur = root; cur != null; cur = cur.right) {
            inc.push(cur);
        }
        while (dec.size() != 0 && inc.size() != 0 && inc.peek() != dec.peek()) {
            int sum = inc.peek().val + dec.peek().val;
            if (sum == k) {
                return true;
            } else if (sum < k) {
                for (TreeNode cur = dec.pop().right; cur != null; cur = cur.left) {
                    dec.push(cur);
                }
            } else {
                for (TreeNode cur = inc.pop().left; cur != null; cur = cur.right) {
                    inc.push(cur);
                }
            }
        }
        return false;

    }
    public boolean findTarget2(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (visited.contains(k - root.val)) return true;
        visited.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }
}
