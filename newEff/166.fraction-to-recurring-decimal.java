/*
 * @lc app=leetcode id=166 lang=java
 *
 * [166] Fraction to Recurring Decimal
 *
 * https://leetcode.com/problems/fraction-to-recurring-decimal/description/
 *
 * algorithms
 * Medium (18.96%)
 * Total Accepted:    82.3K
 * Total Submissions: 430.1K
 * Testcase Example:  '1\n2'
 *
 * Given two integers representing the numerator and denominator of a fraction,
 * return the fraction in string format.
 * 
 * If the fractional part is repeating, enclose the repeating part in
 * parentheses.
 * 
 * Example 1:
 * 
 * 
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * 
 * Example 3:
 * 
 * 
 * Input: numerator = 2, denominator = 3
 * Output: "0.(6)"
 * 
 * 
 */
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) {
            return "";
        }
        if (numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        if (numerator < 0 ^ denominator < 0) {
            sb.append("-");
        }
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        sb.append(num / den);
        if (num % den == 0) {
            return sb.toString();
        }
        num = num % den;
        sb.append(".");
        Map<Long, Integer> resMap = new HashMap<>();
        resMap.put(num, sb.length());
        while (num > 0) {
            num *= 10;
            sb.append(num / den);
            num = num % den;
            if (resMap.containsKey(num)) {
                sb.insert(resMap.get(num), "(");
                sb.append(")");
                break;
            } else {
                resMap.put(num, sb.length());
            }
        }
        return sb.toString();



    }
    public String fractionToDecimal2(int numerator, int denominator) {
        if (denominator == 0) {
            return "";
        }
        String res = String.valueOf((double) numerator / (double) denominator);
        String[] parts = res.split("\\.");
        if (parts[1].equals("0")) {
            return parts[0];
        }
        return parts[0] + "." + parse(parts[1]);
    }
    public String parse(String res) {

        int pos = 0;
        Map<Character, Integer> posMap = new HashMap<>();
        int dist = 0;
        boolean recur = false;
        StringBuilder sb = new StringBuilder();
        while (pos < res.length()) {
            if (!posMap.containsKey(res.charAt(pos))) {
                posMap.put(res.charAt(pos), pos);
                sb.append(res.charAt(pos));
            } else {
                int cur = pos - posMap.get(res.charAt(pos));
                dist = cur;
                while (pos < res.length() && cur > 0) {
                    if (posMap.containsKey(res.charAt(pos)) && (pos - posMap.get(res.charAt(pos)) == dist)) {
                        cur--;
                        pos++;
                        continue;
                    }
                    break;
                }
                if (cur == 0) {
                    recur = true;
                    break;
                }   
            }
            pos++;
        }
        if (!recur) {
            return res;
        } else {
            int sbLen = sb.length();
            String left = sbLen > 1 ? sb.substring(0, sbLen - dist) : "";
            String right = sbLen > 1 ? sb.substring(sbLen - dist) : sb.toString();
            return left + "(" + right + ")";
        }
    }
}
