/*
 * [442] Find All Duplicates in an Array
 *
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/description/
 *
 * algorithms
 * Medium (58.94%)
 * Total Accepted:    80.6K
 * Total Submissions: 136.7K
 * Testcase Example:  '[4,3,2,7,8,2,3,1]'
 *
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements
 * appear twice and others appear once.
 * 
 * Find all the elements that appear twice in this array.
 * 
 * Could you do it without extra space and in O(n) runtime?
 * 
 * Example:
 * 
 * Input:
 * [4,3,2,7,8,2,3,1]
 * 
 * Output:
 * [2,3]
 * 
 */
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int ind = Math.abs(nums[i]) - 1;
            if (nums[ind] < 0) {
                res.add(ind + 1);
            } else {
                nums[ind] = - nums[ind];
            }
        }
        return res;
    }
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int ind = 0;
        while (ind < nums.length) {
            if (nums[ind] - 1 == ind) {
                ind++;
            } else {
                int cur = ind;
                while (nums[cur] - 1 != cur) {
                    if (nums[cur] == nums[nums[cur] - 1]) break;
                    swap(nums, cur, nums[cur] - 1);
                }
                ind++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i) {
                res.add(nums[i]);
            }
        }
        return res;
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
