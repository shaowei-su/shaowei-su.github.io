/*
 * [101] Symmetric Tree
 *
 * https://leetcode.com/problems/symmetric-tree/description/
 *
 * algorithms
 * Easy (41.17%)
 * Total Accepted:    281.7K
 * Total Submissions: 684.2K
 * Testcase Example:  '[1,2,2,3,4,4,3]'
 *
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric
 * around its center).
 * 
 * 
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 * 
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   2
 * ⁠/ \ / \
 * 3  4 4  3
 * 
 * 
 * 
 * But the following [1,2,2,null,3,null,3]  is not:
 * 
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   2
 * ⁠  \   \
 * ⁠  3    3
 * 
 * 
 * 
 * 
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
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
    public boolean isSymmetric4(TreeNode root) {
        return root == null || helper(root.left, root.right);
    }
    public boolean helper(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }
        if (left.val != right.val) {
            return false;
        }
        return helper(left.left, right.right) && helper(left.right, right.left);
    }
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        Deque<TreeNode> stack = new LinkedList<>();
        if (root.left != null) {
            if (root.right == null) return false;
            stack.push(root.left);
            stack.push(root.right);
        } else if (root.right != null) {
            return false;
        }
        while (stack.size() > 0) {
            if (stack.size() % 2 != 0) return false;
            TreeNode right = stack.pop();
            TreeNode left = stack.pop();
            if (right.val != left.val) return false;
            if (left.left != null) {
                if (right.right == null) return false;
                stack.push(left.left);
                stack.push(right.right);
            } else if (right.right != null) {
                return false;
            }

            if (left.right != null) {
                if (right.left == null) return false;
                stack.push(left.right);
                stack.push(right.left);
            } else if (right.left != null) {
                return false;
            }
        }
        return true;
    }
    public boolean isSymmetric3(TreeNode root) {
        if (root == null) {
            return true;
        }
        List<TreeNode> list = new ArrayList<>();
        TreeNode ph = new TreeNode(-1);
        list.add(root);
        boolean hasValid = true;
        while (hasValid) {
            hasValid = false;
            List<TreeNode> temp = new ArrayList<>();
            for (TreeNode tn : list) {
                if (tn.equals(ph)) {
                    temp.add(ph);
                    temp.add(ph);
                    continue;
                }
                if (tn.left != null) {
                    temp.add(tn.left);
                    hasValid = true;
                } else {
                    temp.add(ph);
                }
                if (tn.right != null) {
                    temp.add(tn.right);
                    hasValid = true;
                } else {
                    temp.add(ph);
                }
            }
            if (!isSymm(temp, ph)) return false;
            list = temp;
        }
        return true;
    }
    
    public boolean isSymm(List<TreeNode> list, TreeNode ph) {
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (list.get(left).equals(ph) && list.get(right).equals(ph)) {
                left++;
                right--;
                continue;
            }
            if (list.get(left).equals(ph) || list.get(right).equals(ph)) {
                return false;
            }
            if (list.get(left).val != list.get(right).val) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return false;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
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
            if (!isSym(temp)) return false;
        }
        return true;
    }
    public boolean isSym(List<Integer> list) {
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (list.get(left).intValue() != list.get(right).intValue()) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
