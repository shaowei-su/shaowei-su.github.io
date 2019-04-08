/*
 * [350] Intersection of Two Arrays II
 *
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
 *
 * algorithms
 * Easy (44.60%)
 * Total Accepted:    133.1K
 * Total Submissions: 298.4K
 * Testcase Example:  '[1,2,2,1]\n[2,2]'
 *
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * 
 * 
 * Note:
 * 
 * 
 * Each element in the result should appear as many times as it shows in both
 * arrays.
 * The result can be in any order.
 * 
 * 
 * Follow up:
 * 
 * 
 * What if the given array is already sorted? How would you optimize your
 * algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is
 * better?
 * What if elements of nums2 are stored on disk, and the memory is limited such
 * that you cannot load all elements into the memory at once?
 * 
 * 
 */
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> num1Count = new HashMap<>();
        Map<Integer, Integer> num2Count = new HashMap<>();
        for (int num : nums1) {
            num1Count.put(num, num1Count.getOrDefault(num, 0) + 1);
        }
        for (int num : nums2) {
            num2Count.put(num, num2Count.getOrDefault(num, 0) + 1);
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : num1Count.entrySet()) {
            if (num2Count.containsKey(entry.getKey())) {
                int count = Math.min(entry.getValue(), num2Count.get(entry.getKey()));
                while (count-- > 0) {
                    res.add(entry.getKey());
                }
            }
        }
        int[] resArr = new int[res.size()];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = res.get(i);
        }
        return resArr;
    }
}
