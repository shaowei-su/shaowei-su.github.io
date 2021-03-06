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

        Map<String, Map<String, Integer>> neighbors = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            Map<String, Integer> neiMap = neighbors.computeIfAbsent(A[i], m -> new HashMap<>());
            for (int j = 0; j < A.length; j++) {
                if (i == j) {
                    continue;
                }
                int k = Math.min(A[i].length(), A[j].length());
                while (k > 0) {
                    if (A[i].substring(A[i].length() - k).equals(A[j].substring(0, k))) {
                        break;
                    } else {
                        k--;
                    }
                }
                neiMap.put(A[j], k);
            }
        }

        dfs(A, neighbors, new HashSet<String>(), new StringBuilder(), "");

        return res;
    }

    public void dfs(String[] A, Map<String, Map<String, Integer>> neighbors, Set<String> visited, StringBuilder sb, String last) {
        if (visited.size() == A.length) {
            if (res == null || sb.length() < res.length()) {
                res = sb.toString();
            }
            return;
        }
        for (int i = 0; i < A.length; i++) {
            if (visited.contains(A[i])) {
                continue;
            }
            visited.add(A[i]);
            Integer k = 0;
            if (last.length() > 0) {
                k = neighbors.get(last).get(A[i]);
            }
            String newAi = A[i].substring(k);
            sb.append(newAi);
            dfs(A, neighbors, visited, sb, A[i]);
            sb.setLength(sb.length() - newAi.length());
            visited.remove(A[i]);
        }
    }
}
