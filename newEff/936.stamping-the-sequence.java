/*
 * @lc app=leetcode id=936 lang=java
 *
 * [936] Stamping The Sequence
 *
 * https://leetcode.com/problems/stamping-the-sequence/description/
 *
 * algorithms
 * Hard (35.72%)
 * Total Accepted:    2.8K
 * Total Submissions: 7.8K
 * Testcase Example:  '"abc"\n"ababc"'
 *
 * You want to form a target string of lowercase letters.
 * 
 * At the beginning, your sequence is target.length '?' marks.  You also have a
 * stamp of lowercase letters.
 * 
 * On each turn, you may place the stamp over the sequence, and replace every
 * letter in the sequence with the corresponding letter from the stamp.  You
 * can make up to 10 * target.length turns.
 * 
 * For example, if the initial sequence is "?????", and your stamp is "abc",
 * then you may make "abc??", "?abc?", "??abc" in the first turn.  (Note that
 * the stamp must be fully contained in the boundaries of the sequence in order
 * to stamp.)
 * 
 * If the sequence is possible to stamp, then return an array of the index of
 * the left-most letter being stamped at each turn.  If the sequence is not
 * possible to stamp, return an empty array.
 * 
 * For example, if the sequence is "ababc", and the stamp is "abc", then we
 * could return the answer [0, 2], corresponding to the moves "?????" ->
 * "abc??" -> "ababc".
 * 
 * Also, if the sequence is possible to stamp, it is guaranteed it is possible
 * to stamp within 10 * target.length moves.  Any answers specifying more than
 * this number of moves will not be accepted.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: stamp = "abc", target = "ababc"
 * Output: [0,2]
 * ([1,0,2] would also be accepted as an answer, as well as some other
 * answers.)
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: stamp = "abca", target = "aabcaca"
 * Output: [3,0,1]
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 
 * 
 * 1 <= stamp.length <= target.length <= 1000
 * stamp and target only contain lowercase letters.
 * 
 */
class Solution {
    public int[] movesToStamp(String stamp, String target) {
        if (target.length() < stamp.length()) {
            return new int[0];
        }
        List<Integer> steps = new ArrayList<>();
        char[] cur = target.toCharArray();
        char[] scur = stamp.toCharArray();
        boolean[] visited = new boolean[target.length()];
        while (!finish(cur)) {
            int first = findFirst(cur, scur, visited);
            if (first < 0) {
                return new int[0];
            }
            for (int i = 0; i < stamp.length(); i++) {
                cur[i + first] = '?';
            }
            steps.add(first);
        }
        int[] res = new int[steps.size()];
        for (int i = steps.size() - 1; i >= 0; i--) {
            res[res.length - i - 1] = steps.get(i);
        }
        return res;
    }
    public int findFirst(char[] cur, char[] scur, boolean[] visited) {
        for (int i = 0; i <= cur.length - scur.length; i++) {
            if (visited[i]) {
                continue;
            }
            boolean diff = false;
            for (int j = 0; j < scur.length; j++) {
                if (cur[i + j] != scur[j] && cur[i + j] != '?') {
                    diff = true;
                    break;
                }
            }
            if (!diff) {
                visited[i] = true;
                return i;
            }
        }
        return -1;
    }

    public boolean finish(char[] cur) {
        for (char c : cur) {
            if (c != '?') {
                return false;
            }
        }
        return true;
    }

}
