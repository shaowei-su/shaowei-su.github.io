import java.util.*;
public class MinimumVertices {
    // need 3 set: visited so far; start points; current round visited
    public List<Integer> getMin(int[][] edges) {
        Map<Integer, Set<Integer>> nodes = new HashMap<>();
        for (int[] edge : edges) {
            Set<Integer> tmp = nodes.computeIfAbsent(edge[0], s -> new HashSet<>());
            nodes.get(edge[0]).add(edge[1]);
        }
        Set<Integer> res = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        for (int i : nodes.keySet()) {
            if (!visited.contains(i)) {
                res.add(i);
                visited.add(i);
                dfs(res, nodes, i, i, visited, new HashSet<>());
            }
        }
        return new ArrayList<>(res);
    }

    public void dfs(Set<Integer> res, Map<Integer, Set<Integer>> nodes, int cur, int start, 
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

    public static void main(String args[]) {
        MinimumVertices sol = new MinimumVertices();
        int[][] edge1 = {{0, 0}, {1, 2}, {2, 0}, {2, 3}, {3, 1}};
        int[][] edge2 = {{0, 1}, {1, 0}, {2, 1}, {2, 3}, {3, 2}, {4, 1}};
        int[][] edge3 = {{0, 1}, {1, 0}, {2, 1}, {3, 1}, {3, 2}};
        int[][] edge4 = {{2, 9}, {3, 3}, {3, 5}, {3, 7}, {4, 8}, {5, 8}, {6, 6}, {7, 4}, {8, 7}, {9, 3}, {9, 6}};
        System.out.println(sol.getMin(edge1));
        System.out.println(sol.getMin(edge2));
        System.out.println(sol.getMin(edge3));
        System.out.println(sol.getMin(edge4));
    }


}
