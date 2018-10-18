/*
 * [139] Word Break
 *
 * https://leetcode.com/problems/word-break/description/
 *
 * algorithms
 * Medium (32.76%)
 * Total Accepted:    254.3K
 * Total Submissions: 776.3K
 * Testcase Example:  '"leetcode"\n["leet","code"]'
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, determine if s can be segmented into a space-separated
 * sequence of one or more dictionary words.
 * 
 * Note:
 * 
 * 
 * The same word in the dictionary may be reused multiple times in the
 * segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet
 * code".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple
 * pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 * 
 * 
 */
class Solution {
    Set<String> set;

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (String word : wordDict) {
                if (i - word.length() >= 0 && dp[i - word.length()] && s.substring(i - word.length(), i).equals(word)) {
                    dp[i] = true;
                    continue;
                }
            }
        }
        return dp[s.length()];
    }

    public boolean wordBreak3(String s, List<String> wordDict) {
        set = new HashSet<>(wordDict);
        return wordBreakS(s);
    }
    public boolean wordBreakS(String s) {
        if (s.length() == 0) {
            return true;
        }
        for (int i = 1; i <= s.length(); i++) {
            if (set.contains(s.substring(0, i)) && wordBreakS(s.substring(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        return dfs(s, "", wordDict);           
    }
    public boolean dfs(String s, String cur, List<String> wordDict) {
        if (s.equals(cur)) {
            return true;
        }
        if (cur.length() >= s.length()) {
            return false;
        }
        for (String word : wordDict) {
            if (s.contains(word) && s.startsWith(cur + word) && dfs(s, cur + word, wordDict)) {
                return true;
            }
        }
        return false;
    }
}
