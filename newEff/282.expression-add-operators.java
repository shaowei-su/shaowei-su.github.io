/*
 * @lc app=leetcode id=282 lang=java
 *
 * [282] Expression Add Operators
 *
 * https://leetcode.com/problems/expression-add-operators/description/
 *
 * algorithms
 * Hard (31.89%)
 * Total Accepted:    61.9K
 * Total Submissions: 193K
 * Testcase Example:  '"123"\n6'
 *
 * Given a string that contains only digits 0-9 and a target value, return all
 * possibilities to add binary operators (not unary) +, -, or * between the
 * digits so they evaluate to the target value.
 * 
 * Example 1:
 * 
 * 
 * Input: num = "123", target = 6
 * Output: ["1+2+3", "1*2*3"] 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = "232", target = 8
 * Output: ["2*3+2", "2+3*2"]
 * 
 * Example 3:
 * 
 * 
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 * 
 * Example 4:
 * 
 * 
 * Input: num = "00", target = 0
 * Output: ["0+0", "0-0", "0*0"]
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: num = "3456237490", target = 9191
 * Output: []
 * 
 */
class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return res;
        }
        if (Long.parseLong(num) > Long.valueOf(Integer.MAX_VALUE)) {
            return res;
        }
        dfs(res, num, "", target, 0, 0, 0);
        return res;
    }

    public void dfs(List<String> res, String num, String cur, int target, int curSum, int mul, int pos) {
        if (pos == num.length() && curSum == target) {
            res.add(cur);
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            if (i != pos && num.charAt(pos) == '0') {
                break;
            } else {
                Integer segment = Integer.parseInt(num.substring(pos, i + 1));
                if (pos == 0) {
                    dfs(res, num, String.valueOf(segment), target, segment, segment, i + 1);
                } else {
                    dfs(res, num, cur + "+" + segment, target, curSum + segment, segment, i + 1);
                    dfs(res, num, cur + "-" + segment, target, curSum - segment, -segment, i + 1);
                    dfs(res, num, cur + "*" + segment, target, curSum - mul + mul * segment, mul * segment, i + 1);
                }
            }
        }
    }
}
