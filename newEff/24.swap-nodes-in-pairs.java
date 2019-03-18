/*
 * @lc app=leetcode id=24 lang=java
 *
 * [24] Swap Nodes in Pairs
 *
 * https://leetcode.com/problems/swap-nodes-in-pairs/description/
 *
 * algorithms
 * Medium (42.34%)
 * Total Accepted:    286.2K
 * Total Submissions: 659.8K
 * Testcase Example:  '[1,2,3,4]'
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 * 
 * You may not modify the values in the list's nodes, only nodes itself may be
 * changed.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * 
 * 
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode res = head.next;
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        while (head != null && head.next != null) {
            ListNode next = head.next;
            ListNode tmp = next.next;
            next.next = head;
            prev.next = next;
            prev = head;
            head.next = tmp;
            head = tmp;
        }

        return dummy.next;


    }
    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode nextt = swapPairs(next.next);
        next.next = head;
        head.next = nextt;
        return next;
    }
}
