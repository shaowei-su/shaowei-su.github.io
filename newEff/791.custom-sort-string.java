/*
 * [807] Custom Sort String
 *
 * https://leetcode.com/problems/custom-sort-string/description/
 *
 * algorithms
 * Medium (59.56%)
 * Total Accepted:    20.9K
 * Total Submissions: 35K
 * Testcase Example:  '"cba"\n"abcd"'
 *
 * S and T are strings composed of lowercase letters. In S, no letter occurs
 * more than once.
 * 
 * S was sorted in some custom order previously. We want to permute the
 * characters of T so that they match the order that S was sorted. More
 * specifically, if x occurs before y in S, then x should occur before y in the
 * returned string.
 * 
 * Return any permutation of T (as a string) that satisfies this property.
 * 
 * 
 * Example :
 * Input: 
 * S = "cba"
 * T = "abcd"
 * Output: "cbad"
 * Explanation: 
 * "a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b",
 * and "a". 
 * Since "d" does not appear in S, it can be at any position in T. "dcba",
 * "cdba", "cbda" are also valid outputs.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * S has length at most 26, and no character is repeated in S.
 * T has length at most 200.
 * S and T consist of lowercase letters only.
 * 
 * 
 */
class Solution {
    public String customSortString(String S, String T) {
        int[] charCount = new int[26];
        for (char c : T.toCharArray()) {
            charCount[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            while (charCount[c - 'a']-- > 0) {
                sb.append(c);
            }
        }
        for (int i = 0; i < 26; i++) {
            while (charCount[i]-- > 0) {
                sb.append((char)('a' + i));
            }
        }
        return sb.toString();
    }
    public String customSortString2(String S, String T) {
        if (S == null || T == null || S.length() == 0) {
            return T;
        }
        Map<Character, Integer> weight = new HashMap<>();
        for (int i = 0; i < S.toCharArray().length; i++) {
            weight.put(S.charAt(i), i);
        }
        char[] Tarray = T.toCharArray();
        for (int i = T.length() - 1; i > 0; i--) {
            if (!weight.containsKey(Tarray[i])) {
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (!weight.containsKey(Tarray[j])) {
                    continue;
                }
                if (weight.get(Tarray[j]) > weight.get(Tarray[i])) {
                    swap(Tarray, i, j);
                }
            }
        }
        return new String(Tarray);
    }

    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
