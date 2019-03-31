import java.util.*;

/*
 * @lc app=leetcode id=465 lang=java
 *
 * [465] Optimal Account Balancing
 *
 * https://leetcode.com/problems/optimal-account-balancing/description/
 *
 * algorithms
 * Hard (42.16%)
 * Total Accepted:    14.6K
 * Total Submissions: 34.7K
 * Testcase Example:  '[[0,1,10],[2,0,5]]'
 *
 * A group of friends went on holiday and sometimes lent each other money. For
 * example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5
 * for a taxi ride. We can model each transaction as a tuple (x, y, z) which
 * means person x gave person y $z. Assuming Alice, Bill, and Chris are person
 * 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can
 * be represented as [[0, 1, 10], [2, 0, 5]].
 * 
 * Given a list of transactions between a group of people, return the minimum
 * number of transactions required to settle the debt.
 * 
 * Note:
 * 
 * A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
 * Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we
 * could also have the persons 0, 2, 6.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input:
 * [[0,1,10], [2,0,5]]
 * 
 * Output:
 * 2
 * 
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 * 
 * Two transactions are needed. One way to settle the debt is person #1 pays
 * person #0 and #2 $5 each.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
 * 
 * Output:
 * 1
 * 
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 * 
 * Therefore, person #1 only need to give person #0 $4, and all debt is
 * settled.
 * 
 * 
 */
class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.minTransfers(new int[][]{{0, 1, 10},{2, 0, 5}});
    }

    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int[] tran : transactions) {
            countMap.put(tran[0], countMap.getOrDefault(tran[0], 0) + tran[2]);
            countMap.put(tran[1], countMap.getOrDefault(tran[1], 0) - tran[2]);
        }
        /*
        TreeMap<Integer, Integer> tranOrder = new TreeMap<>();
        for (Integer balance : countMap.values()) {
            if (balance == 0) {
                continue;
            }
            tranOrder.put(balance, tranOrder.getOrDefault(balance, 0) + 1);
        }
        int count = 0;
        while (tranOrder.size() > 1) {
            int first = tranOrder.firstKey();
            int last = tranOrder.lastKey();
            int res = first + last;
            tranOrder.put(res, tranOrder.getOrDefault(res, 0) + 1);
            if (tranOrder.get(first) == 1) {
                tranOrder.remove(first);
            } else {
                tranOrder.put(first, tranOrder.get(first) - 1);
            }
            if (tranOrder.get(last) == 1) {
                tranOrder.remove(last);
            } else {
                tranOrder.put(last, tranOrder.get(last) - 1);
            }
            count++;
        }
        */
        List<Integer> balance = new ArrayList<>(countMap.values());
        int count = dfs(balance, 0, 0, balance.size());
        return count;
    }

    public int dfs(List<Integer> a, int start, int num, int n) {
        int res = Integer.MAX_VALUE;
        while (start < n && a.get(start) == 0) start++;
        for (int i = start + 1; i < n; i++) {
            if (a.get(i) < 0 && a.get(start) > 0 || a.get(i) > 0 && a.get(start) < 0) {
                a.set(i, a.get(i) + a.get(start));
                res = Math.min(res, dfs(a, start + 1, num + 1, n));
                a.set(i, a.get(i) - a.get(start));
            }
        }
        return res == Integer.MAX_VALUE ? num : res;
    }
}
