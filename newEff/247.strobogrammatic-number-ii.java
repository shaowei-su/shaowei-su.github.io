/*
 * @lc app=leetcode id=247 lang=java
 *
 * [247] Strobogrammatic Number II
 *
 * https://leetcode.com/problems/strobogrammatic-number-ii/description/
 *
 * algorithms
 * Medium (43.13%)
 * Total Accepted:    42.8K
 * Total Submissions: 98.5K
 * Testcase Example:  '2'
 *
 * A strobogrammatic number is a number that looks the same when rotated 180
 * degrees (looked at upside down).
 * 
 * Find all strobogrammatic numbers that are of length = n.
 * 
 * Example:
 * 
 * 
 * Input:  n = 2
 * Output: ["11","69","88","96"]
 * 
 * 
 */
class Solution {
    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<>();
        if (n < 1) {
            return res;
        }
        dfs(res, "", n);
        return res;


    }

    char[] arr1 = {'0', '1', '6', '8', '9'};
    char[] arr2 = {'0', '1', '8'};

    public void dfs(List<String> res, String cur, int n) {
        if (cur.length() * 2 > n + 1) {
            return;
        }
        if ((n % 2 == 0) && (cur.length() * 2 == n)) {
            res.add(expand(cur));
            return;
        } else if ((n % 2== 1) && (cur.length() * 2 == n + 1)) {
            res.add(expand(cur));
            return;
        }
        
        if (cur.length()



    public List<String> findStrobogrammatic2(int n) {
        List<String> res = new ArrayList<>();
        if (n < 1) {
            return res;
        }
        if (n == 1) {
            res.add("0");
        }
        long start = (long) Math.pow(10, n - 1);
        long end = (long) Math.pow(10, n);
        for (long l = start; l < end; l++) {
            if (isStrobogrammatic(String.valueOf(l))) {
                res.add(String.valueOf(l));
            }
        }
        return res;
    }
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
            return true;
        }
        int left = 0;
        int right = num.length() - 1;
        while (left < right) {
            if ((num.charAt(left) == num.charAt(right) && num.charAt(left) == '8')
                    || (num.charAt(left) == '6' && num.charAt(right) == '9')
                    || (num.charAt(left) == '9' && num.charAt(right) == '6')
                    || (num.charAt(left) == num.charAt(right) && num.charAt(left) == '1')
                    || (num.charAt(left) == num.charAt(right) && num.charAt(left) == '0')) {
                left++;
                right--;
                continue;
                    }
            return false;
        }
        if (left == right) {
            if (num.charAt(left) != '8' && num.charAt(left) != '1' && num.charAt(left) != '0') {
                return false;
            }
        }
        return true;
    }
}
