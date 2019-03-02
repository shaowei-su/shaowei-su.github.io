/*
 * @lc app=leetcode id=143 lang=java
 *
 * [143] Reorder List
 *
 * https://leetcode.com/problems/reorder-list/description/
 *
 * algorithms
 * Medium (29.28%)
 * Total Accepted:    142.2K
 * Total Submissions: 478.6K
 * Testcase Example:  '[1,2,3,4]'
 *
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * You may not modify the values in the list's nodes, only nodes itself may be
 * changed.
 * 
 * Example 1:
 * 
 * 
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * 
 * Example 2:
 * 
 * 
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return ;
        }
        ListNode midPrev = findMidPrev(head);
        ListNode mid = midPrev.next;
        midPrev.next = null;
        ListNode rev = reverse(mid);
        merge(head, rev);
    }

    public void merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        while (a != null || b != null) {
            if (a != null) {
                dummy.next = a;
                a = a.next;
                dummy = dummy.next;
            }
            if (b != null) {
                dummy.next = b;
                b = b.next;
                dummy = dummy.next;
            }
        }
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }


    public ListNode findMidPrev(ListNode head) {
        // 1 - 2 - 3 -4 --> return 2
        // 1 - 2 - 3 - 4 - 5 --> return 3 
        //
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;

    }
}
