/*
 * @lc app=leetcode id=346 lang=java
 *
 * [346] Moving Average from Data Stream
 *
 * https://leetcode.com/problems/moving-average-from-data-stream/description/
 *
 * algorithms
 * Easy (64.26%)
 * Total Accepted:    64.1K
 * Total Submissions: 99.7K
 * Testcase Example:  '["MovingAverage","next","next","next","next"]\n[[3],[1],[10],[3],[5]]'
 *
 * Given a stream of integers and a window size, calculate the moving average
 * of all integers in the sliding window.
 * 
 * Example:
 * 
 * 
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 * 
 * 
 * 
 * 
 */
class MovingAverage {
    double curAvg;
    Deque<Integer> queue;
    int size;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        queue = new LinkedList<>();
    }
    
    public double next(int val) {
        if (queue.size() < this.size) {
            curAvg = (curAvg * queue.size() + val) / (queue.size() + 1);
            queue.offer(val);
        } else {
            Integer top = queue.poll();
            curAvg = (curAvg * this.size + val - top) / this.size;
            queue.offer(val);
        }
        return curAvg;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
