/*
 * [640] Solve the Equation
 *
 * https://leetcode.com/problems/solve-the-equation/description/
 *
 * algorithms
 * Medium (39.23%)
 * Total Accepted:    13.8K
 * Total Submissions: 35.1K
 * Testcase Example:  '"x+5-3+x=6+x-2"'
 *
 * 
 * Solve a given equation and return the value of x in the form of string
 * "x=#value". The equation contains only '+', '-' operation, the variable x
 * and its coefficient.
 * 
 * 
 * 
 * If there is no solution for the equation, return "No solution".
 * 
 * 
 * If there are infinite solutions for the equation, return "Infinite
 * solutions".
 * 
 * 
 * If there is exactly one solution for the equation, we ensure that the value
 * of x is an integer.
 * 
 * 
 * Example 1:
 * 
 * Input: "x+5-3+x=6+x-2"
 * Output: "x=2"
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "x=x"
 * Output: "Infinite solutions"
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: "2x=x"
 * Output: "x=0"
 * 
 * 
 * 
 * Example 4:
 * 
 * Input: "2x+3x-6x=x+2"
 * Output: "x=-1"
 * 
 * 
 * 
 * Example 5:
 * 
 * Input: "x=x+2"
 * Output: "No solution"
 * 
 * 
 */
class Solution {

    class State {
        int nx;
        int cons;
        public State(int nx, int cons) {
            this.nx = nx;
            this.cons = cons;
        }
    }

    public String solveEquation(String equation) {
        // every side can convert to  : nx + c = mx + d
        // if n == m...
        //
        String[] parts = equation.split("=");
        State left = consolidate(parts[0]);
        State right = consolidate(parts[1]);

        if (left.nx == right.nx) {
            if (left.cons == right.cons) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        } else {
            int consDiff = right.cons - left.cons;
            int nxDiff = left.nx - right.nx;
            int result = consDiff / nxDiff;
            return "x=" + result;
        }
    }

    public State consolidate(String part) {
        int ns = 0, cons = 0;
        boolean isPos = true;
        char[] chars = part.toCharArray();
        int cache = 0;
        boolean sign = false;
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                cache = cache * 10 + (chars[i] - '0');
            } else if (chars[i] == 'x') {
                if (i > 0 && chars[i - 1] == '0') {
                    continue;
                }
                if (isPos) {
                    ns += cache == 0 ? 1 : cache;
                } else {
                    ns -= cache == 0 ? 1 : cache;
                }
                isPos = true;
                cache = 0;
            } else if (chars[i] == '+') {
                if (isPos) {
                    cons += cache;
                } else {
                    cons -= cache;
                }
                isPos = true;
                cache = 0;
            } else if (chars[i] == '-') {
                if (isPos) {
                    cons += cache;
                } else {
                    cons -= cache;
                }
                isPos = false;
                cache = 0;
            }
        }
        if (cache != 0) {
            if (isPos) {
                cons += cache;
            } else {
                cons -= cache;
            }
        }
        return new State(ns, cons);
    }
}
