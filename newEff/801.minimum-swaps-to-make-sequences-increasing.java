/*
 * [819] Minimum Swaps To Make Sequences Increasing
 *
 * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/description/
 *
 * algorithms
 * Medium (32.54%)
 * Total Accepted:    9.4K
 * Total Submissions: 28.9K
 * Testcase Example:  '[1,3,5,4]\n[1,2,3,7]'
 *
 * We have two integer sequences A and B of the same non-zero length.
 * 
 * We are allowed to swap elements A[i] and B[i].  Note that both elements are
 * in the same index position in their respective sequences.
 * 
 * At the end of some number of swaps, A and B are both strictly increasing.
 * (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... <
 * A[A.length - 1].)
 * 
 * Given A and B, return the minimum number of swaps to make both sequences
 * strictly increasing.  It is guaranteed that the given input always makes it
 * possible.
 * 
 * 
 * Example:
 * Input: A = [1,3,5,4], B = [1,2,3,7]
 * Output: 1
 * Explanation: 
 * Swap A[3] and B[3].  Then the sequences are:
 * A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
 * which are both strictly increasing.
 * 
 * 
 * Note:
 * 
 * 
 * A, B are arrays with the same length, and that length will be in the range
 * [1, 1000].
 * A[i], B[i] are integer values in the range [0, 2000].
 * 
 * 
 */
class Solution {
    public int minSwap(int[] A, int[] B) {
         if (A == null || B == null || A.length != B.length || A.length < 2) {
             return 0;
         }
         int len = A.length;
         int[][] dp = new int[len][len];
         dp[0][0] = 0;
         dp[0][1] = 1;
         for (int i = 1; i < len; i++) {
                dp[i][0] = dp[i][1] = Integer.MAX_VALUE;
                if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                    dp[i][0] = dp[i - 1][0];
                    dp[i][1] = dp[i - 1][1] + 1;
                }
                if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
                    dp[i][0] = Math.min(dp[i][0], dp[i - 1][1]);
                    dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] + 1);
                }
        }
         
        return Math.min(dp[len - 1][0], dp[len - 1][1]);
    }
    public int minSwap3(int[] A, int[] B) {
        if (A == null || B == null || A.length != B.length) {
            return 0;
        }
        return minSwap2(A, B, 0);
    }
    public int minSwap2(int[] A, int[] B, int ind) {
        if (ind >= A.length) return 0;
        if (A[ind] == B[ind]) {
            return minSwap2(A, B, ind + 1);
        }
        int[] Anew = Arrays.copyOfRange(A, 0, A.length);
        int[] Bnew = Arrays.copyOfRange(B, 0, B.length);

        int temp = Anew[ind];
        Anew[ind] = Bnew[ind];
        Bnew[ind] = temp;
        if (ind == 0) {
            return Math.min(minSwap2(A, B, ind + 1), minSwap2(Anew, Bnew, ind + 1) + 1);
        } else {
            if (A[ind] <= A[ind - 1] || B[ind] <= B[ind - 1]) {
                return minSwap2(Anew, Bnew, ind + 1) + 1;
            }
            if (Anew[ind] <= Anew[ind - 1] || Bnew[ind] <= Bnew[ind - 1]) {
                return minSwap2(A, B, ind + 1);
            }
            return Math.min(minSwap2(A, B, ind + 1), minSwap2(Anew, Bnew, ind + 1) + 1);
        }
    }
            
}
