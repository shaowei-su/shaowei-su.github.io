/*
 * @lc app=leetcode id=179 lang=java
 *
 * [179] Largest Number
 *
 * https://leetcode.com/problems/largest-number/description/
 *
 * algorithms
 * Medium (24.94%)
 * Total Accepted:    119.7K
 * Total Submissions: 475.4K
 * Testcase Example:  '[10,2]'
 *
 * Given a list of non negative integers, arrange them such that they form the
 * largest number.
 * 
 * Example 1:
 * 
 * 
 * Input: [10,2]
 * Output: "210"
 * 
 * Example 2:
 * 
 * 
 * Input: [3,30,34,5,9]
 * Output: "9534330"
 * 
 * 
 * Note: The result may be very large, so you need to return a string instead
 * of an integer.
 * 
 */
class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        /**
        PriorityQueue<String> pq = new PriorityQueue<String>((a, b) -> {
            if (a.length() > b.length() && a.startsWith(b)) {
                if (a.charAt(b.length()) > b.charAt(0)) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (b.length() > a.length() && b.startsWith(a)) {
                if (b.charAt(a.length()) > a.charAt(0)) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return b.compareTo(a);
            }
        });
        */
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> (b + a).compareTo(a + b));
        StringBuilder sb = new StringBuilder();
        for (int  i : nums) {
            pq.offer(String.valueOf(i));
        }
        if (pq.peek().equals("0")) {
            return "0";
        }
        while (pq.size() > 0) {
            sb.append(pq.poll());
        }
        return sb.toString();
    }
}
