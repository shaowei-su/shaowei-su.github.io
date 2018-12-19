/*
 * [445] Add Two Numbers II
 *
 * https://leetcode.com/problems/add-two-numbers-ii/description/
 *
 * algorithms
 * Medium (48.25%)
 * Total Accepted:    71.7K
 * Total Submissions: 148.5K
 * Testcase Example:  '[7,2,4,3]\n[5,6,4]'
 *
 * You are given two non-empty linked lists representing two non-negative
 * integers. The most significant digit comes first and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * 
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the
 * lists is not allowed.
 * 
 * 
 * 
 * Example:
 * 
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode r1 = reverse(l1);
        ListNode r2 = reverse(l2);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (r1 != null && r2 != null) {
            int sum = carry + r1.val + r2.val;
            ListNode temp = new ListNode(sum % 10);
            cur.next = temp;
            cur = cur.next;
            carry = sum / 10;
            r1 = r1.next;
            r2 = r2.next;
        }
        while (r1 != null) {
            int sum = carry + r1.val;
             ListNode temp = new ListNode(sum % 10);
             cur.next = temp;
             cur = cur.next;
             carry = sum / 10;
             r1 = r1.next;
        }
         while (r2 != null) {
             int sum = carry + r2.val;
              ListNode temp = new ListNode(sum % 10);
              cur.next = temp;
              cur = cur.next;
              carry = sum / 10;
              r2 = r2.next;
         }
         if (carry != 0) {
             ListNode temp = new ListNode(carry);
             cur.next = temp;
         }
         return reverse(dummy.next);
    }
    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode next = head;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;

    }
}
