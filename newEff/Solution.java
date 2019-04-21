import java.util.*;
/*
 * @lc app=leetcode id=943 lang=java
 *
 * [943] Find the Shortest Superstring
 *
 * https://leetcode.com/problems/find-the-shortest-superstring/description/
 *
 * algorithms
 * Hard (35.79%)
 * Total Accepted:    3.5K
 * Total Submissions: 9.5K
 * Testcase Example:  '["alex","loves","leetcode"]'
 *
 * Given an array A of strings, find any smallest string that contains each
 * string in A as a substring.
 * 
 * We may assume that no string in A is substring of another string in A.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: ["alex","loves","leetcode"]
 * Output: "alexlovesleetcode"
 * Explanation: All permutations of "alex","loves","leetcode" would also be
 * accepted.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
 * Output: "gctaagttcatgcatc"
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= A.length <= 12
 * 1 <= A[i].length <= 20
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    String res = null;
    public String shortestSuperstring(String[] A) {
        dfs(A, new HashSet<String>(), new StringBuilder());
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] A = {"catg","ctaagt","gcta","ttca","atgcatc"};
        sol.shortestSuperstring(A);
    }

    public void dfs(String[] A, Set<String> visited, StringBuilder sb) {
        if (visited.size() == A.length) {
            System.out.println("now sb = " + sb);
            if (res == null || sb.length() < res.length()) {
                System.out.println("cur min = " + sb.length());
                res = sb.toString();
            }
            return;
        }
        for (int i = 0; i < A.length; i++) {
            if (visited.contains(A[i])) {
                continue;
            }
            visited.add(A[i]);
            int k = Math.min(A[i].length(), sb.length());
            while (k > 0) {
                if (sb.substring(sb.length() - k).equals(A[i].substring(0, k))) {
                    break;
                } else {
                    k--;
                }
            }
            String newAi = A[i].substring(k);
            sb.append(newAi);
            dfs(A, visited, sb);
            sb.setLength(sb.length() - newAi.length());
            visited.remove(A[i]);
        }
    }
}
