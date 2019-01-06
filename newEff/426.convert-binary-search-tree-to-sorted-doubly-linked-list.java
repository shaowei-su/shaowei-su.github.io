/*
 * [758] Convert Binary Search Tree to Sorted Doubly Linked List
 *
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/description/
 *
 * algorithms
 * Medium (46.41%)
 * Total Accepted:    14.4K
 * Total Submissions: 30.9K
 * Testcase Example:  '{"$id":"1","val":4,"left":{"$id":"2","val":2,"left":{"$id":"4","val":1,"left":null,"right":null},"right":{"$id":"5","val":3,"left":null,"right":null}},"right":{"$id":"3","val":5,"left":null,"right":null}}'
 *
 * Convert a BST to a sorted circular doubly-linked list in-place. Think of the
 * left and right pointers as synonymous to the previous and next pointers in a
 * doubly-linked list.
 * 
 * Let's take the following BST as an example, it may help you understand the
 * problem better:
 * 
 * 
 * 
 * 
 * 
 * We want to transform this BST into a circular doubly linked list. Each node
 * in a doubly linked list has a predecessor and successor. For a circular
 * doubly linked list, the predecessor of the first element is the last
 * element, and the successor of the last element is the first element.
 * 
 * The figure below shows the circular doubly linked list for the BST above.
 * The "head" symbol means the node it points to is the smallest element of the
 * linked list.
 * 
 * 
 * 
 * 
 * 
 * Specifically, we want to do the transformation in place. After the
 * transformation, the left pointer of the tree node should point to its
 * predecessor, and the right pointer should point to its successor. We should
 * return the pointer to the first element of the linked list.
 * 
 * The figure below shows the transformed BST. The solid line indicates the
 * successor relationship, while the dashed line means the predecessor
 * relationship.
 * 
 * 
 * 
 * 
 */
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node newRoot = helper(root);
        circleConnect(newRoot);
        return newRoot;
    }
    // build doubly linked list without cyclic
    public Node helper(Node root) {
        if (root == null) {
            return null;
        }
        Node left = helper(root.left);
        Node right = helper(root.right);
        if (left == null && right == null) {
            return root;
        } else if (left != null && right != null) {
            Node last = left;
            while (last.right != null) {
                last = last.right;
            }
            last.right = root;
            root.left = last;
            root.right = right;
            right.left = root;
            return left;
        } else if (left != null) {
            Node last = left;
            while (last.right != null) {
                last = last.right;
            }
            last.right = root;
            root.left = last;
            return left;
        } else {
            root.right = right;
            right.left = root;
            return root;
        }
    }

    public void circleConnect(Node root) {
        Node last = root;
        while (last.right != null) {
            last = last.right;
        }
        last.right = root;
        root.left = last;
    }
}
