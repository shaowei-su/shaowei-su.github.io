/*
 * [103] Binary Tree Zigzag Level Order Traversal
 *
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
 *
 * algorithms
 * Medium (38.62%)
 * Total Accepted:    166.5K
 * Total Submissions: 430.6K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, return the zigzag level order traversal of its nodes'
 * values. (ie, from left to right, then right to left for the next level and
 * alternate between).
 * 
 * 
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 * 
 * 
 * return its zigzag level order traversal as:
 * 
 * [
 * ⁠ [3],
 * ⁠ [20,9],
 * ⁠ [15,7]
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs(res, root, 0);

        return res;
    }


    public void dfs(List<List<Integer>> res, TreeNode cur, int depth) {
        if (cur == null) {
            return;
        }
        if (depth >= res.size()) {
            List<Integer> temp = new ArrayList<>();
            res.add(temp);
        }
        List<Integer> curLevel = res.get(depth);
        if (depth % 2 == 1) {
            curLevel.add(0, cur.val);
        } else {
            curLevel.add(cur.val);
        }
        dfs(res, cur.left, depth + 1);
        dfs(res, cur.right, depth + 1);
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean reversed = false;
        while (queue.size() > 0) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            while (size > 0) {
                TreeNode cur = queue.poll();
                temp.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
                size--;
            }
            if (reversed) Collections.reverse(temp);
            reversed = !reversed;
            res.add(temp);
        }
        return res;
                
    }
}
