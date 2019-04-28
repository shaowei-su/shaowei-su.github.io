/*
 * [658] Find K Closest Elements
 *
 * https://leetcode.com/problems/find-k-closest-elements/description/
 *
 * algorithms
 * Medium (34.65%)
 * Total Accepted:    26.8K
 * Total Submissions: 77.4K
 * Testcase Example:  '[1,2,3,4,5]\n4\n3'
 *
 * 
 * Given a sorted array, two integers k and x, find the k closest elements to x
 * in the array.  The result should also be sorted in ascending order.
 * If there is a tie,  the smaller elements are always preferred.
 * 
 * 
 * Example 1:
 * 
 * Input: [1,2,3,4,5], k=4, x=3
 * Output: [1,2,3,4]
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [1,2,3,4,5], k=4, x=-1
 * Output: [1,2,3,4]
 * 
 * 
 * 
 * Note:
 * 
 * The value k is positive and will always be smaller than the length of the
 * sorted array.
 * ⁠Length of the given array is positive and will not exceed 104
 * ⁠Absolute value of elements in the array and x will not exceed 104
 * 
 * 
 * 
 * 
 * 
 * 
 * UPDATE (2017/9/19):
 * The arr parameter had been changed to an array of integers (instead of a
 * list of integers). Please reload the code definition to get the latest
 * changes.
 * 
 */
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        if (arr.length < k) {
            return res;
        }
        int left = 0;
        int right = arr.length - k;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }


        return res;
    




    }


    public List<Integer> findClosestElements3(int[] arr, int k, int x) {
        int left = 0; 
        int right = arr.length - 1;
        int target = -1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == x) {
                target = mid;
                break;
            } else if (arr[mid] > x) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (target == -1) {
            target = x >= arr[right] ? right : left;
        }
        left = target - 1;
        right = target + 1;
        List<Integer> res = new ArrayList<>();
        res.add(arr[target]);
        k--;
        while (k > 0) {
            if (left < 0) {
                res.add(arr[right++]);
            } else if (right >= arr.length) {
                res.add(arr[left--]);
            } else {
                if (Math.abs(arr[left] - x) == Math.abs(arr[right] - x)) {
                    res.add(arr[left--]);
                } else if (Math.abs(arr[left] - x) < Math.abs(arr[right] - x)) {
                    res.add(arr[left--]);
                } else {
                    res.add(arr[right++]);
                }
            }
            k--;
        }
        Collections.sort(res);
        return res;

    }
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        if (arr == null || arr.length < k) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> absMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            absMap.put(i, Math.abs(arr[i] - x));
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() == b.getValue() ? a.getKey() - b.getKey() : a.getValue() - b.getValue());
        for (Map.Entry<Integer, Integer> entry : absMap.entrySet()) {
            pq.offer(entry);
        }
        for (int i = 0; i < k; i++) {
            res.add(arr[pq.poll().getKey()]);
        }
        Collections.sort(res);
        return res;
    }
}
