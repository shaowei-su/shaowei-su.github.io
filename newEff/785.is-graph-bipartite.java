/*
 * [801] Is Graph Bipartite?
 *
 * https://leetcode.com/problems/is-graph-bipartite/description/
 *
 * algorithms
 * Medium (40.08%)
 * Total Accepted:    23K
 * Total Submissions: 57K
 * Testcase Example:  '[[1,3],[0,2],[1,3],[0,2]]'
 *
 * Given an undirected graph, return true if and only if it is bipartite.
 * 
 * Recall that a graph is bipartite if we can split it's set of nodes into two
 * independent subsets A and B such that every edge in the graph has one node
 * in A and another node in B.
 * 
 * The graph is given in the following form: graph[i] is a list of indexes j
 * for which the edge between nodes i and j exists.  Each node is an integer
 * between 0 and graph.length - 1.  There are no self edges or parallel edges:
 * graph[i] does not contain i, and it doesn't contain any element twice.
 * 
 * 
 * Example 1:
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Output: true
 * Explanation: 
 * The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 * 
 * 
 * 
 * Example 2:
 * Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * Output: false
 * Explanation: 
 * The graph looks like this:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * We cannot find a way to divide the set of nodes into two independent
 * subsets.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * graph will have length in range [1, 100].
 * graph[i] will contain integers in range [0, graph.length - 1].
 * graph[i] will not contain i or duplicate values.
 * The graph is undirected: if any element j is in graph[i], then i will be in
 * graph[j].
 * 
 * 
 */
class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == 0 && !dfs(graph, colors, 1, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean dfs(int[][] graph, int[] colors, int color, int pos) {
        if (colors[pos] != 0) {
            return colors[pos] == color;
        }
        colors[pos] = color;
        for (int i : graph[pos]) {
            if (!dfs(graph, colors, -color, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isBipartite2(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return true;
        }
        Set<Integer> left = new HashSet<>();
        Set<Integer> right = new HashSet<>();
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(0);
        left.add(0);
        boolean isLeft = true;
        while (!queue.isEmpty() || (left.size() + right.size() < graph.length)) {
            if (queue.isEmpty()) {
                for (int i = 0; i < graph.length; i++) {
                    if (left.contains(i) || right.contains(i)) {
                        continue;
                    }
                    if (graph[i].length == 0) {
                        left.add(i);
                    } else {
                        queue.offer(i);
                        left.add(i);
                        break;
                    }
                }
                if (queue.isEmpty()) {
                    return true;
                }
                isLeft = true;
            }
            int size = queue.size();
            while (size > 0) {
                int cur = queue.poll();
                for (int i : graph[cur]) {
                    if ((isLeft && left.contains(i)) || (!isLeft && right.contains(i))) {
                        return false;
                    }
                    if (left.contains(i) || right.contains(i)) {
                        continue;
                    }
                    if (isLeft) {
                        queue.offer(i);
                        right.add(i);
                    } else {
                        queue.offer(i);
                        left.add(i);
                    }
                }
                size--;
            }
            isLeft = !isLeft;
        }
        return true;
    }
}
