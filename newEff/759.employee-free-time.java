/*
 * @lc app=leetcode id=759 lang=java
 *
 * [759] Employee Free Time
 *
 * https://leetcode.com/problems/employee-free-time/description/
 *
 * algorithms
 * Hard (59.33%)
 * Total Accepted:    12.3K
 * Total Submissions: 20.3K
 * Testcase Example:  '[[[1,2],[5,6]],[[1,3]],[[4,10]]]'
 *
 * 
 * We are given a list schedule of employees, which represents the working time
 * for each employee.
 * 
 * Each employee has a list of non-overlapping Intervals, and these intervals
 * are in sorted order.
 * 
 * Return the list of finite intervals representing common, positive-length
 * free time for all employees, also in sorted order.
 * 
 * 
 * Example 1:
 * 
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation:
 * There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 * 
 * 
 * Example 2:
 * 
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 * 
 * 
 * 
 * 
 * (Even though we are representing Intervals in the form [x, y], the objects
 * inside are Intervals, not lists or arrays.  For example,
 * schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is
 * not defined.)
 * 
 * Also, we wouldn't include intervals like [5, 5] in our answer, as they have
 * zero length.
 * 
 * 
 * Note:
 * schedule and schedule[i] are lists with lengths in range [1, 50].
 * 0 .
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
    class IndexedInterval {
        int index;
        List<Interval> schedule;
        public IndexedInterval(List<Interval> sd) {
            this.schedule = sd;
            index = 0;
        }
        public boolean hasItem() {
            return index < schedule.size();
        }
        public Interval next() {
            return schedule.get(index++);
        }
        public Interval peek() {
            return schedule.get(index);
        }
    }
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> mergeRes = new ArrayList<>();

        PriorityQueue<IndexedInterval> pq = new PriorityQueue<>((a, b) -> {
            if (a.peek().start == b.peek().start) {
                return a.peek().end - b.peek().end;
            } else {
                return a.peek().start - b.peek().start;
            }
        });

        for (List<Interval> li : schedule) {
            IndexedInterval ii = new IndexedInterval(li);
            pq.offer(ii);
        }

        while (!pq.isEmpty()) {
            IndexedInterval top = pq.poll();
            Interval topInterval = top.next();
            if (top.hasItem()) {
                pq.offer(top);
            }
            while (!pq.isEmpty() && isOverlap(topInterval, pq.peek().peek())) {
                IndexedInterval tmp = pq.poll();
                merge(topInterval, tmp.next());
                if (tmp.hasItem()) {
                    pq.offer(tmp);
                }
            }
            mergeRes.add(topInterval);
        }

        return diff(mergeRes);
    }


    public boolean isOverlap(Interval a, Interval b) {
        return a.end >= b.start;
    }

    public void merge(Interval a, Interval b) {
        a.start = Math.min(a.start, b.start);
        a.end = Math.max(a.end, b.end);
    }

    public List<Interval> diff(List<Interval> inputs) {
        List<Interval> res = new ArrayList<>();
        if (inputs.size() < 2) {
            return inputs;
        }
        for (int i = 1; i < inputs.size(); i++) {
            Interval itv = new Interval(inputs.get(i - 1).end, inputs.get(i).start);
            res.add(itv);
        }

        return res;

    }
}
