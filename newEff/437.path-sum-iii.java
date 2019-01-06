/*
 * [437] Path Sum III
 *
 * https://leetcode.com/problems/path-sum-iii/description/
 *
 * algorithms
 * Easy (41.13%)
 * Total Accepted:    83.3K
 * Total Submissions: 202.5K
 * Testcase Example:  '[10,5,-3,3,2,null,11,3,-2,null,1]\n8'
 *
 * You are given a binary tree in which each node contains an integer value.
 * 
 * Find the number of paths that sum to a given value.
 * 
 * The path does not need to start or end at the root or a leaf, but it must go
 * downwards
 * (traveling only from parent nodes to child nodes).
 * 
 * The tree has no more than 1,000 nodes and the values are in the range
 * -1,000,000 to 1,000,000.
 * 
 * Example:
 * 
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * 
 * ⁠     10
 * ⁠    /  \
 * ⁠   5   -3
 * ⁠  / \    \
 * ⁠ 3   2   11
 * ⁠/ \   \
 * 3  -2   1
 * 
 * Return 3. The paths that sum to 8 are:
 * 
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
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
    class Result {
        List<Integer> sumEndsAt;
        int totalCount;
        public Result(List<Integer> sumEndsAt, int totalCount) {
            this.sumEndsAt = sumEndsAt;
            this.totalCount = totalCount;
        }
    }
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    public int pathSumFrom(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return (root.val == sum ? 1 : 0) + pathSumFrom(root.left, sum - root.val) + pathSumFrom(root.right, sum - root.val);

    }


    public int pathSum2(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        Result res = dfs(root, sum);
        return res.totalCount;
    }
    public Result dfs(TreeNode root, int target) {
        if (root == null) {
            return new Result(new ArrayList<>(), 0);
        }
        Result left = dfs(root.left, target);
        Result right = dfs(root.right, target);
        List<Integer> sumEndsAt = new ArrayList<>();
        int totalCount = 0;
        sumEndsAt.add(root.val);
        for (Integer i : left.sumEndsAt) {
            sumEndsAt.add(i + root.val);
        }
        for (Integer i : right.sumEndsAt) {
            sumEndsAt.add(i + root.val);
        }
        for (Integer i : sumEndsAt) {
            if (i == target) {
                totalCount++;
            }
        }
        return new Result(sumEndsAt, totalCount + left.totalCount + right.totalCount);
    }
}
