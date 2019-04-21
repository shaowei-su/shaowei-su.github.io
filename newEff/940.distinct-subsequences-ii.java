/*
 * @lc app=leetcode id=940 lang=java
 *
 * [940] Distinct Subsequences II
 *
 * https://leetcode.com/problems/distinct-subsequences-ii/description/
 *
 * algorithms
 * Hard (38.37%)
 * Total Accepted:    4.8K
 * Total Submissions: 12.4K
 * Testcase Example:  '"abc"'
 *
 * Given a string S, count the number of distinct, non-empty subsequences of S
 * .
 * 
 * Since the result may be large, return the answer modulo 10^9 + 7.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: "abc"
 * Output: 7
 * Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac",
 * "bc", and "abc".
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "aba"
 * Output: 6
 * Explanation: The 6 distinct subsequences are "a", "b", "ab", "ba", "aa" and
 * "aba".
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: "aaa"
 * Output: 3
 * Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * S contains only lowercase letters.
 * 1 <= S.length <= 2000
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    int res;
    public int distinctSubseqII(String S) {
        int len = S.length();
        int[] dp = new int[len];
        dp[0] = 1;
        int ret = 1;
        for (int i = 1; i < len; i++) {
            char cur = S.charAt(i);
            int ind = i - 1;
            while (ind >= 0 && S.charAt(ind) != cur) {
                ind--;
            }
            dp[i] = ind < 0 ? 1 : 0;
            ind = ind < 0 ? 0 : ind;
            for (int j = ind; j < i; j++) {
                dp[i] += dp[j];
                dp[i] %= 1000000007;
            }
            ret += dp[i];
            ret %= 1000000007;
        }
        return ret;
    }
    public int distinctSubseqII2(String S) {
        if (S == null || S.length() == 0) {
            return 0;
        }
        dfs(S, new HashSet<String>(), 0, new StringBuilder());
        return res;
    }
    public void dfs(String S, Set<String> subs, int pos, StringBuilder sb) {
        if (sb.length() > 0) {
            subs.add(sb.toString());
            res = subs.size() % 1000000007;
        }
        for (int i = pos; i < S.length(); i++) {
            sb.append(S.charAt(i));
            dfs(S, subs, i + 1, sb);
            sb.setLength(sb.length() - 1);
        }
    }

}
