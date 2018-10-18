/*
 * [323] Number of Connected Components in an Undirected Graph
 *
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/
 *
 * algorithms
 * Medium (49.63%)
 * Total Accepted:    43K
 * Total Submissions: 86.6K
 * Testcase Example:  '5\n[[0,1],[1,2],[3,4]]'
 *
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each
 * edge is a pair of nodes), write a function to find the number of connected
 * components in an undirected graph.
 * 
 * Example 1:
 * 
 * 
 * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
 * 
 * ⁠    0          3
 * ⁠    |          |
 * ⁠    1 --- 2    4 
 * 
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
 * 
 * ⁠    0           4
 * ⁠    |           |
 * ⁠    1 --- 2 --- 3
 * 
 * Output:  1
 * 
 * 
 * Note:
 * You can assume that no duplicate edges will appear in edges. Since all edges
 * are undirected, [0, 1] is the same as [1, 0] and thus will not appear
 * together in edges.
 * 
 */
class Solution {
    public int countComponents2(int n, int[][] edges) {
       if (n < 1) {
           return 0;
       }
       if (edges == null || edges.length == 0 || edges[0].length == 0) {
           return n;
       }
       Map<Integer, Set<Integer>> node2Set = new HashMap<>();
       for (int[] edge : edges) {
           Set<Integer> left = node2Set.computeIfAbsent(edge[0], s -> new HashSet<Integer>());
           Set<Integer> right = node2Set.computeIfAbsent(edge[1], s -> new HashSet<Integer>());
           left.add(edge[1]);
           right.add(edge[0]);
       }
       boolean[] visited = new boolean[n];
       Deque<Integer> queue = new LinkedList<>();
       int count = 0;
       for (int i = 0; i < n; i++) {
           if (visited[i]) {
               continue;
           }
           count++;
           visited[i] = true;
           queue.offer(i);
           while (queue.size() > 0) {
               Integer cur = queue.poll();
               if (node2Set.get(cur) == null) {
                   break;
               }
               for (Integer nei : node2Set.get(cur)) {
                   if (!visited[nei]) {

                        visited[nei] = true;
                        queue.offer(nei);
                   }
               }
           }
       }
       return count;

    }

     public int countComponents(int n, int[][] edges) {
        if (n < 1) {
            return 0;
        }
        if (edges == null || edges.length == 0 || edges[0].length == 0) {
            return n;
        }
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int count = n;
        for (int[] edge : edges) {
            int p1 = findParent(edge[0], parent);
            int p2 = findParent(edge[1], parent);
            if (p1 != p2) {
                parent[p1] = p2;
                count--;
            }
        }
        return count;
     }


    public int findParent(int i, int[] parent) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
}
