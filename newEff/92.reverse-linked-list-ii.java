/*
 * @lc app=leetcode id=92 lang=java
 *
 * [92] Reverse Linked List II
 *
 * https://leetcode.com/problems/reverse-linked-list-ii/description/
 *
 * algorithms
 * Medium (33.56%)
 * Total Accepted:    178.9K
 * Total Submissions: 525.9K
 * Testcase Example:  '[1,2,3,4,5]\n2\n4'
 *
 * Reverse a linked list from position m to n. Do it in one-pass.
 * 
 * Note: 1 ≤ m ≤ n ≤ length of list.
 * 
 * Example:
 * 
 * 
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        if (m != 1) {
            head.next = reverseBetween(head.next, m - 1, n - 1);
            return head;
        } else {
            if (n == 1) {
                return head;
            } else {
                ListNode res = reverseBetween(head.next, m, n - 1);
                ListNode temp = head.next.next;
                head.next.next = head;
                head.next = temp;
                return res;
            }
        }
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode iter = dummy;
        int count = m - 1;
        while (count > 0) {
            iter = iter.next;
            count--;
        }
        ListNode saveHead = iter;
        ListNode prev = saveHead.next;
        ListNode saveTail = saveHead.next;
        iter = prev.next;
        count = n - m;
        while (count > 0) {
            ListNode temp = iter.next;
            iter.next = prev;
            prev = iter;
            iter = temp;
            count--;
        }
        saveHead.next = prev;
        saveTail.next = iter;
        return dummy.next;
    }
}
