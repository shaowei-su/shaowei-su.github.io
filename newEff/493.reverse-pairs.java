/*
 * [493] Reverse Pairs
 *
 * https://leetcode.com/problems/reverse-pairs/description/
 *
 * algorithms
 * Hard (21.63%)
 * Total Accepted:    18.4K
 * Total Submissions: 84.9K
 * Testcase Example:  '[1,3,2,3,1]'
 *
 * Given an array nums, we call (i, j) an important reverse pair if i < j and
 * nums[i] > 2*nums[j].
 * 
 * You need to return the number of important reverse pairs in the given
 * array.
 * 
 * Example1:
 * 
 * Input: [1,3,2,3,1]
 * Output: 2
 * 
 * 
 * Example2:
 * 
 * Input: [2,4,3,5,1]
 * Output: 3
 * 
 * 
 * Note:
 * 
 * The length of the given array will not exceed 50,000.
 * All the numbers in the input array are in the range of 32-bit integer.
 * 
 * 
 */
class Solution {
    public int reversePairs(int[] nums) {
        return bsearch(nums, 0, nums.length - 1);
    }

    public int bsearch(int[] nums, int l, int r) {
        if (l >= r) {
            return 0;
        }
        int mid = l + (r - l) / 2;
        int res = bsearch(nums, l, mid) + bsearch(nums, mid + 1, r);
        int i = l, j = mid + 1, p = mid + 1, k = 0;
        int[] merge = new int[r - l + 1];
        while (i <= mid) {
            while (p <= r && nums[i] > 2L * nums[p]) {
                p++;
            }
            res += p - (mid + 1);
            while (j <= r && nums[i] >= nums[j]) {
                merge[k++] = nums[j++];
            }
            merge[k++] = nums[i++];
        }
        while (j <= r) {
            merge[k++] = nums[j++];
        }
        System.arraycopy(merge, 0, nums, l, merge.length);
        return res;
    }

    public int reversePairs2(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((long)nums[i] > 2 * (long)nums[j]) {
                    res++;
                }
            }
        }
        return res;
    }
}
