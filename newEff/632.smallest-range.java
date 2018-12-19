/*
 * [632] Smallest Range
 *
 * https://leetcode.com/problems/smallest-range/description/
 *
 * algorithms
 * Hard (44.84%)
 * Total Accepted:    17.2K
 * Total Submissions: 38.2K
 * Testcase Example:  '[[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]'
 *
 * You have k lists of sorted integers in ascending order. Find the smallest
 * range that includes at least one number from each of the k lists. 
 * 
 * We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c
 * if b-a == d-c.
 * 
 * Example 1:
 * 
 * Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * Output: [20,24]
 * Explanation: 
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 * 
 * 
 * 
 * 
 * Note:
 * 
 * The given list may contain duplicates, so ascending order means >= here.
 * 1 k 
 * ⁠-105 value of elements 5.
 * For Java users, please note that the input type has been changed to
 * List<List<Integer>>. And after you reset the code template, you'll see this
 * point.
 * 
 * 
 * 
 */
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int[] res = new int[2];
        if (nums == null || nums.size() == 0) {
            return res;
        }
        PriorityQueue<Element> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));
        int maxVal = 0;
        int start = -1, end = -1;
        int range = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.size(); i++) {
            pq.offer(new Element(i, 0, nums.get(i).get(0)));
            maxVal = Math.max(maxVal, nums.get(i).get(0));
        }
        while (pq.size() == nums.size()) {
            Element cur = pq.poll();
            if (maxVal - cur.val < range) {
                range = maxVal - cur.val;
                start = cur.val;
                end = maxVal;
            }
            if (cur.ind + 1 < nums.get(cur.row).size()) {
                cur.ind = cur.ind + 1;
                cur.val = nums.get(cur.row).get(cur.ind);
                pq.offer(cur);
                if (cur.val > maxVal) {
                    maxVal = cur.val;
                }
            }
        }
        res[0] = start;
        res[1] = end;
        return res;
    }
    class Element {
        int row;
        int ind;
        int val;
        public Element(int row, int ind, int val) {
            this.row = row;
            this.ind = ind;
            this.val = val;
        }
    }
}
