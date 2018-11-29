/*
 * [686] Repeated String Match
 *
 * https://leetcode.com/problems/repeated-string-match/description/
 *
 * algorithms
 * Easy (31.31%)
 * Total Accepted:    51K
 * Total Submissions: 162.8K
 * Testcase Example:  '"abcd"\n"cdabcdab"'
 *
 * Given two strings A and B, find the minimum number of times A has to be
 * repeated such that B is a substring of it. If no such solution, return -1.
 * 
 * For example, with A = "abcd" and B = "cdabcdab".
 * 
 * Return 3, because by repeating A three times (“abcdabcdabcd”), B is a
 * substring of it; and B is not a substring of A repeated two times
 * ("abcdabcd").
 * 
 * Note:
 * The length of A and B will be between 1 and 10000.
 * 
 */
class Solution {
    public int repeatedStringMatch(String A, String B) {
        if (A == null || B == null) {
            return -1;
        }
        int start = B.length() / A.length();
        if (B.length() % A.length() != 0) {
            start++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < start; i++) {
            sb.append(A);
        }
        String cur = sb.toString();
        if (cur.contains(B)) {
            return start;
        }
        if ((A + cur).contains(B)) {
            return start + 1;
        }
        if ((cur + A).contains(B)) {
            return start + 1;
        }
        if ((A + cur + A).contains(B)) {
            return start + 2;
        }
        return -1;
    }
}
