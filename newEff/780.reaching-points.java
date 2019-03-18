/*
 * @lc app=leetcode id=780 lang=java
 *
 * [780] Reaching Points
 *
 * https://leetcode.com/problems/reaching-points/description/
 *
 * algorithms
 * Hard (26.39%)
 * Total Accepted:    7.4K
 * Total Submissions: 27.3K
 * Testcase Example:  '9\n5\n12\n8'
 *
 * A move consists of taking a point (x, y) and transforming it to either (x,
 * x+y) or (x+y, y).
 * 
 * Given a starting point (sx, sy) and a target point (tx, ty), return True if
 * and only if a sequence of moves exists to transform the point (sx, sy) to
 * (tx, ty). Otherwise, return False.
 * 
 * 
 * Examples:
 * Input: sx = 1, sy = 1, tx = 3, ty = 5
 * Output: True
 * Explanation:
 * One series of moves that transforms the starting point to the target is:
 * (1, 1) -> (1, 2)
 * (1, 2) -> (3, 2)
 * (3, 2) -> (3, 5)
 * 
 * Input: sx = 1, sy = 1, tx = 2, ty = 2
 * Output: False
 * 
 * Input: sx = 1, sy = 1, tx = 1, ty = 1
 * Output: True
 * 
 * 
 * 
 * Note:
 * 
 * 
 * sx, sy, tx, ty will all be integers in the range [1, 10^9].
 * 
 * 
 */
class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
          if (sx == tx && sy == ty) {
              return true;
          }
          if (tx == ty || sx > tx || sy > ty) {
              return false;
          }
          if (tx > ty) {
              int sub = Math.max(1, (tx - sx) / ty);
              return reachingPoints(sx, sy, tx - sub * ty, ty);
          } else {
              int sub = Math.max(1, (ty - sy) / tx);
              return reachingPoints(sx, sy, tx, ty - sub * tx);
          }

    }
    public boolean reachingPoints4(int sx, int sy, int tx, int ty) {
         if (sx > tx || sy > ty) {
             return false;
         }
         List<int[]> l = new ArrayList<>();
         l.add(new int[]{sx, sy});
         Set<String> visited = new HashSet<>();
         while (l.size() > 0) {
             List<int[]> temp = new ArrayList<>();
             for (int[] pair : l) {
                 if (pair[0] == tx && pair[1] == ty) {
                     return true;
                 }
                 visited.add(pair[0] + "-" + pair[1]);
                 if (pair[0] + pair[1] <= tx && !visited.contains(pair[0] + pair[1] + "-" + pair[1])) {
                     temp.add(new int[] {pair[0] + pair[1], pair[1]});
                 }
                 if (pair[0] + pair[1] <= ty && !visited.contains(pair[0] + "-" + pair[0] + pair[1])) {
                     temp.add(new int[] {pair[0], pair[0] + pair[1]});
                 }
             }
             l = temp;
         }

         return false;

    }
    public boolean reachingPoints3(int sx, int sy, int tx, int ty) {
        if (sx > tx || sy > ty) {
            return false;
        }
        boolean[][] dp = new boolean[tx + 1][ty + 1];
        dp[sx][sy] = true;
        for (int i = sx; i <= tx; i++) {
            for (int j = sy; j <= ty; j++) {
                if (i + j <= ty)
                dp[i][j + i] = dp[i][j];
                if (i + j <= tx)
                dp[i + j][j] = dp[i][j];
            }
        }


        return dp[tx][ty];

    }
    Map<String, Boolean> cache = new HashMap<>();
    public boolean reachingPoints2(int sx, int sy, int tx, int ty) {
        //dp[i][j] == dp[i][j - i] || dp[i - j][j]
        if (cache.containsKey(sx + "-" + sy)) {
            return cache.get(sx + "-" + sy);
        }
        if (sx > tx || sy > ty) {
            cache.put(sx + "-" + sy, false);
            return false;
        }
        boolean res = reachingPoints(sx + sy, sy, tx, ty) || reachingPoints(sx, sx + sy, tx, ty);
        cache.put(sx + "-" + sy, res);


        return res;

    }
}
