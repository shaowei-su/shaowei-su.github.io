/*
 * [800] Letter Case Permutation
 *
 * https://leetcode.com/problems/letter-case-permutation/description/
 *
 * algorithms
 * Easy (53.51%)
 * Total Accepted:    28.8K
 * Total Submissions: 53.7K
 * Testcase Example:  '"a1b2"'
 *
 * Given a string S, we can transform every letter individually to be lowercase
 * or uppercase to create another string.  Return a list of all possible
 * strings we could create.
 * 
 * 
 * Examples:
 * Input: S = "a1b2"
 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 * 
 * Input: S = "3z4"
 * Output: ["3z4", "3Z4"]
 * 
 * Input: S = "12345"
 * Output: ["12345"]
 * 
 * 
 * Note:
 * 
 * 
 * S will be a string with length between 1 and 12.
 * S will consist only of letters or digits.
 * 
 * 
 */
class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        if (S == null) {
            return res;
        }
        StringBuilder sb = new StringBuilder();
        dfs(S, res, sb);
        return res;
    }
    public void dfs(String S, List<String> res, StringBuilder sb) {
        if (sb.length() == S.length()) {
            res.add(sb.toString());
            return;
        }
        char cur = S.charAt(sb.length());
        if (Character.isDigit(cur)) {
            sb.append(cur);
            dfs(S, res, sb);
            sb.setLength(sb.length() - 1);
        } else {
            sb.append(cur);
            dfs(S, res, sb);
            sb.setLength(sb.length() - 1);
            if ('a' <= cur && cur <= 'z') {
                sb.append((char)(cur - 32));
            } else {
                sb.append((char)(cur + 32));
            }
            dfs(S, res, sb);
            sb.setLength(sb.length() - 1);
        }
    }

}
