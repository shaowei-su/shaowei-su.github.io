/*
 * [31] Next Permutation
 *
 * https://leetcode.com/problems/next-permutation/description/
 *
 * algorithms
 * Medium (29.33%)
 * Total Accepted:    183.4K
 * Total Submissions: 625.4K
 * Testcase Example:  '[1,2,3]'
 *
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place and use only constant extra memory.
 * 
 * Here are some examples. Inputs are in the left-hand column and its
 * corresponding outputs are in the right-hand column.
 * 
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * 
 */
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return ;
        }
        int lastInc = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                lastInc = i;
            }
        }
        if (lastInc == -1) {
            Arrays.sort(nums);
            return;
        }
        int smallestAfter = nums[lastInc + 1], smallestAfterInd = lastInc + 1;
        for (int i = smallestAfterInd + 1; i < nums.length; i++) {
            if (nums[i] <= smallestAfter && nums[i] > nums[lastInc]) {
                smallestAfter = nums[i];
                smallestAfterInd = i;
            }
        }
        swap(nums, lastInc, smallestAfterInd);
        reverse(nums, lastInc + 1, nums.length - 1);
        return;





    }
    public void nextPermutation2(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int firstDec = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                firstDec = i - 1;
                break;
            }
        }
        if (firstDec == -1) {
            Arrays.sort(nums);
            return;
        }
        int smallestAfterDec = nums[firstDec + 1];
        int smallestInd = firstDec + 1;
        for (int i = firstDec + 1; i < nums.length; i++) {
            if (nums[i] <= smallestAfterDec && nums[i] > nums[firstDec]) {
                smallestAfterDec = nums[i];
                smallestInd = i;
            }
        }
        swap(nums, firstDec, smallestInd);
        reverse(nums, firstDec + 1, nums.length - 1);
        return;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
