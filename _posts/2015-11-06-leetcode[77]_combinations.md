---
layout: post
title: Leetcode[77] Combinations
---
###Task
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

###Python

```python
class Solution(object):
    def combine(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: List[List[int]]
        """
        ret = []
        temp = []
        self.helper(ret, temp, 1, n, k)
        return ret
    def helper(self, ret, temp, cur, n, k):
        if len(temp) == k:
            ret.append(temp[:])
            return
        for i in range(cur, n + 1):
            temp.append(i)
            self.helper(ret, temp, i + 1, n, k)
            temp.pop()
            
```

###Java

```java
public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        helper(ret, temp, 1, n, k);
        return ret;
    }
    
    public void helper(List<List<Integer>> ret, List<Integer> temp, int cur, int n, int k) {
        if (temp.size() == k) {
            ret.add(new ArrayList<Integer>(temp));
            return;
        } else {
            for (int i = cur; i <= n; i++) {
                temp.add(i);
                helper(ret, temp, i + 1, n, k);
                temp.remove(temp.size() - 1);
            }
        }
    }
}

```

###Points
* Difference with __Permutation__, every number appear only __once__;
* The condition that determine __when__ to add a temp 
* DFS / backtracking