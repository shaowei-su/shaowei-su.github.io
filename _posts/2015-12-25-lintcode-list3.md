---
layout: post
title: LintCode List Rotate List
---
###Task1
Given a list, rotate the list to the right by k places, where k is non-negative.

Have you met this question in a real interview? Yes
Example
Given 1->2->3->4->5 and k = 2, return 4->5->1->2->3.

###java
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param head: the List
     * @param k: rotate to the right k places
     * @return: the list after rotation
     */
    public ListNode rotateRight(ListNode head, int k) {
        // write your code here
        if (head == null || head.next == null) {
            return head;
        }
        int len = 0;
        ListNode itr = head;
        while (itr != null) {
            itr = itr.next;
            len++;
        }
        k = k % len;
        ListNode pre = findKth(head, len - k - 1);
        ListNode newHead = pre.next;
        pre.next = null;
        itr = newHead;
        while (itr.next != null) {
            itr = itr.next;
        }
        itr.next = head;
        return newHead;
    }
    
    public ListNode findKth(ListNode head, int k) {
        while (k > 0) {
            head = head.next;
            k--;
        }
        return head;
    }
}

```

###Points
* find length --> modulo
* find kth then disconnect

