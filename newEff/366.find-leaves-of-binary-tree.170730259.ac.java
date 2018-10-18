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
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }
        Map<Integer, Set<TreeNode>> depthMap = new HashMap<Integer, Set<TreeNode>>();
        int rootDepth = depth(root, depthMap);
        for (int i = 1; i <= rootDepth; i++) {
            List<Integer> l = new ArrayList<Integer>();
            for (TreeNode t : depthMap.get(i)) {
                l.add(t.val);
            }
            res.add(l);
        }
        return res;
    }
    public int depth(TreeNode root, Map<Integer, Set<TreeNode>> depthMap) {
        if (root == null) {
            return 0;
        }
        int d = Math.max(depth(root.left, depthMap), depth(root.right, depthMap)) + 1;
        Set<TreeNode> s = depthMap.get(d);
        if (s == null) {
            s = new HashSet<TreeNode>();
            depthMap.put(d, s);
        }
        s.add(root);
        return d;
    }
}
