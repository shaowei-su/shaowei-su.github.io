/*
 * [496] Next Greater Element I
 *
 * https://leetcode.com/problems/next-greater-element-i/description/
 *
 * algorithms
 * Easy (57.63%)
 * Total Accepted:    75.8K
 * Total Submissions: 131.5K
 * Testcase Example:  '[4,1,2]\n[1,3,4,2]'
 *
 * 
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s
 * elements are subset of nums2. Find all the next greater numbers for nums1's
 * elements in the corresponding places of nums2. 
 * 
 * 
 * 
 * The Next Greater Number of a number x in nums1 is the first greater number
 * to its right in nums2. If it does not exist, output -1 for this number.
 * 
 * 
 * Example 1:
 * 
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * Output: [-1,3,-1]
 * Explanation:
 * ⁠   For number 4 in the first array, you cannot find the next greater number
 * for it in the second array, so output -1.
 * ⁠   For number 1 in the first array, the next greater number for it in the
 * second array is 3.
 * ⁠   For number 2 in the first array, there is no next greater number for it
 * in the second array, so output -1.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: nums1 = [2,4], nums2 = [1,2,3,4].
 * Output: [3,-1]
 * Explanation:
 * ⁠   For number 2 in the first array, the next greater number for it in the
 * second array is 3.
 * ⁠   For number 4 in the first array, there is no next greater number for it
 * in the second array, so output -1.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * All elements in nums1 and nums2 are unique.
 * The length of both nums1 and nums2 would not exceed 1000.
 * 
 * 
 */
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new LinkedList<>();
        Map<Integer, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            if (stack.size() == 0) {
                stack.push(nums2[i]);
            } else {
                while (stack.size() > 0 && stack.peek() < nums2[i]) {
                    targetMap.put(stack.pop(), nums2[i]);
                }
                stack.push(nums2[i]);
            }
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            if (targetMap.containsKey(nums1[i])) {
                res[i] = targetMap.get(nums1[i]);
            } else {
                res[i] = -1;
            }
        }
        return res;
    }
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] res = new int[len1];
        for (int i = 0; i < len1; i++) {
            res[i] = -1;
            int target = -1;
            for (int j = 0; j < len2; j++) {
                if (nums2[j] == nums1[i]) {
                    target = j;
                    break;
                }
            }
            if (target == len2 - 1) {
                continue;
            }
            for (int j = target + 1; j < len2; j++) {
                if (nums2[j] > nums1[i]) {
                    res[i] = nums2[j];
                    break;
                }
            }
        }
        return res;
    }
}
