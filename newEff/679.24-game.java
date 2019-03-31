/*
 * @lc app=leetcode id=679 lang=java
 *
 * [679] 24 Game
 *
 * https://leetcode.com/problems/24-game/description/
 *
 * algorithms
 * Hard (40.94%)
 * Total Accepted:    18.2K
 * Total Submissions: 43.3K
 * Testcase Example:  '[4,1,8,7]'
 *
 * 
 * You have 4 cards each containing a number from 1 to 9.  You need to judge
 * whether they could operated through *, /, +, -, (, ) to get the value of
 * 24.
 * 
 * 
 * Example 1:
 * 
 * Input: [4, 1, 8, 7]
 * Output: True
 * Explanation: (8-4) * (7-1) = 24
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [1, 2, 1, 2]
 * Output: False
 * 
 * 
 * 
 * Note:
 * 
 * The division operator / represents real division, not integer division.  For
 * example, 4 / (1 - 2/3) = 12.
 * Every operation done is between two numbers.  In particular, we cannot use -
 * as a unary operator.  For example, with [1, 1, 1, 1] as input, the
 * expression -1 - 1 - 1 - 1 is not allowed.
 * You cannot concatenate numbers together.  For example, if the input is [1,
 * 2, 1, 2], we cannot write this as 12 + 12.
 * 
 * 
 * 
 */
class Solution {
    public boolean judgePoint24(int[] nums) {
        List<Double> candidates = new ArrayList<>();
        for (int num : nums) {
            candidates.add((double) num);
        }
        return dfs(candidates);
    }
    
    public boolean dfs(List<Double> candidates) {
        if (candidates.size() == 1) {
            if (Math.abs(candidates.get(0) - 24.0) < 1e-3) {
                return true;
            } else {
                return false;
            }
        }
        for (int i = 0; i < candidates.size() - 1; i++) {
            for (int j = i + 1; j < candidates.size(); j++) {
                for (Double com : getCombinations(candidates.get(i), candidates.get(j))) {
                    List<Double> nextRounds = new ArrayList<>();
                    nextRounds.add(com);
                    for (int k = 0; k < candidates.size(); k++) {
                        if (k == i || k == j) {
                            continue;
                        }
                        nextRounds.add(candidates.get(k));
                    }
                    if (dfs(nextRounds)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<Double> getCombinations(Double a, Double b) {
        List<Double> res = new ArrayList<>();
        res.add(a - b);
        res.add(a + b);
        res.add(a / b);
        res.add(a * b);
        res.add(b - a);
        res.add(b / a);
        return res;
    }
}
