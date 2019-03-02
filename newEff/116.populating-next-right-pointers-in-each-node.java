/*
 * @lc app=leetcode id=116 lang=java
 *
 * [116] Populating Next Right Pointers in Each Node
 *
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
 *
 * algorithms
 * Medium (36.55%)
 * Total Accepted:    226.1K
 * Total Submissions: 616.2K
 * Testcase Example:  '{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}'
 *
 * You are given a perfect binary tree where all leaves are on the same level,
 * and every parent has two children. The binary tree has the following
 * definition:
 * 
 * 
 * struct Node {
 * ⁠ int val;
 * ⁠ Node *left;
 * ⁠ Node *right;
 * ⁠ Node *next;
 * }
 * 
 * 
 * Populate each next pointer to point to its next right node. If there is no
 * next right node, the next pointer should be set to NULL.
 * 
 * Initially, all next pointers are set to NULL.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * 
 * 
 * Input:
 * {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
 * 
 * Output:
 * {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}
 * 
 * Explanation: Given the above perfect binary tree (Figure A), your function
 * should populate each next pointer to point to its next right node, just like
 * in Figure B.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * You may only use constant extra space.
 * Recursive approach is fine, implicit stack space does not count as extra
 * space for this problem.
 * 
 * 
 */
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {


    public Node connect(Node root) {
        Node curFirst = root;
        while (curFirst != null) {
            Node iter = curFirst;
            Node nextFirst = null;
            Node prev = null;
            while (iter != null) {
                if (iter.left != null) {
                    if (nextFirst == null) {
                        nextFirst = iter.left;
                    }
                    if (prev != null) {
                        prev.next = iter.left;
                    }
                    prev = iter.left;
                }
                if (iter.right != null) {
                    if (nextFirst == null) {
                        nextFirst = iter.right;
                    }
                    if (prev != null) {
                        prev.next = iter.right;
                    }
                    prev = iter.right;
                }
                iter = iter.next;
            }
            curFirst = nextFirst;
        }
        return root;


    }

    public Node connect3(Node root) {
        Node cur = root;
        while (cur != null) {
            Node iter = cur;
            while (iter != null) {
                if (iter.left != null) {
                    iter.left.next = iter.right;
                }
                if (iter.right != null && iter.next != null) {
                    iter.right.next = iter.next.left;
                }
                iter = iter.next;
            }
            cur = cur.left;
        }
        return root;
    }
    public Node connect2(Node root) {
        if (root == null) {
            return root;
        }
        Deque<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.size() > 0) {
            int size = queue.size();
            Node prev = null;
            while (size > 0) {
                Node cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (prev != null) {
                    prev.next = cur;
                }
                prev = cur;
                size--;
                if (size == 0) {
                    cur.next = null;
                }
            }
        }
        return root;
    }
}
