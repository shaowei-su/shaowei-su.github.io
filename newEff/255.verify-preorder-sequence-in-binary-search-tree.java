/*
 * [255] Verify Preorder Sequence in Binary Search Tree
 *
 * https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/description/
 *
 * algorithms
 * Medium (41.70%)
 * Total Accepted:    28.6K
 * Total Submissions: 68.5K
 * Testcase Example:  '[5,2,6,1,3]'
 *
 * Given an array of numbers, verify whether it is the correct preorder
 * traversal sequence of a binary search tree.
 * 
 * You may assume each number in the sequence is unique.
 * 
 * Consider the following binary search tree: 
 * 
 * 
 * ⁠    5
 * ⁠   / \
 * ⁠  2   6
 * ⁠ / \
 * ⁠1   3
 * 
 * Example 1:
 * 
 * 
 * Input: [5,2,6,1,3]
 * Output: false
 * 
 * Example 2:
 * 
 * 
 * Input: [5,2,1,3,6]
 * Output: true
 * 
 * Follow up:
 * Could you do it using only constant space complexity?
 * 
 */
class Solution {
    public boolean verifyPreorder2(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return true;
        }
        int root = preorder[0];
        int firstBigger = -1;
        for (int i = 1; i < preorder.length; i++) {
            if (preorder[i] > root) {
                firstBigger = i;
                break;
            }
        }
        if (firstBigger == -1) {
            return verifyPreorder(Arrays.copyOfRange(preorder, 1, preorder.length));
        } else {
            // check all bigger after first bigger
            for (int i = firstBigger; i < preorder.length; i++) {
                if (preorder[i] < root) {
                    return false;
                }
            }
            if (firstBigger == preorder.length - 1) {
                return verifyPreorder(Arrays.copyOfRange(preorder, 1, preorder.length - 1));
            } else {
                return verifyPreorder(Arrays.copyOfRange(preorder, 1, firstBigger)) && verifyPreorder(Arrays.copyOfRange(preorder, firstBigger, preorder.length));
            }
        }
    }
    public boolean verifyPreorder3(int[] preorder) {
        Stack<Integer> stack = new Stack<>();
        int min = Integer.MIN_VALUE;
        for (int i : preorder) {
            if (i < min) {
                return false;
            }
            while (stack.size() > 0 && i > stack.peek()) {
                min = stack.pop();
            }
            stack.push(i);
        }
        return true;
    }

    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE;
        int index = -1;
        for (int i : preorder) {
            if (i < low) {
                return false;
            }
            while (index >= 0 && preorder[index] < i) {
                low = preorder[index--];
            }
            preorder[++index] = i;
        }
        return true;
    }
}
