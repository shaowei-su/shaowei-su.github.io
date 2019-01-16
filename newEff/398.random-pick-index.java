/*
 * @lc app=leetcode id=398 lang=java
 *
 * [398] Random Pick Index
 *
 * https://leetcode.com/problems/random-pick-index/description/
 *
 * algorithms
 * Medium (48.14%)
 * Total Accepted:    48.3K
 * Total Submissions: 100.4K
 * Testcase Example:  '["Solution","pick"]\n[[[1,2,3,3,3]],[3]]'
 *
 * Given an array of integers with possible duplicates, randomly output the
 * index of a given target number. You can assume that the given target number
 * must exist in the array.
 * 
 * Note:
 * The array size can be very large. Solution that uses too much extra space
 * will not pass the judge.
 * 
 * Example:
 * 
 * 
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 * 
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should
 * have equal probability of returning.
 * solution.pick(3);
 * 
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(1);
 * 
 * 
 */
class Solution {
    int[] nums;
    public Solution(int[] nums) {
       this.nums = nums; 
    }
    
    public int pick(int target) {
        Random rand = new Random();
        int count = 0;
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                int cur = rand.nextInt(++count);
                res = cur == 0 ? i : res;
            }
        }
        return res;
    }
    public int pick2(int target) {
       List<Integer> indexs = new ArrayList<>();
       for (int i = 0; i < nums.length; i++) {
           if (nums[i] == target) {
               indexs.add(i);
            }
       }
       int size = indexs.size();
       Random r = new Random();
       return indexs.get(r.nextInt(size));
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
