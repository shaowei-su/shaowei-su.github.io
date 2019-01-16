/*
 * [399] Evaluate Division
 *
 * https://leetcode.com/problems/evaluate-division/description/
 *
 * algorithms
 * Medium (45.57%)
 * Total Accepted:    60.2K
 * Total Submissions: 131.9K
 * Testcase Example:  '[ ["a","b"],["b","c"] ]\n[2.0,3.0]\n[ ["a","c"],["b","c"],["a","e"],["a","a"],["x","x"] ]'
 *
 * 
 * Equations are given in the format A / B = k, where  A and B are variables
 * represented as strings, and k is a real number (floating point number).
 * Given some queries, return the answers. If the answer does not exist, return
 * -1.0.
 * 
 * Example:
 * Given  a / b = 2.0, b / c = 3.0. queries are:  a / c = ?,  b / a = ?, a / e
 * = ?,  a / a = ?, x / x = ? . return  [6.0, 0.5, -1.0, 1.0, -1.0 ].
 * 
 * 
 * The input is:  vector<pair<string, string>> equations, vector<double>&
 * values, vector<pair<string, string>> queries , where equations.size() ==
 * values.size(), and the values are positive. This represents the equations.
 * Return  vector<double>.
 * 
 * 
 * According to the example above:
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"]
 * ]. 
 * 
 * 
 * 
 * The input is always valid. You may assume that evaluating the queries will
 * result in no division by zero and there is no contradiction.
 * 
 */
class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        // 1. convert to graph
        Map<String, Map<String, Double>> graph = convert(equations, values);
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = eval(graph, queries[i]);
        }
        return res;
    }

    public Map<String, Map<String, Double>> convert(String[][] equations, double[] values) {
        Map<String, Map<String, Double>> res = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            Map<String, Double> left = res.computeIfAbsent(equations[i][0], m -> new HashMap<>());
            Map<String, Double> right = res.computeIfAbsent(equations[i][1], m -> new HashMap<>());
            left.put(equations[i][1], new Double(values[i]));
            right.put(equations[i][0], new Double(1.0d / values[i]));
        }
        return res;
    }

    public double eval(Map<String, Map<String, Double>> graph, String[] query) {

        if (!graph.containsKey(query[0])) {
            return -1.0d;
        }

        if (!graph.containsKey(query[1])) {
            return -1.0d;
        }
        
        if (query[0].equals(query[1])) {
            return 1.0d;
        }
        ArrayList<Double> res =  new ArrayList<Double>();
        Set<String> visited = new HashSet<>();
        visited.add(query[0]);
        dfs(query[0], graph, query[1], res, 1.0d, visited);
        return res.size() > 0 ? res.get(0) : -1.0d;

    }

    public void dfs(String cur, Map<String, Map<String, Double>> graph, String dest, ArrayList<Double> res, double temp, Set<String> visited) {
        if (cur.equals(dest)) {
            res.add(temp);
            return;
        }
        for (String nei : graph.get(cur).keySet()) {
            if (visited.contains(nei)) {
                continue;
            }
            visited.add(nei);
            dfs(nei, graph, dest, res, temp * graph.get(cur).get(nei), visited);
        }
    }
        
}
