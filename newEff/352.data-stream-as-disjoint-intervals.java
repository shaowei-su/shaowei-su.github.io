/*
 * [352] Data Stream as Disjoint Intervals
 *
 * https://leetcode.com/problems/data-stream-as-disjoint-intervals/description/
 *
 * algorithms
 * Hard (41.54%)
 * Total Accepted:    18.9K
 * Total Submissions: 45.6K
 * Testcase Example:  '["SummaryRanges","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals"]\n[[],[1],[],[3],[],[7],[],[2],[],[6],[]]'
 *
 * Given a data stream input of non-negative integers a1, a2, ..., an, ...,
 * summarize the numbers seen so far as a list of disjoint intervals.
 * 
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6,
 * ..., then the summary will be:
 * 
 * 
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 * 
 * 
 * Follow up:
 * What if there are lots of merges and the number of disjoint intervals are
 * small compared to the data stream's size?
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
class SummaryRanges {
    Map<Integer, Interval> indMap;
    /** Initialize your data structure here. */
    public SummaryRanges() {
        indMap = new HashMap<Integer, Interval>();
    }
    
    public void addNum(int val) {
        if (indMap.containsKey(val)) {
            return;
        }
        if (indMap.containsKey(val - 1) && indMap.containsKey(val + 1)) {
            Interval i1 = indMap.get(val - 1);
            Interval i2 = indMap.get(val + 1);
            Interval i3 = new Interval(i1.start, i2.end);
            for (int i = i1.start; i <= i1.end; i++) {
                indMap.remove(i);
            }
            for (int i = i2.start; i <= i2.end; i++) {
                indMap.remove(i);
            }
            for (int i = i1.start; i <= i2.end; i++) {
                indMap.put(i, i3);
            }
        } else if (indMap.containsKey(val - 1)) {
            Interval i1 = indMap.get(val - 1);
            Interval i3 = new Interval(i1.start, val);
            for (int i = i1.start; i <= val; i++) {
                indMap.remove(i);
                indMap.put(i, i3);
            }
        } else if (indMap.containsKey(val + 1)) {
             Interval i2 = indMap.get(val + 1);
             Interval i3 = new Interval(val, i2.end);
             for (int i = val; i <= i2.end; i++) {
                 indMap.remove(i);
                 indMap.put(i, i3);
             }
        } else {
            Interval i3 = new Interval(val, val);
            indMap.put(val, i3);
        }
    }
    
    public List<Interval> getIntervals() {
        List<Interval> res = new ArrayList<>();
        Set<Interval> resSet = new HashSet<>();
        for (Integer i : indMap.keySet()) {
            Interval inter = indMap.get(i);
            if (resSet.contains(inter)) {
                continue;
            }
            resSet.add(inter);
            res.add(inter);
        }
        Collections.sort(res, (a, b) -> a.start - b.start);
        return res;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */
