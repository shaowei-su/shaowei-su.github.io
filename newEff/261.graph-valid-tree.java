/*
 * [261] Graph Valid Tree
 *
 * https://leetcode.com/problems/graph-valid-tree/description/
 *
 * algorithms
 * Medium (38.80%)
 * Total Accepted:    67K
 * Total Submissions: 172.8K
 * Testcase Example:  '5\n[[0,1],[0,2],[0,3],[1,4]]'
 *
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each
 * edge is a pair of nodes), write a function to check whether these edges make
 * up a valid tree.
 * 
 * Example 1:
 * 
 * 
 * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 * 
 * Example 2:
 * 
 * 
 * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 * 
 * Note: you can assume that no duplicate edges will appear in edges. Since all
 * edges are undirected, [0,1] is the same as [1,0] and thus will not appear
 * together in edges.
 * 
 */
class Solution {
    public boolean validTree2(int n, int[][] edges) {
        if (n <= 0) {
            return true;
        }
        if (n == 1 && edges.length == 0) {
            return true;
        }
        if (edges.length == 0 || edges[0].length == 0) {
            return false;
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        LinkedList<Integer> queue = new LinkedList<>();
        for (int[] edge : edges) {
            Set<Integer> conn1 = map.computeIfAbsent(edge[0], m -> new HashSet<Integer>());
            conn1.add(edge[1]);
            Set<Integer> conn2 = map.computeIfAbsent(edge[1], m -> new HashSet<Integer>());
            conn2.add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        Integer last = -1;
        while (queue.size() > 0) {
            Integer cur = queue.poll();
            visited.add(cur);
            if (map.get(cur) == null) continue;
            for (Integer i : map.get(cur)) {
                if (i != last && visited.contains(i)) {
                    return false;
                } else if (i != last) {
                    queue.offer(i);
                }
            }
            last = cur;
        }
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                return false;
            }
        }
        return true;
    }
    public boolean validTree3(int n, int[][] edges) {
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        for (int[] edge : edges) {
            int x = find(parent, edge[0]);
            int y = find(parent, edge[1]);
            if (x == y) return false;
            parent[x] = y;
        }
        return edges.length == n - 1;
    }
    public int find(int[] parent, int cur) {
        if (parent[cur] == -1) return cur;
        parent[cur] = find(parent, parent[cur]);
        return parent[cur];
    }
    public boolean validTree4(int n, int[][] edges) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int[] edge : edges) {
            int x = find2(parent, edge[0]);
            int y = find2(parent, edge[1]);
            if (x == y) return false;
            parent[x] = y;
        }
        return edges.length == n - 1;
    }
    public int find2(int[] parent, int cur) {
        while (parent[cur] != cur) {
            parent[cur] = parent[parent[cur]];
            cur = parent[cur];
        }
        return cur;
    }

    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
             graph.add(i, new ArrayList<Integer>());
         }
         for (int[] edge : edges) {
             graph.get(edge[0]).add(edge[1]);
             graph.get(edge[1]).add(edge[0]);
         }
         boolean[] visited = new boolean[n];
         if (hasCircle(visited, graph, 0, -1)) {
             return false;
         }
         for (int i = 0; i < n; i++) {
             if (!visited[i]) {
                 return false;
             }
         }
         return true;
    }
    public boolean hasCircle(boolean[] visited, List<List<Integer>> graph, int cur, int last) {
        visited[cur] = true;
        for (Integer i : graph.get(cur)) {
            if ((i != last && visited[i]) || (!visited[i] && hasCircle(visited, graph, i, cur))) {
                return true;
            }
        }
        return false;
    }

}
