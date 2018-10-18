/*
 * [235] Lowest Common Ancestor of a Binary Search Tree
 *
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 *
 * algorithms
 * Easy (41.35%)
 * Total Accepted:    222.3K
 * Total Submissions: 537.6K
 * Testcase Example:  '[6,2,8,0,4,7,9,null,null,3,5]\n2\n8'
 *
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of
 * two given nodes in the BST.
 * 
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor
 * is defined between two nodes p and q as the lowest node in T that has both p
 * and q as descendants (where we allow a node to be a descendant of itself).”
 * 
 * Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * 
 * 
 * ⁠       _______6______
 * ⁠      /              \
 * ⁠   ___2__          ___8__
 * ⁠  /      \        /      \
 * ⁠  0      _4       7       9
 * ⁠        /  \
 * ⁠        3   5
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant
 * of itself 
 * ⁠            according to the LCA definition.
 * 
 * 
 * Note:
 * 
 * 
 * All of the nodes' values will be unique.
 * p and q are different and both values will exist in the BST.
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
    List<TreeNode> path = new ArrayList<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        return (root.val - p.val) * (root.val - q.val) < 1 ? root :
            lowestCommonAncestor(p.val < root.val ? root.left : root.right, p, q);
    }


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        findPath(root, p);
        List<TreeNode> pPath = new ArrayList<>(path);
        path.clear();
        findPath(root, q);
        List<TreeNode> qPath = new ArrayList<>(path);

        int n = 0;
        int m = 0;
        TreeNode first = null;
        while (n < pPath.size()  && m < qPath.size()) {
            if (qPath.get(m) != pPath.get(n)) {
                break;
            }
            first = pPath.get(n);
            n++;
            m++;
        }
        return first;
    }

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        while (true) {
            int large = Math.max(p.val, q.val);
            int small = Math.min(p.val, q.val);
            if (large >= root.val && small <= root.val) {
                break;
            } else if (root.val > large) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }


    public void findPath(TreeNode root, TreeNode n) {
        if (root == null) {
            return;
        }
        path.add(root);
        if (root.val == n.val) {
            return;
        } else if (root.val > n.val) {
            findPath(root.left, n);
        } else {
            findPath(root.right, n);
        }
    }


    public boolean findPath2(TreeNode root, TreeNode n) {
        if (root == null) {
            return false;
        }
        if (root.val == n.val) {
            path.add(root);
            return true;
        } else {
            if (findPath2(root.left, n) || findPath2(root.right, n)) {
                path.add(root);
                return true;
            } else {
                return false;
            }
        }
    }        
}
