/*
 * [41] First Missing Positive
 *
 * https://leetcode.com/problems/first-missing-positive/description/
 *
 * algorithms
 * Hard (26.92%)
 * Total Accepted:    162.7K
 * Total Submissions: 602.4K
 * Testcase Example:  '[1,2,0]'
 *
 * Given an unsorted integer array, find the smallest missing positive
 * integer.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,2,0]
 * Output: 3
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [3,4,-1,1]
 * Output: 2
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: [7,8,9,11,12]
 * Output: 1
 * 
 * 
 * Note:
 * 
 * Your algorithm should run in O(n) time and uses constant extra space.
 * 
 */
class Solution {

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int len = nums.length;
        int iter = 0;
        while (iter < len) {
            if (nums[iter] < 1 || nums[iter] > len || nums[iter] == iter + 1 || nums[nums[iter] - 1] == nums[iter]) {
                iter++;
            } else {
                int pos = nums[iter] - 1;
                int tmp = nums[pos];
                nums[pos] = nums[iter];
                nums[iter] = tmp;
            }
        }
        for (int i = 1; i <= len; i++) {
            if (nums[i - 1] != i) {
                return i;
            }
        }

        return len + 1;





    }

    public int firstMissingPositive3(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums.length || nums[i] < 1) {
                continue;
            }
            int newPos = nums[i] - 1;
            if (nums[i] != nums[newPos]) {
                swap(nums, i, nums[i] - 1);
                i -= 1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != (i + 1)) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int firstMissingPositive2(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        for (int i = 1; i < nums.length + 1; i++) {
            if (!set.contains(i)) {
                return i;
                }
        }
        return nums.length + 1;
    }
}
