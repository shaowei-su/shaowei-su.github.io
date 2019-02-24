/*
 * @lc app=leetcode id=207 lang=java
 *
 * [207] Course Schedule
 *
 * https://leetcode.com/problems/course-schedule/description/
 *
 * algorithms
 * Medium (36.03%)
 * Total Accepted:    185.2K
 * Total Submissions: 507.2K
 * Testcase Example:  '2\n[[1,0]]'
 *
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have
 * to first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it
 * possible for you to finish all courses?
 * 
 * Example 1:
 * 
 * 
 * Input: 2, [[1,0]] 
 * Output: true
 * Explanation: There are a total of 2 courses to take. 
 * To take course 1 you should have finished course 0. So it is possible.
 * 
 * Example 2:
 * 
 * 
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take. 
 * To take course 1 you should have finished course 0, and to take course 0 you
 * should
 * also have finished course 1. So it is impossible.
 * 
 * 
 * Note:
 * 
 * 
 * The input prerequisites is a graph represented by a list of edges, not
 * adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input
 * prerequisites.
 * 
 * 
 */
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return false;
        }
        Map<Integer, Integer> depCount = new HashMap<>();
        Map<Integer, Set<Integer>> dep = new HashMap<>();
        for (int[] p : prerequisites) {
            dep.computeIfAbsent(p[1], s -> new HashSet<>()).add(p[0]);
            depCount.put(p[0], depCount.getOrDefault(p[0], 0) + 1);
        }
        Deque<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < numCourses; i++) {
            if (!depCount.containsKey(i)) {
                queue.offer(i);
                count++;
            }
        }
        while (queue.size() > 0) {
            int cur = queue.poll();
            if (!dep.containsKey(cur)) {
                continue;
            }
            for (Integer nei : dep.get(cur)) {
                if (depCount.get(nei) == 1) {
                    queue.offer(nei);
                    count++;
                } else {
                    depCount.put(nei, depCount.get(nei) - 1);
                }
            }
        }
        return count == numCourses;
    }
}
