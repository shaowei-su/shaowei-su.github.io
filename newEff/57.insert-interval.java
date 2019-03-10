/*
 * [57] Insert Interval
 *
 * https://leetcode.com/problems/insert-interval/description/
 *
 * algorithms
 * Hard (29.85%)
 * Total Accepted:    147.4K
 * Total Submissions: 492.4K
 * Testcase Example:  '[[1,3],[6,9]]\n[2,5]'
 *
 * Given a set of non-overlapping intervals, insert a new interval into the
 * intervals (merge if necessary).
 * 
 * You may assume that the intervals were initially sorted according to their
 * start times.
 * 
 * Example 1:
 * 
 * 
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with
 * [3,5],[6,7],[8,10].
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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
         List<Interval> res = new ArrayList<>();
         if (intervals == null || intervals.size() == 0) {
             res.add(newInterval);
             return res;
         }
         Interval cur = newInterval;
         int pos = 0;
         int lastMerge = -1;
         while (pos < intervals.size()) {
             if (pos == 0 && newInterval.end < intervals.get(0).start) {
                 intervals.add(0, newInterval);
                 lastMerge = -1;
                 break;
             }
             if (pos == intervals.size() - 1 && newInterval.start > intervals.get(intervals.size() - 1).end) {
                 intervals.add(newInterval);
                 lastMerge = -1;
                 break;
             }
             if (pos > 0 && intervals.get(pos - 1).end < newInterval.start && intervals.get(pos).start > newInterval.end) {
                 intervals.add(pos, newInterval);
                 lastMerge = -1;
                 break;
             }
             if (isOverlap(intervals.get(pos), newInterval)) {
                 newInterval = merge(intervals.get(pos), newInterval);
                 intervals.remove(pos);
                 lastMerge = pos;
             } else {
                 pos++;
             }
         }
         if (lastMerge != -1) {
             intervals.add(lastMerge, newInterval);
         }
         return intervals;




    }
    public List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            res.add(newInterval);
            return res;
        }
        boolean overlap = false;
        for (int i = 0; i < intervals.size(); i++) {
            if (isOverlap(intervals.get(i), newInterval)) {
                newInterval = merge(intervals.get(i), newInterval);
                overlap = true;
                intervals.remove(i);
                break;
            }
            if (i == 0 && newInterval.end < intervals.get(0).start) {
                res.add(newInterval);
                res.addAll(intervals);
                intervals = res;
                break;
            }
            if (i == intervals.size() - 1 && newInterval.start > intervals.get(i).end) {
                res.addAll(intervals);
                res.add(newInterval);
                intervals = res;
                break;
            }
            if (i > 0 && intervals.get(i - 1).end < newInterval.start && intervals.get(i).start > newInterval.end) {
                res.addAll(intervals);
                res.add(i, newInterval);
                intervals = res;
                break;
            }
        }
        if (overlap) {
            return insert(intervals, newInterval);
        } else {
            return intervals;
        }

    }

    public boolean isOverlap(Interval i1, Interval i2) {
        if (i1.start <= i2.start && i1.end >= i2.end) return true;
        if (i2.start <= i1.start && i2.end >= i1.end) return true;
        if (i1.start <= i2.start && i1.end >= i2.start) return true;
        if (i1.start <= i2.end && i1.end >= i2.end) return true;
        return false;
    }
    public Interval merge(Interval i1, Interval i2) {
        return new Interval(Math.min(i1.start, i2.start), Math.max(i1.end, i2.end));
    }
}
