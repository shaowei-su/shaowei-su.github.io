/*
 * @lc app=leetcode id=756 lang=java
 *
 * [756] Pyramid Transition Matrix
 *
 * https://leetcode.com/problems/pyramid-transition-matrix/description/
 *
 * algorithms
 * Medium (50.72%)
 * Total Accepted:    12.4K
 * Total Submissions: 24.3K
 * Testcase Example:  '"ABC"\n["ABD","BCE","DEF","FFF"]'
 *
 * 
 * We are stacking blocks to form a pyramid.  Each block has a color which is a
 * one letter string, like `'Z'`.
 * 
 * For every block of color `C` we place not in the bottom row, we are placing
 * it on top of a left block of color `A` and right block of color `B`.  We are
 * allowed to place the block there only if `(A, B, C)` is an allowed triple.
 * 
 * We start with a bottom row of bottom, represented as a single string.  We
 * also start with a list of allowed triples allowed.  Each allowed triple is
 * represented as a string of length 3.
 * 
 * Return true if we can build the pyramid all the way to the top, otherwise
 * false.
 * 
 * 
 * Example 1:
 * 
 * Input: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"]
 * Output: true
 * Explanation:
 * We can stack the pyramid like this:
 * ⁠   A
 * ⁠  / \
 * ⁠ D   E
 * ⁠/ \ / \
 * X   Y   Z
 * 
 * This works because ('X', 'Y', 'D'), ('Y', 'Z', 'E'), and ('D', 'E', 'A') are
 * allowed triples.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: bottom = "XXYX", allowed = ["XXX", "XXY", "XYX", "XYY", "YXZ"]
 * Output: false
 * Explanation:
 * We can't stack the pyramid to the top.
 * Note that there could be allowed triples (A, B, C) and (A, B, D) with C !=
 * D.
 * 
 * 
 * 
 * Note:
 * 
 * bottom will be a string with length in range [2, 8].
 * allowed will have length in range [0, 200].
 * Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E',
 * 'F', 'G'}.
 * 
 * 
 */
class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        if (bottom == null || bottom.length() == 0) {
            return false;
        }
        if (bottom.length() == 1) {
            return true;
        }
        List<List<String>> perms = new ArrayList<>();
        for (int i = 0; i < bottom.length() - 1; i++) {
            List<String> tmp = new ArrayList<>();
            String cur = bottom.substring(i, i + 2);
            for (String a : allowed) {
                if (a.startsWith(cur)) {
                    tmp.add(a.substring(2));
                }
            }
            if (tmp.size() == 0) {
                return false;
            }
            perms.add(tmp);
        }
        List<String> permRes = permute(perms);
        for (String p : permRes) {
            if (pyramidTransition(p, allowed)) {
                return true;
            }
        }
        return false;
    }
    private List<String> permute(List<List<String>> perms) {
        List<String> res = new ArrayList<>();
        dfs(res, perms, 0, new StringBuilder());
        return res;
    }
    private void dfs(List<String> res, List<List<String>> perms, int pos, StringBuilder sb) {
        if (pos == perms.size()) {
            res.add(sb.toString());
            return;
        }
        for (String s : perms.get(pos)) {
            sb.append(s);
            dfs(res, perms, pos + 1, sb);
            sb.setLength(sb.length() - 1);
        }
    }
}
