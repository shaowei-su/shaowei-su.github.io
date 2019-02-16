import java.util.*;
/*
 * @lc app=leetcode id=281 lang=java
 *
 * [281] Zigzag Iterator
 *
 * https://leetcode.com/problems/zigzag-iterator/description/
 *
 * algorithms
 * Medium (55.06%)
 * Total Accepted:    49.5K
 * Total Submissions: 89.5K
 * Testcase Example:  '[1,2]\n[3,4,5,6]'
 *
 * Given two 1d vectors, implement an iterator to return their elements
 * alternately.
 * 
 * Example:
 * 
 * 
 * Input:
 * v1 = [1,2]
 * v2 = [3,4,5,6] 
 * 
 * Output: [1,3,2,4,5,6]
 * 
 * Explanation: By calling next repeatedly until hasNext returns
 * false, 
 * the order of elements returned by next should be: [1,3,2,4,5,6].
 * 
 * Follow up: What if you are given k 1d vectors? How well can your code be
 * extended to such cases?
 * 
 * Clarification for the follow up question:
 * The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases.
 * If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For
 * example:
 * 
 * 
 * Input:
 * [1,2,3]
 * [4,5,6,7]
 * [8,9]
 * 
 * Output: [1,4,8,2,5,9,3,6,7].
 * 
 * 
 */
public class ZigzagIterator {
    List<Integer> l1, l2;
    int i1 = 0, i2 = 0;
    boolean isFirst;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
       l1 = v1;
       l2 = v2;
       isFirst = true;
    }

    public int next() {
       if ((i1 < l1.size()) && (i2 < l2.size())) {
           if (isFirst) {
               isFirst = !isFirst;
               return l1.get(i1++);
           } else {
               isFirst = !isFirst;
               return l2.get(i2++);
           }
       } else if (i1 < l1.size()) {
           return l1.get(i1++);
       } else if (i2 < l2.size()) {
           return l2.get(i2++);
       }
       return 0;
    }

    public boolean hasNext() {
        return (i1 < l1.size()) || (i2 < l2.size());
    }

    public static void main(String[] args) {
        ZigzagIterator zi = new ZigzagIterator(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6, 7));
        while (zi.hasNext()) {
            System.out.println("hehe : " + zi.next());
        }
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
