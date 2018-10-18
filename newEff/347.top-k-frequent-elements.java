/*
 * [347] Top K Frequent Elements
 *
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 *
 * algorithms
 * Medium (50.78%)
 * Total Accepted:    128.8K
 * Total Submissions: 253.6K
 * Testcase Example:  '[1,1,1,2,2,3]\n2'
 *
 * Given a non-empty array of integers, return the k most frequent elements.
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1], k = 1
 * Output: [1]
 * 
 * 
 * Note: 
 * 
 * 
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is
 * the array's size.
 * 
 * 
 */
class Solution {
    class Pair implements Comparable<Pair> {
        int a;
        int b;
        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public int compareTo(Pair p) {
            return this.b - p.b;
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Map<Integer, Integer> valFreMap = new HashMap<>();
        Map<Integer, Integer> freValMap = new HashMap<>();
        for (int num : nums) {
            if (valFreMap.containsKey(num)) {
                valFreMap.put(num, valFreMap.get(num) + 1);
            } else {
                valFreMap.put(num, 1);
            }
        }
        List<Integer>[] buckets = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : valFreMap.entrySet()) {
            List<Integer> l = buckets[entry.getValue().intValue()];
            if (l == null) {
                l = new ArrayList<Integer>();
                buckets[entry.getValue().intValue()] = l;
            }
            l.add(entry.getKey());
        }
        for (int i = buckets.length - 1; i >= 0 && res.size() < k; i--) {
            if (buckets[i] != null) {
                res.addAll(buckets[i]);
            }
        }


        return res;
    }
}
