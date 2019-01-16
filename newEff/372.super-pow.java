/*
 * @lc app=leetcode id=372 lang=java
 *
 * [372] Super Pow
 *
 * https://leetcode.com/problems/super-pow/description/
 *
 * algorithms
 * Medium (35.27%)
 * Total Accepted:    25.3K
 * Total Submissions: 71.5K
 * Testcase Example:  '2\n[3]'
 *
 * Your task is to calculate a^b mod 1337 where a is a positive integer and b
 * is an extremely large positive integer given in the form of an array.
 * 
 * Example 1:
 * 
 * 
 * 
 * Input: a = 2, b = [3]
 * Output: 8
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: a = 2, b = [1,0]
 * Output: 1024
 * 
 * 
 * 
 */
class Solution {
    public int superPow(int a, int[] b) {
        if (b == null || b.length == 0) {
            return 1;
        }
        if (b.length < 9) {
            return pow(a, convert2int(b));
        }
        boolean isEven = b[b.length - 1] % 2 == 0;
        int[] split = divide(b);
        int partial = superPow(a, split) % 1337;
        int mul = (partial * partial) % 1337;
        return isEven ? mul : (mul * (a % 1337)) % 1337;
    }
    public int pow(int a, int b) {
        if (b == 1) {
            return a;
        }
        int split = pow(a, b / 2) % 1337;
        int mul = (split * split) % 1337;
        return b % 2 == 0 ? mul : (mul * (a % 1337)) % 1337;
    }
    public int convert2int(int[] b) {
        int res = 0;
        for (int i = 0; i < b.length; i++) {
            res = res * 10 + b[i];
        }
        return res;
    }
    public int[] divide(int[] b) {
        int len = b.length;
        return Arrays.copyOf(b, len - 1);
    }
}
