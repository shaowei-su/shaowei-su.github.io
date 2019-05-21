/*
 * [215] Kth Largest Element in an Array
 *
 * https://leetcode.com/problems/kth-largest-element-in-an-array/description/
 *
 * algorithms
 * Medium (42.93%)
 * Total Accepted:    257.7K
 * Total Submissions: 600K
 * Testcase Example:  '[3,2,1,5,6,4]\n2'
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth
 * largest element in the sorted order, not the kth distinct element.
 * 
 * Example 1:
 * 
 * 
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 * 
 */
class Solution {
    public int findKthLargest2(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return Integer.MAX_VALUE;
        }
        List<Integer> l = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < k; i++) {
            int cur = Integer.MIN_VALUE;
            int ind = -1;
            for (int j = 0; j < nums.length; j++) {
                if (!visited.contains(j) && nums[j] > cur) {
                    cur = nums[j];
                    ind = j;
                }
            }
            visited.add(ind);
            l.add(cur);
        }
        return l.get(l.size() - 1);
    }

    public int findKthLargest(int[] nums, int k) {
        int i = partition2(nums, 0, nums.length - 1, nums.length - k + 1);
        return i;
    }

    public int partition2(int[] nums, int left, int right, int k) {
        int i = left, j = right, pivot = nums[right];
        while (i < j) {
            if (nums[i++] > pivot) {
                swap(nums, --i, --j);
            }
        }
        swap(nums, i, right);
        int len = i - left + 1;
        if (len == k) {
            return pivot;
        } else if (len < k) {
            return partition2(nums, i + 1, right, k - len);
        } else {
            return partition2(nums, left, i - 1, k);
        }
    }





















    public int partition(int[] nums, int lo, int hi, int k) {
        int i = lo, j = hi, pivot = nums[hi];
        while (i < j) {
            if (nums[i++] > pivot) {
                swap(nums, --i, --j);
            }
        }
        swap(nums, i, hi);
        int len = i - lo + 1;
        if (len == k) {
            return i;
        } else if (len > k) {
            return partition(nums, lo, i - 1, k);
        } else {
            return partition(nums, i + 1, hi, k - len);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
