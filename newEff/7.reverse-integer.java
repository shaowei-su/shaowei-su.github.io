/*
 * [7] Reverse Integer
 *
 * https://leetcode.com/problems/reverse-integer/description/
 *
 * algorithms
 * Easy (24.48%)
 * Total Accepted:    510K
 * Total Submissions: 2.1M
 * Testcase Example:  '123'
 *
 * Given a 32-bit signed integer, reverse digits of an integer.
 * 
 * Example 1:
 * 
 * 
 * Input: 123
 * Output: 321
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: -123
 * Output: -321
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: 120
 * Output: 21
 * 
 * 
 * Note:
 * Assume we are dealing with an environment which could only store integers
 * within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of
 * this problem, assume that your function returns 0 when the reversed integer
 * overflows.
 * 
 */
class Solution {

    public int reverse(int x) {
        if (x == 0) return 0;
        int res = 0;
        while (x != 0) {
            int tail = x % 10;
            int newRes = res * 10 + tail;
            x = x / 10;
            if ((newRes - tail) / 10 != res) return 0;
            res = newRes;
        }
        return res;
    }


    public int reverse3(int x) {
        if (x == 0) return 0;
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x = x / 10;
            if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return 0;
        }
        return (int)res;
    }

    public int reverse2(int x) {
        boolean isNeg = false;
        if (x == 0) return 0;
        long y = (long) x;
        if (y < 0) {
            isNeg = true;
            y = -y;
        }
        String temp = String.valueOf(y);
        String reverse = re(temp);
        int start = 0;
        while (start < reverse.length() && reverse.charAt(start) == '0') start++;
        Long res = Long.valueOf(reverse.substring(start));
        if (res > Integer.MAX_VALUE) return 0;
        if (isNeg) res = res * -1;
        return res.intValue();
    }
    public String re(String in) {
        char[] arr = in.toCharArray();
        int left = 0;
        int right = in.length() - 1;
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        return new String(arr);
    }
}
