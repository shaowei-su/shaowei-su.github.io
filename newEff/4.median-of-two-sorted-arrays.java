/*
 * [4] Median of Two Sorted Arrays
 *
 * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 *
 * algorithms
 * Hard (24.13%)
 * Total Accepted:    322.4K
 * Total Submissions: 1.3M
 * Testcase Example:  '[1,3]\n[2]'
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * 
 * Find the median of the two sorted arrays. The overall run time complexity
 * should be O(log (m+n)).
 * 
 * You may assume nums1 and nums2 cannot be both empty.
 * 
 * Example 1:
 * 
 * 
 * nums1 = [1, 3]
 * nums2 = [2]
 * 
 * The median is 2.0
 * 
 * 
 * Example 2:
 * 
 * 
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 
 * The median is (2 + 3)/2 = 2.5
 * 
 * 
 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len1 = nums1.length;
        int len2 = nums2.length;
        if ((len1 + len2) % 2 == 1) {
            return findMid(nums1, nums2, 0, len1 - 1, 0, len2 - 1, (len1 + len2) / 2 + 1);
        } else {
            return (findMid(nums1, nums2, 0, len1 - 1, 0, len2 - 1, (len1 + len2) / 2) + findMid(nums1, nums2, 0, len1 - 1, 0, len2 - 1, (len1 + len2) / 2 + 1)) / 2;
        }
    }

    public double findMid(int[] nums1, int[] nums2, int start1, int end1, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        if (len1 == 0) {
            return nums2[start2 + k - 1];
        }
        if (len2 == 0) {
            return nums1[start1 + k - 1];
        }

        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;
        if (nums1[i] < nums2[j]) {
            return findMid(nums1, nums2, i + 1, end1, start2, end2, k - (i - start1 + 1));
        } else {
            return findMid(nums1, nums2, start1, end1, j + 1, end2, k - (j - start2 + 1));
        }
    }





    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(nums1, nums2, 0, m - 1, 0, n - 1, left) + findKth(nums1, nums2, 0, m - 1, 0, n - 1, right)) * 0.5;
    }
    public double findKth(int[] nums1, int[] nums2, int start1, int end1, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        if (len1 == 0) return nums2[start2 + k - 1];
        if (len2 == 0) return nums1[start1 + k - 1];
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;
        if (nums1[i] < nums2[j]) {
            return findKth(nums1, nums2, i + 1, end1, start2, end2, k - (i - start1 + 1));
        } else {
            return findKth(nums1, nums2, start1, end1, j + 1, end2, k - (j - start2 + 1));
        }
    }
}
