---
layout: post
title: Lintcode BS Sqrt(x)
---
###Task1
Sqrt(x) Show result 

Implement int sqrt(int x).

Compute and return the square root of x.

Have you met this question in a real interview? Yes

Example

sqrt(3) = 1

sqrt(4) = 2

sqrt(5) = 2

sqrt(10) = 3

Challenge
O(log(x))

###Python
```python
class Solution(object):
    def mySqrt(self, x):
        """
        :type x: int
        :rtype: int
        """
        l = 0
        r = x
        while l + 1 < r:
            m = l + (r - l) / 2
            if m * m <= x and (m + 1) * (m + 1) > x:
                return m
            elif m * m < x:
                l = m
            else:
                r = m
        if l * l == x:
            return l
        else:
            return r
```

###Java (BETTER)
```java
class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // write your code here
        if (x <= 0) {
            return 0;
        }
        long start = 0;
        long end = x;
        long mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (mid * mid <= x) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (end * end <= x) {
            return (int) end;
        } else {
            return (int) start;
        }
    }
}
```

###Points
* why long? mid * mid may overflow;
* What to find? the last element that ele * ele <= x;

