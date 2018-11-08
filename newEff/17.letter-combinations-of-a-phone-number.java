/*
 * [17] Letter Combinations of a Phone Number
 *
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 *
 * algorithms
 * Medium (38.55%)
 * Total Accepted:    295K
 * Total Submissions: 764.5K
 * Testcase Example:  '"23"'
 *
 * Given a string containing digits from 2-9 inclusive, return all possible
 * letter combinations that the number could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below. Note that 1 does not map to any letters.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * 
 * Note:
 * 
 * Although the above answer is in lexicographical order, your answer could be
 * in any order you want.
 * 
 */
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;

        Map<Character, String[]> charMap = new HashMap<>();
        charMap.put('2', new String[]{"a", "b", "c"});
        charMap.put('3', new String[]{"d", "e", "f"});
        charMap.put('4', new String[]{"g", "h", "i"});
        charMap.put('5', new String[]{"j", "k", "l"});
        charMap.put('6', new String[]{"m", "n", "o"});
        charMap.put('7', new String[]{"p", "q", "r", "s"});
        charMap.put('8', new String[]{"t", "u", "v"});
        charMap.put('9', new String[]{"w", "x", "y", "z"});

        StringBuilder sb = new StringBuilder();
        dfs(res, sb, charMap, 0, digits);
        return res;
    }
    public void dfs(List<String> res, StringBuilder sb, Map<Character, String[]> charMap, int pos, String digits) {
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }
        for (String s : charMap.get(digits.charAt(pos))) {
            sb.append(s);
            dfs(res, sb, charMap, pos + 1, digits);
            sb.setLength(sb.length() - 1);
        }
    }
}
