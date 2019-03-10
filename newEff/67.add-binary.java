/*
 * @lc app=leetcode id=67 lang=java
 *
 * [67] Add Binary
 *
 * https://leetcode.com/problems/add-binary/description/
 *
 * algorithms
 * Easy (37.26%)
 * Total Accepted:    277.4K
 * Total Submissions: 730.4K
 * Testcase Example:  '"11"\n"1"'
 *
 * Given two binary strings, return their sum (also a binary string).
 * 
 * The input strings are both non-empty and contains only characters 1 or 0.
 * 
 * Example 1:
 * 
 * 
 * Input: a = "11", b = "1"
 * Output: "100"
 * 
 * Example 2:
 * 
 * 
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 * 
 */
class Solution {
    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0) {
            return b;
        }
        if (b == null || b.length() == 0) {
            return a;
        }
        char[] al = new StringBuilder(a).reverse().toString().toCharArray();
        char[] bl = new StringBuilder(b).reverse().toString().toCharArray();
        int carry = 0;
        int ai = 0;
        int bi = 0;
        List<Character> cl = new ArrayList<>();
        while (ai < al.length || bi < bl.length || carry > 0) {
            int av = ai < al.length ? al[ai] - '0' : 0;
            int bv = bi < bl.length ? bl[bi] - '0' : 0;
            int res = av + bv + carry;
            cl.add((char)(res % 2 + '0'));
            carry = res / 2;
            ai++;
            bi++;
        }
        Collections.reverse(cl);
        StringBuilder ret = new StringBuilder();
        for (Character c : cl) {
            ret.append(c);
        }

        return ret.toString();

    }
}
