---
layout: post
title: LintCode List Remove Nth Node From End of List
---
###Task1
Given a linked list, remove the nth node from the end of list and return its head.

Have you met this question in a real interview? Yes
Example
Given linked list: 1->2->3->4->5->null, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5->null.
Note
The minimum number of nodes in list is n.

Challenge
O(n) time

###java
```java
/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @param n: An integer.
     * @return: The head of linked list.
     */
    ListNode removeNthFromEnd(ListNode head, int n) {
        // write your code here
        if (head == null || n < 1) {
            return null;
        }
        
        ListNode fast = head.next;
        while (n > 0 && fast != null) {
            fast = fast.next;
            n--;
        }
        if (fast == null) {
            return head.next;
        }
        ListNode itr = head;
        while (fast != null) {
            itr = itr.next;
            fast = fast.next;
        }
        itr.next = itr.next.next;
        return head;
    }
}


```

###Points
* The case when to delete the first node: fast will be null!

