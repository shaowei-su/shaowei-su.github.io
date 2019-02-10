/*
 * @lc app=leetcode id=295 lang=java
 *
 * [295] Find Median from Data Stream
 *
 * https://leetcode.com/problems/find-median-from-data-stream/description/
 *
 * algorithms
 * Hard (33.80%)
 * Total Accepted:    90K
 * Total Submissions: 262K
 * Testcase Example:  '["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]\n[[],[1],[2],[],[3],[]]'
 *
 * Median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value. So the median is the mean of the two
 * middle value.
 * For example,
 * 
 * [2,3,4], the median is 3
 * 
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Design a data structure that supports the following two operations:
 * 
 * 
 * void addNum(int num) - Add a integer number from the data stream to the data
 * structure.
 * double findMedian() - Return the median of all elements so far.
 * 
 * 
 * 
 * 
 * Example:
 * 
 * 
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3) 
 * findMedian() -> 2
 * 
 * 
 * 
 * 
 * Follow up:
 * 
 * 
 * If all integer numbers from the stream are between 0 and 100, how would you
 * optimize it?
 * If 99% of all integer numbers from the stream are between 0 and 100, how
 * would you optimize it?
 * 
 * 
 */
class MedianFinder {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;
    /** initialize your data structure here. */
    public MedianFinder() {
        left = new PriorityQueue<Integer>((a, b) -> b - a);
        right = new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
        left.offer(num);
        right.offer(left.poll());
        if (right.size() > left.size()) {
            left.offer(right.poll());
        }
    }

    public void addNum2(int num) {
        if (left.size() == 0 && right.size() == 0) {
            left.offer(num);
        } else if (left.size() == 0) {
            Integer rightP = right.peek();
            if (num <= rightP) {
                left.offer(num);
            } else {
                left.offer(right.poll());
                right.offer(num);
            }
        } else if (right.size() == 0) {
            Integer leftP = left.peek();
            if (num > leftP) {
                right.offer(num);
            } else {
                right.offer(left.peek());
                left.offer(num);
            }
        } else {
            Integer leftP = left.peek();
            Integer rightP = right.peek();
            if (num <= leftP) {
                left.offer(num);
            } else {
                right.offer(num);
            }
        }
        rebalance();
    }

    public void rebalance() {
        while (left.size() - right.size() > 1) {
            right.offer(left.poll());
        }
        while (right.size() - left.size() > 1) {
            left.offer(right.poll());
        }
    }
    
    public double findMedian() {
        if (left.size() == right.size()) {
            return (left.peek() + right.peek()) / 2.0d;
        } else if (left.size() > right.size()) {
            return left.peek() * 1.0d;
        } else {
            return right.peek() * 1.0d;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
