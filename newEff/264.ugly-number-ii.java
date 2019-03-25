import java.util.*;

/*
 * @lc app=leetcode id=264 lang=java
 *
 * [264] Ugly Number II
 *
 * https://leetcode.com/problems/ugly-number-ii/description/
 *
 * algorithms
 * Medium (35.13%)
 * Total Accepted:    98.3K
 * Total Submissions: 275.4K
 * Testcase Example:  '10'
 *
 * Write a program to find the n-th ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3,
 * 5. 
 * 
 * Example:
 * 
 * 
 * Input: n = 10
 * Output: 12
 * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10
 * ugly numbers.
 * 
 * Note:  
 * 
 * 
 * 1 is typically treated as an ugly number.
 * n does not exceed 1690.
 * 
 */
class Solution {

    Map<Integer, Boolean> uglyCache = new HashMap<>();

    public boolean isUgly(int num) {
        if (num < 1) {
            return false;
        }
        if (num == 1) {
            return true;
        }
        if (uglyCache.containsKey(num)) {
            return uglyCache.get(num);
        }
        boolean by2 = false;
        boolean by3 = false;
        boolean by5 = false;
        if (num % 2 == 0) {
            by2 = isUgly(num / 2);
        }
        if (num % 3 == 0) {
            by3 = isUgly(num / 3);
        }
        if (num % 5 == 0) {
            by5 = isUgly(num / 5);
        }
        uglyCache.put(num, by2 || by3 || by5);
        return by2 || by3 || by5;
    }


    public int nthUglyNumber2(int n) {
        int count = 0;
        int ind = 1;
        while (count < n) {
            if (isUgly(ind++)) {
                count++;
            }
        }
        return ind - 1;
    }
    public int nthUglyNumber(int n) {
        if (n < 1) {
            return 0;
        }
        int[] cand = new int[] {2, 3, 5};
        List<Integer> res = new ArrayList<>();
        res.add(1);
        int i2 = 0, i3 = 0, i5 = 0;
        int next2 = 2;
        int next3 = 3;
        int next5 = 5;
        while (res.size() < n) {
            int next = Math.min(next2, Math.min(next3, next5));
            res.add(next);
            if (next == next2) {
                i2++;
                next2 = res.get(i2) * 2;
            }
            if (next == next3) {
                i3++;
                next3 = res.get(i3) * 3;
            }
            if (next == next5) {
                i5++;
                next5 = res.get(i5) * 5;
            }
        }
        return res.get(n - 1);
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.nthUglyNumber(10);
    }
}
