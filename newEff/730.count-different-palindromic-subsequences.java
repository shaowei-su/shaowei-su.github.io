/*
 * @lc app=leetcode id=730 lang=java
 *
 * [730] Count Different Palindromic Subsequences
 *
 * https://leetcode.com/problems/count-different-palindromic-subsequences/description/
 *
 * algorithms
 * Hard (37.09%)
 * Total Accepted:    7.6K
 * Total Submissions: 20.1K
 * Testcase Example:  '"bccb"'
 *
 * 
 * Given a string S, find the number of different non-empty palindromic
 * subsequences in S, and return that number modulo 10^9 + 7.
 * 
 * A subsequence of a string S is obtained by deleting 0 or more characters
 * from S.
 * 
 * A sequence is palindromic if it is equal to the sequence reversed.
 * 
 * Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some
 * i for which A_i != B_i.
 * 
 * 
 * Example 1:
 * 
 * Input: 
 * S = 'bccb'
 * Output: 6
 * Explanation: 
 * The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc',
 * 'bcb', 'bccb'.
 * Note that 'bcb' is counted only once, even though it occurs twice.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: 
 * S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
 * Output: 104860361
 * Explanation: 
 * There are 3104860382 different non-empty palindromic subsequences, which is
 * 104860361 modulo 10^9 + 7.
 * 
 * 
 * 
 * Note:
 * The length of S will be in the range [1, 1000].
 * Each character S[i] will be in the set {'a', 'b', 'c', 'd'}.
 * 
 */
class Solution {
    int div = 10000007;
    public int countPalindromicSubsequences(String S) {
        TreeSet[] charSet = new TreeSet[26];    
        for (int i = 0; i < 26; i++) {
            charSet[i] = new TreeSet<Integer>();
        }
        for (int i = 0; i < S.length(); i++) {
            charSet[S.charAt(i) - 'a'].add(i);
        }
        Integer[][] dp = new Integer[S.length() + 1][S.length() + 1];
        return dfs(S, dp, 0, S.length(), charSet);
    }

    public int dfs(String S, Integer[][] dp, Integer start, Integer end, TreeSet<Integer>[] charSet) {
        if (start >= end) {
            return 0;
        }
        if (dp[start][end] != null) {
            return dp[start][end];
        }
        long ans = 0;
        for (int i = 0; i < 26; i++) {
            Integer new_s = charSet[i].ceiling(start);
            Integer new_e = charSet[i].lower(end);
            if (new_s == null || new_s >= end) {
                continue;
            }
            ans++;
            if (new_s != new_e) {
                ans++;
            }
            ans += dfs(S, dp, new_s + 1, new_e, charSet);
            ans %= div;
        }
        dp[start][end] = (int) (ans % div);
        return dp[start][end];
    }
}
