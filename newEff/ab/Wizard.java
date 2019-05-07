import java.util.*;

/*
 *
 * hard to actually find the path, which is record whenever possible
 *
 */
public class Wizard {
public List<Integer> getShortestPath(List<List<Integer>> wizards, int source, int target, int n) {
    int[] parent = new int[n];
    int[] dist = new int[n];
    for (int i = 0; i < n; i++) {
        parent[i] = i;
        if (i != source) {
            dist[i] = Integer.MAX_VALUE;
        }
    }
    Deque<Integer> queue = new LinkedList<>();
    queue.offer(source);
    while (queue.size() > 0) {
        int cur = queue.poll();
        if (cur >= n - 1) {
            continue;
        }
        for (int nei : wizards.get(cur)) {
            int weight = (int) Math.pow(nei - cur, 2);
            if (dist[cur] + weight < dist[nei]) {
                dist[nei] = dist[cur] + weight;
                parent[nei] = cur;
                queue.offer(nei);
            }
        }
    }
    List<Integer> path = new ArrayList<>();
    int t = target;
    while (t != source) {
        path.add(t);
        t = parent[t];
    }
    path.add(source);
    Collections.reverse(path);
    return path;
}

    
    public List<Integer> getShortestDijkstra(List<List<Integer>> wizards, int source, int target) {
        List<Integer> path = new ArrayList<>();
        int n = wizards.size();
        Route[] from = new Route[n + 1];

        PriorityQueue<Route> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        for (Integer next : wizards.get(source)) {
            pq.offer(new Route(next, source, (next - source) * (next - source)));
        }
        while (!pq.isEmpty()) {
            Route cur = pq.poll();
            if (from[cur.wizard] != null) {
                continue;
            }
            from[cur.wizard] = cur;
            if (cur.wizard == target) {
                getPath(from, source, target, path);
                return path;
            }
            for (int next : wizards.get(cur.wizard)) {
                pq.offer(new Route(next, cur.wizard, (cur.wizard - next) * (cur.wizard - next)));
            }
        }

        return path;
    }

    public void getPath(Route[] from, int source, int target, List<Integer> path) {
        int wizard = target;
        while (wizard != source) {
            path.add(wizard);
            wizard = from[wizard].from;
        }
        path.add(source);
        Collections.reverse(path);
    }




    public static void main(String[] args) {
        Wizard w = new Wizard();
        List<List<Integer>> wizards = new ArrayList<>();
        wizards.add(new ArrayList<>(Arrays.asList(1, 2)));
        wizards.add(new ArrayList<>(Arrays.asList(3)));
        wizards.add(new ArrayList<>(Arrays.asList(3, 4)));
        wizards.add(new ArrayList<>(Arrays.asList(4)));
        System.out.println(w.getShortestPath(wizards, 0, 4, 5));
        System.out.println(w.getShortestDijkstra(wizards, 0, 4));

    }
}

class Route {
    int wizard;
    int from;
    int cost;
    public Route(int wizard, int from, int cost) {
        this.wizard = wizard;
        this.from = from;
        this.cost = cost;
    }
}


