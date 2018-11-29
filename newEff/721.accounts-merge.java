/*
 * [721] Accounts Merge
 *
 * https://leetcode.com/problems/accounts-merge/description/
 *
 * algorithms
 * Medium (36.67%)
 * Total Accepted:    21.8K
 * Total Submissions: 59.3K
 * Testcase Example:  '[["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]'
 *
 * Given a list accounts, each element accounts[i] is a list of strings, where
 * the first element accounts[i][0] is a name, and the rest of the elements are
 * emails representing emails of the account.
 * 
 * Now, we would like to merge these accounts.  Two accounts definitely belong
 * to the same person if there is some email that is common to both accounts.
 * Note that even if two accounts have the same name, they may belong to
 * different people as people could have the same name.  A person can have any
 * number of accounts initially, but all of their accounts definitely have the
 * same name.
 * 
 * After merging the accounts, return the accounts in the following format: the
 * first element of each account is the name, and the rest of the elements are
 * emails in sorted order.  The accounts themselves can be returned in any
 * order.
 * 
 * Example 1:
 * 
 * Input: 
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John",
 * "johnnybravo@mail.com"], ["John", "johnsmith@mail.com",
 * "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com',
 * 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary",
 * "mary@mail.com"]]
 * Explanation: 
 * The first and third John's are the same person as they have the common email
 * "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email
 * addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary',
 * 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']]
 * would still be accepted.
 * 
 * 
 * 
 * Note:
 * The length of accounts will be in the range [1, 1000].
 * The length of accounts[i] will be in the range [1, 10].
 * The length of accounts[i][j] will be in the range [1, 30].
 * 
 */
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
         List<List<String>> res = new ArrayList<>();
         if (accounts == null || accounts.size() == 0) {
             return res;
         }
         Map<String, String> emailMap = new HashMap<>();
         int[] parent = new int[accounts.size()];
         for (int i = 0; i < parent.length; i++) {
             parent[i] = i;
         }
         for (int i = 0; i < accounts.size(); i++) {
             List<String> curList = accounts.get(i);
             String key = i + "#" + curList.get(0);
             for (int j = 1; j < curList.size(); j++) {
                 if (emailMap.containsKey(curList.get(j))) {
                     int otherKey = Integer.parseInt(emailMap.get(curList.get(j)).split("#")[0]);
                     int otherParent = findParent(parent, otherKey);
                     int curParent = findParent(parent, i);
                     if (otherParent != curParent) {
                         parent[otherParent] = curParent;
                     }
                 }
             }
             for (int j = 1; j < curList.size(); j++) {
                 emailMap.put(curList.get(j), key);
             }
         }
         Map<String, Set<String>> tempRes = new HashMap<>();
         for (int i = 0; i < parent.length; i++) {
            int pId = findParent(parent, i);
            String pName = accounts.get(pId).get(0);
            Set<String> pSet = tempRes.computeIfAbsent(pId + "#" + pName, s -> new HashSet<>());
            for (int j = 1; j < accounts.get(i).size(); j++) {
                pSet.add(accounts.get(i).get(j));
            }
         }
         for (String key : tempRes.keySet()) {
             List<String> temp = new ArrayList<>(tempRes.get(key));
             Collections.sort(temp);
             temp.add(0, key.split("#")[1]);
             res.add(temp);
         }
         return res;
    }

    public int findParent(int[] parent, int i) {
        while (parent[i] != i) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    public List<List<String>> accountsMerge2(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        if (accounts == null || accounts.size() == 0) {
            return res;
        }
        Map<String, String> emailMap = new HashMap<>();
        Map<String, List<String>> indexMap = new HashMap<>(); 
        for (int i = 0; i < accounts.size(); i++) {
            List<String> curList = accounts.get(i);
            String key = i + "#" + curList.get(0);
            List<String> temp = new ArrayList<>();
            Set<String> dedup = new HashSet<>();
            for (int j = 1; j < curList.size(); j++) {
                if (emailMap.containsKey(curList.get(j))) {
                    if (indexMap.containsKey(emailMap.get(curList.get(j)))) {
                        dedup.addAll(indexMap.get(emailMap.get(curList.get(j))));
                    }
                    indexMap.remove(emailMap.get(curList.get(j)));
                } else {
                    dedup.add(curList.get(j));
                }
            }
            temp.addAll(dedup);
            for (String email : temp) {
                emailMap.put(email, key);
            }
            Collections.sort(temp);
            indexMap.put(key, temp);
        }
        for (String key : indexMap.keySet()) {
            List<String> acc = new ArrayList<>();
            acc.add(key.split("#")[1]);
            acc.addAll(indexMap.get(key));
            res.add(acc);
        }
        return res;
    }
}
