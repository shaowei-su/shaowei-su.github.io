/*
 * [254] Factor Combinations
 *
 * https://leetcode.com/problems/factor-combinations/description/
 *
 * algorithms
 * Medium (43.72%)
 * Total Accepted:    43.6K
 * Total Submissions: 99.8K
 * Testcase Example:  '1'
 *
 * Numbers can be regarded as product of its factors. For example,
 * 
 * 
 * 8 = 2 x 2 x 2;
 * ⁠ = 2 x 4.
 * 
 * 
 * Write a function that takes an integer n and return all possible
 * combinations of its factors.
 * 
 * Note:
 * 
 * 
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 * 
 * 
 * Example 1: 
 * 
 * 
 * Input: 1
 * Output: []
 * 
 * 
 * Example 2: 
 * 
 * 
 * Input: 37
 * Output:[]
 * 
 * Example 3: 
 * 
 * 
 * Input: 12
 * Output:
 * [
 * ⁠ [2, 6],
 * ⁠ [2, 2, 3],
 * ⁠ [3, 4]
 * ]
 * 
 * Example 4: 
 * 
 * 
 * Input: 32
 * Output:
 * [
 * ⁠ [2, 16],
 * ⁠ [2, 2, 8],
 * ⁠ [2, 2, 2, 4],
 * ⁠ [2, 2, 2, 2, 2],
 * ⁠ [2, 4, 4],
 * ⁠ [4, 8]
 * ]
 * 
 * 
 */
class Solution {
    public List<List<Integer>> getFactors(int n) {
        return helper(n, true, 2);
    }


    public List<List<Integer>> helper(int n, boolean isFirst, int start) {
        List<List<Integer>> res = new ArrayList<>();
        if (!isFirst) {
            List<Integer> self = new ArrayList<>();
            self.add(n);
            res.add(self);
        }
        double sq = Math.sqrt(n);
        for (int i = start; i <= sq; i++) {
            if (n % i == 0) {
                List<List<Integer>> subRes = helper(n / i, false, i);
                for (List<Integer> sub : subRes) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.addAll(sub);
                    res.add(temp);
                }
            }
        }
        return res;        
    }
}
