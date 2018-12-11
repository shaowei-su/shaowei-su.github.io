/*
 * [670] Maximum Swap
 *
 * https://leetcode.com/problems/maximum-swap/description/
 *
 * algorithms
 * Medium (38.98%)
 * Total Accepted:    30K
 * Total Submissions: 77K
 * Testcase Example:  '2736'
 *
 * 
 * Given a non-negative integer, you could swap two digits at most once to get
 * the maximum valued number. Return the maximum valued number you could get.
 * 
 * 
 * Example 1:
 * 
 * Input: 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: 9973
 * Output: 9973
 * Explanation: No swap.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * The given number is in the range [0, 108]
 * 
 * 
 */
class Solution {
    public int maximumSwap(int num) {
         String numStr = String.valueOf(num);
         char[] numArr = numStr.toCharArray();
         int[] buckets = new int[10];
         for (int i = 0; i < numArr.length; i++) {
             buckets[numArr[i] - '0'] = i;
             // last index of digit c- '0'
         }
         for (int i = 0; i < numArr.length - 1; i++) {
             for (int j = 9; j > numArr[i] - '0'; j--) {
                 if (buckets[j] > i) {
                     char temp = numArr[i];
                     numArr[i] = numArr[buckets[j]];
                     numArr[buckets[j]] = temp;
                     return Integer.parseInt(new String(numArr));
                 }
             }
         }
         return num;

    }
    public int maximumSwap2(int num) {
        String numStr = String.valueOf(num);
        char[] numArr = numStr.toCharArray();
        for (int i = 0; i < numArr.length; i++) {
            for (int j = i + 1; j < numArr.length; j++) {
                int cur = swap(numArr, i, j);
                num = Math.max(num, cur);
            }
        }
        return num;
    }
    public int swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        int res = Integer.parseInt(new String(arr));
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return res;
    }
}
