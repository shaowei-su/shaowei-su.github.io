/*
 * @lc app=leetcode id=787 lang=java
 *
 * [787] Cheapest Flights Within K Stops
 *
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
 *
 * algorithms
 * Medium (32.99%)
 * Total Accepted:    31.9K
 * Total Submissions: 93.9K
 * Testcase Example:  '3\n[[0,1,100],[1,2,100],[0,2,500]]\n0\n2\n1'
 *
 * There are n cities connected by m flights. Each fight starts from city u and
 * arrives at v with a price w.
 * 
 * Now given all the cities and flights, together with starting city src and
 * the destination dst, your task is to find the cheapest price from src to dst
 * with up to k stops. If there is no such route, output -1.
 * 
 * 
 * Example 1:
 * Input: 
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation: 
 * The graph looks like this:
 * 
 * 
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as
 * marked red in the picture.
 * 
 * 
 * Example 2:
 * Input: 
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation: 
 * The graph looks like this:
 * 
 * 
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as
 * marked blue in the picture.
 * 
 * Note:
 * 
 * 
 * The number of nodes n will be in range [1, 100], with nodes labeled from 0
 * to n - 1.
 * The size of flights will be in range [0, n * (n - 1) / 2].
 * The format of each flight will be (src, dst, price).
 * The price of each flight will be in the range [1, 10000].
 * k is in the range of [0, n - 1].
 * There will not be any duplicated flights or self cycles.
 * 
 * 
 */
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[K + 2][n];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dp[0][src] = 0;
        for (int i = 1; i < K + 2; i++) {
            dp[i][src] = 0;
            for (int[] f : flights) {
                dp[i][f[1]] = Math.min(dp[i][f[1]], dp[i - 1][f[0]] + f[2]);
            }
        }

        return dp[K + 1][dst] == Integer.MAX_VALUE ? -1 : dp[K + 1][dst];


    }

    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        convert(graph, flights);
        int count = 0;
        int curMin = Integer.MAX_VALUE;
        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0}); //node, cost
        Map<Integer, Integer> nodeBest = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nodeBest.put(i, Integer.MAX_VALUE);
        }
        while (count <= K) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                if (!graph.containsKey(cur[0])) {
                    continue;
                }
                for (int[] neighbor : graph.get(cur[0])) {
                    if (cur[1] + neighbor[1] < nodeBest.get(neighbor[0])) {
                        nodeBest.put(neighbor[0], cur[1] + neighbor[1]);
                        queue.offer(new int[] {neighbor[0], cur[1] + neighbor[1]});
                    }
                    if (neighbor[0] == dst) {
                        curMin = cur[1] + neighbor[1] < curMin ? cur[1] + neighbor[1] : curMin;
                    }
                }
            }
            count++;
        }
        return curMin == Integer.MAX_VALUE ? -1 : curMin;
    }

    public void convert(Map<Integer, List<int[]>> graph, int[][] flights) {
        for (int[] f : flights) {
            List<int[]> nei = graph.computeIfAbsent(f[0], l -> new ArrayList<>());
            nei.add(new int[] {f[1], f[2]});
        }
    }
}
