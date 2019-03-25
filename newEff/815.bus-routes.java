/*
 * @lc app=leetcode id=815 lang=java
 *
 * [815] Bus Routes
 *
 * https://leetcode.com/problems/bus-routes/description/
 *
 * algorithms
 * Hard (37.97%)
 * Total Accepted:    18K
 * Total Submissions: 45.9K
 * Testcase Example:  '[[1,2,7],[3,6,7]]\n1\n6'
 *
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th
 * bus repeats forever. For example if routes[0] = [1, 5, 7], this means that
 * the first bus (0-th indexed) travels in the sequence
 * 1->5->7->1->5->7->1->... forever.
 * 
 * We start at bus stop S (initially not on a bus), and we want to go to bus
 * stop T. Travelling by buses only, what is the least number of buses we must
 * take to reach our destination? Return -1 if it is not possible.
 * 
 * 
 * Example:
 * Input: 
 * routes = [[1, 2, 7], [3, 6, 7]]
 * S = 1
 * T = 6
 * Output: 2
 * Explanation: 
 * The best strategy is take the first bus to the bus stop 7, then take the
 * second bus to the bus stop 6.
 * 
 * 
 * Note: 
 * 
 * 
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 500.
 * 0 <= routes[i][j] < 10 ^ 6.
 * 
 * 
 */
class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) {
            return 0;
        }
        Set<Integer> transfer = new HashSet<>();
        Map<Integer, Set<Integer>> graphMap = convert(routes, transfer);
        // bfs
        int start = graphMap.get(S).size() < graphMap.get(T).size() ? S : T;
        int end = start == S ? T : S;
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(start);
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                if (graphMap.get(cur).contains(end)) {
                    return count;
                }
                for (Integer i : graphMap.get(cur)) {
                    if (visited.contains(i)) {
                        continue;
                    }
                    visited.add(i);
                    if (!transfer.contains(i)) {
                        continue;
                    }
                    queue.offer(i);
                }
            }
            count++;
        }

        return -1;

    }

    public Map<Integer, Set<Integer>> convert(int[][] routes, Set<Integer> transfer) {
        Map<Integer, Set<Integer>> res = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                if (res.containsKey(routes[i][j])) {
                    transfer.add(routes[i][j]);
                }
                Set<Integer> tmp = res.computeIfAbsent(routes[i][j], s -> new HashSet<Integer>());
                for (int k = 0; k < routes[i].length; k++) {
                    if (k == j) {
                        continue;
                    }
                    tmp.add(routes[i][k]);
                }
            }
        }
        return res;
    }
}
