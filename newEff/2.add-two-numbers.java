/*
 * [2] Add Two Numbers
 *
 * https://leetcode.com/problems/add-two-numbers/description/
 *
 * algorithms
 * Medium (29.31%)
 * Total Accepted:    632.4K
 * Total Submissions: 2.2M
 * Testcase Example:  '[2,4,3]\n[5,6,4]'
 *
 * You are given two non-empty linked lists representing two non-negative
 * integers. The digits are stored in reverse order and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * 
 * Example:
 * 
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
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
        ListNode r1 = l1;
        ListNode r2 = l2;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (r1 != null && r2 != null) {
            int res = r1.val + r2.val + carry;
            ListNode add = new ListNode(res % 10);
            carry = res / 10;
            cur.next = add;
            cur = cur.next;
            r1 = r1.next;
            r2 = r2.next;
        }
        while (r1 != null) {
            int res = r1.val + carry;
            ListNode add = new ListNode(res % 10);
            carry = res / 10;
            cur.next = add;
            cur = cur.next;
            r1 = r1.next;
        }
         while (r2 != null) {
             int res = r2.val + carry;
             ListNode add = new ListNode(res % 10);
             carry = res / 10;
             cur.next = add;
             cur = cur.next;
             r2 = r2.next;
         }
         if (carry != 0) {
             ListNode add = new ListNode(carry);
             cur.next = add;
         }
         return dummy.next;
    }
    public ListNode reverse(ListNode list) {
        ListNode next = list.next;
        list.next = null;
        while (next != null) {
            ListNode temp = next.next;
            next.next = list;
            list = next;
            next = temp;
        }
        return list;
    }
}
