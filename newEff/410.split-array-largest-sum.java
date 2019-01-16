/*
 * [410] Split Array Largest Sum
 *
 * https://leetcode.com/problems/split-array-largest-sum/description/
 *
 * algorithms
 * Hard (40.85%)
 * Total Accepted:    32.2K
 * Total Submissions: 78.7K
 * Testcase Example:  '[7,2,5,10,8]\n2'
 *
 * Given an array which consists of non-negative integers and an integer m, you
 * can split the array into m non-empty continuous subarrays. Write an
 * algorithm to minimize the largest sum among these m subarrays.
 * 
 * 
 * Note:
 * If n is the length of array, assume the following constraints are
 * satisfied:
 * 
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * 
 * 
 * 
 * Examples: 
 * 
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 * 
 * Output:
 * 18
 * 
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 * 
 * 
 */
class Solution {
        public int splitArray(int[] nums, int m) {
        int max = 0; long sum = 0;
        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        if (m == 1) return (int)sum;
        //binary search
        long l = max; long r = sum;
        while (l + 1 < r) {
            long mid = l + (r - l) / 2;
            if (isFit(nums, mid, m)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return isFit(nums, l, m) ? (int)l : (int)r;
    }
    public int splitArray2(int[] nums, int m) {
        if (nums == null || nums.length < m) {
            return 0;
        }
        long sum = Arrays.stream(nums).sum();
        long min =  (sum / m );
        long max =  (sum - m + 1);
        /*
        for (int i = min; i < max; i++) {
            if (isFit(nums, i, m)) {
                return i;
            }
        }
        */
        while (min <= max) {
            long mid = min + (max - min) / 2;
            if (isFit(nums, mid, m)) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return (int)min;
    }

    public boolean isFit(int[] nums, long sum, int m) {
        int pieces = 0;
        int i = 0;
        int cur = 0;
        while (i < nums.length && pieces < m) {
            if (nums[i] > sum) {
                return false;
            }
            if (nums[i] + cur > sum) {
                pieces++;
                cur = nums[i];
            } else {
                cur += nums[i];
            }
            i++;
        }
        if (cur > sum || pieces == m) {
            return false;
        }
        return true;
    }
}
