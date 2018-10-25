/*
 * [60] Permutation Sequence
 *
 * https://leetcode.com/problems/permutation-sequence/description/
 *
 * algorithms
 * Medium (30.86%)
 * Total Accepted:    116.6K
 * Total Submissions: 376.9K
 * Testcase Example:  '3\n3'
 *
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 * 
 * By listing and labeling all of the permutations in order, we get the
 * following sequence for n = 3:
 * 
 * 
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 
 * 
 * Given n and k, return the kth permutation sequence.
 * 
 * Note:
 * 
 * 
 * Given n will be between 1 and 9 inclusive.
 * Given k will be between 1 and n! inclusive.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3, k = 3
 * Output: "213"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 4, k = 9
 * Output: "2314"
 * 
 * 
 */
class Solution {
    public String getPermutation(int n, int k) {
        if (n <= 0) return "";
        int count = 1;
        int factHelp = n - 1;
        while (factHelp > 0) {
            count *= factHelp;
            factHelp--;
        }
        int origi = n;
        n--;
        StringBuilder sb = new StringBuilder();
        Set<Integer> wo = new HashSet<>();
        while (n >= 1) {
            int place = k == count ? 0 : k / count;
            int cur = ith(place, wo);
            wo.add(cur);
            sb.append(cur);
            k = k % count;
            count = count / n--;
        }
        sb.append(last(origi, wo));
        return sb.toString();
    }

    public int last(int n, Set<Integer> wo) {
        int iter = 1;
        while (iter <= n) {
            if (wo.contains(iter)) {
                iter++;
            } else {
                return iter;
            }
        }
        return n;
    }

    public int ith(int i, Set<Integer> wo) {
        int count = 0;
        int iter = 1;
        while (count < i) {
            if (wo.contains(iter)) {
                iter++;
            } else {
                count++;
                iter++;
            }
        }
        return iter;
    }

    public int fact(int n) {
        if (n == 1) return 1;
        return n * fact(n - 1);
    }
}
