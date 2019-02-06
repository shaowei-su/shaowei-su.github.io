/*
 * @lc app=leetcode id=360 lang=java
 *
 * [360] Sort Transformed Array
 *
 * https://leetcode.com/problems/sort-transformed-array/description/
 *
 * algorithms
 * Medium (46.20%)
 * Total Accepted:    24.3K
 * Total Submissions: 52.7K
 * Testcase Example:  '[-4,-2,2,4]\n1\n3\n5'
 *
 * Given a sorted array of integers nums and integer values a, b and c. Apply a
 * quadratic function of the form f(x) = ax^2 + bx + c to each element x in the
 * array.
 * 
 * The returned array must be in sorted order.
 * 
 * Expected time complexity: O(n)
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
 * Output: [3,9,15,33]
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
 * Output: [-23,-5,1,7]
 * 
 * 
 * 
 */
class Solution {

    public int compute(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        List<Integer> tmp = new ArrayList<>();
        int mid = a == 0 ? 0 : -(b / 2 / a);
        int visited = 0;
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num: nums) {
            numSet.add(num);
        }
        if (numSet.contains(mid)) {
            tmp.add(compute(mid, a, b, c));
        }
        boolean goLeft = true;
        int left = mid, right = mid;
        while (visited < nums.length) {
            if (goLeft) {
                left--;
                if (numSet.contains(left)) {
                    tmp.add(compute(left, a, b, c));
                    visited++;
                }
                goLeft = false;
            } else {
                right++;
                if (numSet.contains(right)) {
                    tmp.add(compute(right, a, b, c));
                    visited++;
                }
                goLeft = true;
            }
        }
        if (a < 0) {
            Collections.reverse(tmp);
        }
        for (int i = 0; i < res.length; i++) {
            res[i] = tmp.get(i);
        }
        return res;
    }

    public int[] sortTransformedArray2(int[] nums, int a, int b, int c) {
        double mid = a == 0 ? 0.0d :  -((double)b / 2 / (double)a);
        Map<Double, Integer> distMap = new HashMap<>();
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (int i : nums) {
            double dist = Math.abs((double)i - mid);
            min = Math.min(min, dist);
            max = Math.max(max, dist);
            distMap.put(dist, compute(i, a, b, c));
        }
        int length = (int)(max - min);
        int[] buckets = new int[length + 1];
        int[] res = new int[nums.length];
        for (Double key : distMap.keySet()) {
            int dist = (int)(key - min);
            buckets[dist]++;
        }
        int resCount = a > 0 ? 0 : nums.length - 1;
        for (Double d = min; d <= max; d = d + 1.0d) {
            int dist = (int)(d - min);
            if (buckets[dist] > 0) {
                if (a > 0) {
                    res[resCount++] = distMap.get(d);
                } else {
                    res[resCount--] = distMap.get(d);
                }
            }
        }
        
        return res;
    }
}
