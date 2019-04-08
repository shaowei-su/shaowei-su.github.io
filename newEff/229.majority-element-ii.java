/*
 * @lc app=leetcode id=229 lang=java
 *
 * [229] Majority Element II
 *
 * https://leetcode.com/problems/majority-element-ii/description/
 *
 * algorithms
 * Medium (31.52%)
 * Total Accepted:    95.7K
 * Total Submissions: 303.3K
 * Testcase Example:  '[3,2,3]'
 *
 * Given an integer array of size n, find all elements that appear more than ⌊
 * n/3 ⌋ times.
 * 
 * Note: The algorithm should run in linear time and in O(1) space.
 * 
 * Example 1:
 * 
 * 
 * Input: [3,2,3]
 * Output: [3]
 * 
 * Example 2:
 * 
 * 
 * Input: [1,1,1,3,3,2,2,2]
 * Output: [1,2]
 * 
 */
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int numA = -1;
        int numB = -1;
        int countA = 0;
        int countB = 0;
        for (int num : nums) {
            if (numA == num) {
                countA++;
                continue;
            }
            if (numB == num) {
                countB++;
                continue;
            }
            if (countA == 0) {
                numA = num;
                countA++;
                continue;
            }
            if (countB == 0) {
                numB = num;
                countB++;
                continue;
            }
            countA--;
            countB--;
        }
        countA = 0;
        countB = 0;
        for (int num : nums) {
            if (num == numA) {
                countA++;
            } else if (num == numB) {
                countB++;
            }
        }
        List<Integer> res = new ArrayList<>();
        if (countA > nums.length / 3) {
            res.add(numA);
        }
        if (countB > nums.length / 3) {
            res.add(numB);
        }
        return res;
    }
}
