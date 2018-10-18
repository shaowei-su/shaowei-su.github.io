/*
 * [138] Copy List with Random Pointer
 *
 * https://leetcode.com/problems/copy-list-with-random-pointer/description/
 *
 * algorithms
 * Medium (25.60%)
 * Total Accepted:    185.8K
 * Total Submissions: 725.8K
 * Testcase Example:  '{}'
 *
 * 
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 * 
 * 
 * 
 * Return a deep copy of the list.
 * 
 */
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        Map<RandomListNode, RandomListNode> old2new = new HashMap<>();
        RandomListNode newHead = new RandomListNode(head.label);
        RandomListNode cur = head.next;
        old2new.put(head, newHead);
        RandomListNode iter = newHead;
        while (cur != null) {
            RandomListNode newNode = new RandomListNode(cur.label);
            old2new.put(cur, newNode);
            iter.next = newNode;
            iter = newNode;
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            RandomListNode point2 = cur.random;
            RandomListNode newCur = old2new.get(cur);
            RandomListNode newPoint2 = old2new.get(point2);
            newCur.random = newPoint2;
            cur = cur.next;
        }
        return newHead;
    }
}
