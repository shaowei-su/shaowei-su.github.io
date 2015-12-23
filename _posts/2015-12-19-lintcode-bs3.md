---
layout: post
title: Lintcode BS Wood Cut
---
###Task1
Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.

Have you met this question in a real interview? Yes
Example

For L=[232, 124, 456], k=7, return 114.

Note

You couldn't cut wood into float length.

Challenge

O(n log Len), where Len is the longest length of the wood.

###java
```java
public class Solution {
    /** 
     *@param L: Given n pieces of wood with length L[i]
     *@param k: An integer
     *return: The maximum length of the small pieces.
     */
    public int woodCut(int[] L, int k) {
        // write your code here
        if (L == null || L.length == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < L.length; i++) {
            max = Math.max(max, L[i]);
        }
        int start = 1;
        int end = max;
        int mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (count(L, mid) >= k) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (count(L, end) >= k) {
            return end;
        } else if (count(L, start) >= k) {
            return start;
        }
        return 0;
    }
    
    public int count(int[] L, int length) {
        int count = 0;
        for (int i = 0; i < L.length; i++) {
            count += L[i] / length;
        }
        return count;
    }
}

```

###Points
* Find the last length that make the number of cuts >= k;
* Initialize end to the max length of woods. why? We need keep the possibility of making it larger.
* To separate the count() function out!!!

