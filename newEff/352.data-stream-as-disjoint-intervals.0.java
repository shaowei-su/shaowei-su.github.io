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
    TreeMap<Integer, Interval> indMap;
    /** Initialize your data structure here. */
    public SummaryRanges() {
        indMap = new TreeMap<>();
    }
    
    public void addNum(int val) {
        if (indMap.containsKey(val)) {
            return;
        }
        Integer l = indMap.lowerKey(val);
        Integer h = indMap.higherKey(val);
        if (l != null && h != null && indMap.get(l).end + 1 == val && indMap.get(h).start == val + 1) {
            indMap.put(val, new Interval(indMap.get(l).start, indMap.get(h).end));
            indMap.remove(l);
            indMap.remove(h);
        } else if (l != null && indMap.get(l).end + 1 == val) {
            indMap.put(val, new Interval(indMap.get(l).start, val));
            indMap.remove(l);
        } else if (h != null && indMap.get(h).start == val + 1) {
            indMap.put(val, new Interval(val, indMap.get(h).end));
            indMap.remove(h);
        } else {
            indMap.put(val, new Interval(val, val));
        }
    }
    
    public List<Interval> getIntervals() {
        return new ArrayList<Interval>(indMap.values());
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */
