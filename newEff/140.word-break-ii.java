/*
 * @lc app=leetcode id=140 lang=java
 *
 * [140] Word Break II
 *
 * https://leetcode.com/problems/word-break-ii/description/
 *
 * algorithms
 * Hard (26.30%)
 * Total Accepted:    147K
 * Total Submissions: 552.4K
 * Testcase Example:  '"catsanddog"\n["cat","cats","and","sand","dog"]'
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, add spaces in s to construct a sentence where each word is
 * a valid dictionary word. Return all such possible sentences.
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
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 * 
 */
class Solution {

    public boolean canBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            dp[i] = false;
            for (String word : wordDict) {
                if (i - word.length() >= 0 && dp[i - word.length()] && s.substring(i - word.length(), i).equals(word)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();        
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return res;
        }
        if (!canBreak(s, wordDict)) {
            return res;
        }
        dfs(s, "", new StringBuilder(), res, wordDict);
        return res;
    }
    public void dfs(String s, String cur, StringBuilder sb, List<String> res, List<String> wordDict) {
        if (cur.length() > s.length()) {
            return;
        }
        if (s.equals(cur)) {
            res.add(sb.toString().substring(1));
            return;
        }
        for (String word : wordDict) {
            if (s.startsWith(cur + word)) {
                dfs(s, cur + word, sb.append(" " + word), res, wordDict);
                sb.setLength(sb.length() - 1 - word.length());
            }
            
        }
    }
}
