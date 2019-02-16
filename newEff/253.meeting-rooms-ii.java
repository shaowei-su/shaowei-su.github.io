/*
 * @lc app=leetcode id=253 lang=java
 *
 * [253] Meeting Rooms II
 *
 * https://leetcode.com/problems/meeting-rooms-ii/description/
 *
 * algorithms
 * Medium (41.56%)
 * Total Accepted:    124K
 * Total Submissions: 295.6K
 * Testcase Example:  '[[0,30],[5,10],[15,20]]'
 *
 * Given an array of meeting time intervals consisting of start and end times
 * [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms
 * required.
 * 
 * Example 1:
 * 
 * 
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 * 
 * Example 2:
 * 
 * 
 * Input: [[7,10],[2,4]]
 * Output: 1
 * 
 */
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> (a.start - b.start));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Interval inter : intervals) {
            if (pq.size() > 0 && inter.start >= pq.peek()) {
                pq.poll();
            }
            pq.offer(inter.end);
        }
        return pq.size();
    }
    public int minMeetingRooms2(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Interval inter : intervals) {
            min = Math.min(min, inter.start);
            max = Math.max(max, inter.end);
        }
        int maxCount = Integer.MIN_VALUE;
        for (int i = min; i <= max; i++) {
            int cur = 0;
            for (Interval inter : intervals) {
                if (inter.start <= i && i < inter.end) {
                    cur++;
                }
            }
            maxCount = Math.max(maxCount, cur);
        }
        return maxCount;
    }
}
