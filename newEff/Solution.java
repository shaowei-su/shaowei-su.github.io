/*
 * [439] Ternary Expression Parser
 *
 * https://leetcode.com/problems/ternary-expression-parser/description/
 *
 * algorithms
 * Medium (52.46%)
 * Total Accepted:    13.2K
 * Total Submissions: 25.1K
 * Testcase Example:  '"T?2:3"'
 *
 * Given a string representing arbitrarily nested ternary expressions,
 * calculate the result of the expression. You can always assume that the given
 * expression is valid and only consists of digits 0-9, ?, :, T and F (T and F
 * represent True and False respectively).
 * 
 * Note:
 * 
 * The length of the given string is ≤ 10000.
 * Each number will contain only one digit.
 * The conditional expressions group right-to-left (as usual in most
 * languages).
 * The condition will always be either T or F. That is, the condition will
 * never be a digit.
 * The result of the expression will always evaluate to either a digit 0-9, T
 * or F.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: "T?2:3"
 * 
 * Output: "2"
 * 
 * Explanation: If true, then result is 2; otherwise result is 3.
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "F?1:T?4:5"
 * 
 * Output: "4"
 * 
 * Explanation: The conditional expressions group right-to-left. Using
 * parenthesis, it is read/evaluated as:
 * 
 * ⁠            "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 :
 * 5))"
 * ⁠         -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
 * ⁠         -> "4"                                    -> "4"
 * 
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: "T?T?F:5:3"
 * 
 * Output: "F"
 * 
 * Explanation: The conditional expressions group right-to-left. Using
 * parenthesis, it is read/evaluated as:
 * 
 * ⁠            "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) :
 * 3)"
 * ⁠         -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
 * ⁠         -> "F"                                    -> "F"
 * 
 * 
 */
class Solution {
    public String parseTernary(String expression) {
        if (!expression.contains("?")) {
            return expression;
        }
        System.out.println("now processing  : " + expression);
        int lastQ = expression.lastIndexOf('?');
        int nextCol = expression.indexOf(':', lastQ);
        int neiCol = nextSymbol(expression, nextCol);
        System.out.println("lastQ = {}, nextCol = {}, neiCol = {}" +  lastQ + " " + nextCol + " " +  neiCol);
        String left = expression.substring(lastQ + 1, nextCol);
        String right = neiCol > 0 ? expression.substring(nextCol + 1, neiCol) : expression.substring(nextCol + 1);
        String temp = eval(expression.charAt(lastQ - 1)) ? left : right;
        System.out.println("temp = " + temp);
        String lefter = lastQ == 1 ? "" : expression.substring(0, lastQ - 1);
        String righter = neiCol > 0 ? expression.substring(neiCol) : "";
        return parseTernary(lefter + temp + righter);
    }

    public int nextSymbol(String exp, int start) {
        for (int i = start + 1; i < exp.length(); i++) {
            if (!Character.isDigit(exp.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public boolean eval(char exp) {
        if (exp == 'T') {
            System.out.println("eval true !");
            return true;
        } else {
            System.out.println("eval false !");
            return false;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.parseTernary("T?2:3");
    }
}
