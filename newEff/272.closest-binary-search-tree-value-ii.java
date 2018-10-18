/*
 * [272] Closest Binary Search Tree Value II
 *
 * https://leetcode.com/problems/closest-binary-search-tree-value-ii/description/
 *
 * algorithms
 * Hard (41.46%)
 * Total Accepted:    27.3K
 * Total Submissions: 65.9K
 * Testcase Example:  '[4,2,5,1,3]\n3.714286\n2'
 *
 * Given a non-empty binary search tree and a target value, find k values in
 * the BST that are closest to the target.
 * 
 * Note:
 * 
 * 
 * Given target value is a floating point.
 * You may assume k is always valid, that is: k ≤ total nodes.
 * You are guaranteed to have only one unique set of k values in the BST that
 * are closest to the target.
 * 
 * 
 * Example:
 * 
 * 
 * Input: root = [4,2,5,1,3], target = 3.714286, and k = 2
 * 
 * ⁠   4
 * ⁠  / \
 * ⁠ 2   5
 * ⁠/ \
 * 1   3
 * 
 * Output: [4,3]
 * 
 * Follow up:
 * Assume that the BST is balanced, could you solve it in less than O(n)
 * runtime (where n = total nodes)?
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
    int totalCount = 0;
    public List<Integer> closestKValues2(TreeNode root, double target, int k) {
        TreeMap<Double, Set<Integer>> diffMap = new TreeMap<>();
        List<Integer> res = new ArrayList<>();
        dfs(diffMap, root, target, k);
        for (Set<Integer> s : diffMap.values()) {
            res.addAll(s);
        }

        return res;
    }
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<Integer> pred = new Stack<>();
        Stack<Integer> succ = new Stack<>();
        List<Integer> res = new ArrayList<>();
        inorder(root, target, true, pred);
        inorder(root, target, false, succ);

        while (k > 0) {
            if (pred.isEmpty()) {
                res.add(succ.pop());
            } else if (succ.isEmpty()) {
                res.add(pred.pop());
            } else if (Math.abs(succ.peek() - target) < Math.abs(pred.peek() - target)) {
                res.add(succ.pop());
            } else {
                res.add(pred.pop());
            }
            k--;
        }

        return res;
    }

    public void inorder(TreeNode root, double target, boolean isIn, Stack<Integer> st) {
        if (root == null) {
            return ;
        }
        inorder(isIn ? root.left : root.right, target, isIn, st);
        if ((isIn && root.val >= target) || (!isIn && root.val < target)) return;
        st.push(root.val);
        inorder(isIn ? root.right : root.left, target, isIn, st);
    }
    
    public void dfs(TreeMap<Double, Set<Integer>> diffMap, TreeNode root, double target, int k) {
        if (root == null) {
            return;
        }
        Double diff = Math.abs(root.val - target);
        if (totalCount < k) {
            Set<Integer> valSet = diffMap.computeIfAbsent(diff, s -> new HashSet<Integer>());
            valSet.add(root.val);
            totalCount += 1;
        } else {
            Double maxKey = diffMap.lastKey();
            if (diff < maxKey) {
                Set<Integer> valSet = diffMap.computeIfAbsent(diff, s -> new HashSet<Integer>());
                valSet.add(root.val);

                if (diffMap.get(maxKey).size() == 1) {
                    diffMap.remove(maxKey);
                } else {
                    Integer first = diffMap.get(maxKey).iterator().next();
                    diffMap.get(maxKey).remove(first);
                }
            }
        }
        dfs(diffMap, root.left, target, k);
        dfs(diffMap, root.right, target, k);
    }
}
