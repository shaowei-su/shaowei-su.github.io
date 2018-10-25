/*
 * [65] Valid Number
 *
 * https://leetcode.com/problems/valid-number/description/
 *
 * algorithms
 * Hard (13.19%)
 * Total Accepted:    100.7K
 * Total Submissions: 760K
 * Testcase Example:  '"0"'
 *
 * Validate if a given string can be interpreted as a decimal number.
 * 
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 * 
 * Note: It is intended for the problem statement to be ambiguous. You should
 * gather all requirements up front before implementing one. However, here is a
 * list of characters that can be in a valid decimal number:
 * 
 * 
 * Numbers 0-9
 * Exponent - "e"
 * Positive/negative sign - "+"/"-"
 * Decimal point - "."
 * 
 * 
 * Of course, the context of these characters also matters in the input.
 * 
 * Update (2015-02-10):
 * The signature of the C++ function had been updated. If you still see your
 * function signature accepts a const char * argument, please click the reload
 * button to reset your code definition.
 * 
 */
class Solution {
    public boolean isNumber(String s) {
        String input = s.trim();
        if (input.length() == 0) {
            return false;
        }
        if (input.length() == 1 && !Character.isDigit(input.charAt(0))) return false;
         boolean isExp = false;
         boolean isDecim = false;
         boolean isMinus = false;
         boolean anyNum = false;
        char[] chars = input.toCharArray();
        if (chars[0] != '-' && chars[0] != '+' && !Character.isDigit(chars[0]) && !(chars[0] == '.')) return false;
        if (chars[0] == '.') isDecim = true;
        if (Character.isDigit(chars[0])) anyNum = true;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i - 1] == 'e' && (chars[i] == '-' || chars[i] == '+') && i < chars.length - 1) {
                isMinus = true;
                continue;
            }
            if (chars[i] == '-' || chars[i] == '+') {
                return false;
            }
            if (chars[i] == '.') {
                if (isDecim || isExp) {
                    return false;
                } else {
                    isDecim = true;
                    continue;
                }
            }
            
            if (chars[i] == 'e') {
                if (!anyNum) return false;
                if (isExp) return false;
                if (chars[i - 1] == '.' && i == 1) {
                    return false;
                }
                if (i == chars.length - 1) {
                    return false;
                }
                isExp = true;
                continue;
            }

            if (!Character.isDigit(chars[i])) {
                return false;
            } else {
                anyNum = true;
            }
        }

        return anyNum;
    }
}
