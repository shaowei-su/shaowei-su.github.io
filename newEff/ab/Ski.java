import java.util.*;
public class Ski {

    private Map<String, Map<String, Integer>> graph;
    private Map<String, Integer> rewardMap;
    private Map<String, Integer> scoreMap;
    int globalMax = Integer.MIN_VALUE;

    public Ski(List<String> inputGraph, List<String> inputReward, Set<String> ends, String start) {
        graph = new HashMap<>();
        rewardMap = new HashMap<>();
        scoreMap = new HashMap<>();
        for (String g : inputGraph) {
            Map<String, Integer> neighbors = graph.computeIfAbsent(g.split(",")[0], m -> new HashMap<>());
            neighbors.put(g.split(",")[1], Integer.valueOf(g.split(",")[2]));
        }
        for (String r : inputReward) {
            rewardMap.put(r.split(",")[0], Integer.valueOf(r.split(",")[1]));
            scoreMap.put(r.split(",")[0], 0);
        }
        dfs(start, ends, rewardMap.get(start));
    }

    public void dfs(String cur, Set<String> ends, int score) {
        if (ends.contains(cur)) {
            globalMax = Math.max(globalMax, score);
            return;
        }
        if (!graph.containsKey(cur)) {
            return;
        }
        for (Map.Entry<String, Integer> nei : graph.get(cur).entrySet()) {
            if (score + rewardMap.get(nei.getKey()) + nei.getValue() <= scoreMap.get(nei.getKey())) {
                continue;
            }
            scoreMap.put(nei.getKey(), score + rewardMap.get(nei.getKey()) + nei.getValue());
            dfs(nei.getKey(), ends, score + rewardMap.get(nei.getKey()) + nei.getValue());
        }
    }

    public int tp(List<String> inputGraph, Set<String> ends, String start) {
        Map<String, Integer> indegree = new HashMap<>();
        Map<String, Integer> sMap = new HashMap<>();
        for (String g : inputGraph) {
            String prev = g.split(",")[0];
            String cur = g.split(",")[1];
            sMap.put(prev, 0);
            sMap.put(cur, 0);
            indegree.put(cur, indegree.getOrDefault(cur, 0) + 1);
        }
        Deque<String> queue = new LinkedList<>();
        queue.offer(start);
        sMap.put("A", 5);
        int localMax = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (ends.contains(cur)) {
                localMax = Math.max(localMax, sMap.get(cur));
                continue;
            }
            if (!graph.containsKey(cur)) {
                continue;
            }
            for (Map.Entry<String, Integer> nei : graph.get(cur).entrySet()) {
                sMap.put(nei.getKey(), Math.max(sMap.get(nei.getKey()), sMap.get(cur) + rewardMap.get(nei.getKey()) + nei.getValue()));
                indegree.put(nei.getKey(), indegree.get(nei.getKey()) - 1);
                if (indegree.get(nei.getKey()) == 0) {
                    queue.offer(nei.getKey());
                }
            }
        }
        return localMax;

    }

    public static void main(String[] args) {
        List<String> inputGraph = new ArrayList<>(Arrays.asList("A,B,2","A,C,3","B,D,5","B,E,6","C,E,4","D,H,7","E,H,6","H,I,1","H,J,2","F,J,3"));
        List<String> inputReward = new ArrayList<>(Arrays.asList("A,5", "B,7", "C,6", "D,2", "E,4", "F,7", "H,7", "I,3", "J,2"));
        Set<String> ends = new HashSet<>(Arrays.asList("I", "J"));
        Ski s = new Ski(inputGraph, inputReward, ends, "A");
        System.out.println("max = " + s.globalMax);
        System.out.println("localMax = " + s.tp(inputGraph, ends, "A"));
    }

}
