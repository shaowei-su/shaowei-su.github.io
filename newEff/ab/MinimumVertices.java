public class MinimumVertices {

    public List<String> getMin(int[][] edges, int n) {
        Map<Integer, Set<Integer>> nodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nodes.put(i, new HashSet<Integer>());
        }
        for (int[] edge : edges) {
            nodes.get(edge[0]).add(edge[1]);
        }
        Set<String> res = new HashSet<>();
        Set<String> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                res.add(i);
                visited.add(i);
                dfs(res, nodes, i, i, visited, new HashSet<>());
            }
        }
        return new ArrayList<>(res);
    }

    public void dfs(Set<String> res, Map<Integer, Set<Integer>> nodes, int cur, int start, 
            Set<Integer> visited, Set<Integer> curVisited) {
        curVisited.add(cur);
        visited.add(cur);
        for (Integer next : nodes.get(cur)) {
            if (res.contains(next) && next != start)   {
                res.remove(next);
            }
            if (!curVisited.contains(next)) {
                dfs(res, nodes, next, start, visited, curVisited);
            }
        }
    }



}
