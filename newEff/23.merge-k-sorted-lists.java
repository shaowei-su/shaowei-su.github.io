/*
 * [23] Merge k Sorted Lists
 *
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 *
 * algorithms
 * Hard (30.73%)
 * Total Accepted:    284K
 * Total Submissions: 923.7K
 * Testcase Example:  '[[1,4,5],[1,3,4],[2,6]]'
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 * 
 * Example:
 * 
 * 
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
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
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((ListNode a, ListNode b) -> a.val - b.val);
        for (ListNode ln : lists) {
            if (ln != null) pq.offer(ln);
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (pq.size() > 0) {
            ListNode smallest = pq.poll();
            cur.next = smallest;
            cur = cur.next;
            if (smallest.next != null) pq.offer(smallest.next);
        }
       return dummy.next; 
    }
}
