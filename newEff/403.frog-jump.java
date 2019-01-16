/*
 * [403] Frog Jump
 *
 * https://leetcode.com/problems/frog-jump/description/
 *
 * algorithms
 * Hard (34.74%)
 * Total Accepted:    42.5K
 * Total Submissions: 122.2K
 * Testcase Example:  '[0,1,3,4,5,7,9,10,12]'
 *
 * A frog is crossing a river. The river is divided into x units and at each
 * unit there may or may not exist a stone. The frog can jump on a stone, but
 * it must not jump into the water.
 * 
 * Given a list of stones' positions (in units) in sorted ascending order,
 * determine if the frog is able to cross the river by landing on the last
 * stone. Initially, the frog is on the first stone and assume the first jump
 * must be 1 unit.
 * 
 * 
 * If the frog's last jump was k units, then its next jump must be either k -
 * 1, k, or k + 1 units. Note that the frog can only jump in the forward
 * direction.
 * 
 * Note:
 * 
 * The number of stones is ≥ 2 and is < 1,100.
 * Each stone's position will be a non-negative integer < 231.
 * The first stone's position is always 0.
 * 
 * 
 * 
 * Example 1:
 * 
 * [0,1,3,5,6,8,12,17]
 * 
 * There are a total of 8 stones.
 * The first stone at the 0th unit, second stone at the 1st unit,
 * third stone at the 3rd unit, and so on...
 * The last stone at the 17th unit.
 * 
 * Return true. The frog can jump to the last stone by jumping 
 * 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
 * 2 units to the 4th stone, then 3 units to the 6th stone, 
 * 4 units to the 7th stone, and 5 units to the 8th stone.
 * 
 * 
 * 
 * Example 2:
 * 
 * [0,1,2,3,4,8,9,11]
 * 
 * Return false. There is no way to jump to the last stone as 
 * the gap between the 5th and 6th stone is too large.
 * 
 * 
 */
class Solution {
     public boolean canCross(int[] stones) {
         // dp[i][k]: true: able to jump to index i, with k distance last jump
         // dp[i][k] = true --> dp[i + k + 1][k + 1], dp[i + k - 1][k - 1] also true
         if (stones == null) {
             return false;
         }
 
         int len = stones.length;
         int max = stones[len - 1];
         if (max > (len * (len + 1) / 2)) {
             return false;
         }
         if (len == 2) {
             if (stones[0] == 0 && stones[1] == 1) {
                 return true;
             } else {
                 return false;
             }
         }

        Map<Integer, Map<Integer, Boolean>> dp = new HashMap<>();
        Map<Integer, Boolean> one = dp.computeIfAbsent(1, m -> new HashMap<>());
        one.put(1, true);

         for (int i = 1; i < len; i++) {
             int pos = stones[i];
             for (int k = 1; k < max; k++) {
                 if (dp.get(pos) == null) {
                     continue;
                 }
                 Map<Integer, Boolean> mini = dp.get(pos);
                 if (mini.get(k) != null) {
                    if (k - 1 > 0 && (pos + k - 1 <= max)) {
                        Map<Integer, Boolean> t1 = dp.computeIfAbsent(pos + k - 1, m -> new HashMap<>());
                        t1.put(k - 1, true);
                    }
                    if (pos + k + 1 <= max) {
                        Map<Integer, Boolean> t2 = dp.computeIfAbsent(pos + k + 1, m -> new HashMap<>());
                        t2.put(k + 1, true);
                    }
                    if (pos + k <= max) {
                        Map<Integer, Boolean> t3 = dp.computeIfAbsent(pos + k, m -> new HashMap<>());
                        t3.put(k, true);
                    }
                 }
             }
         }
         if (dp.get(max) != null) {
             for (Boolean b : dp.get(max).values()) {
                 if (b) {
                     return true;
                 }
             }
         }
         return false;

     }
    public boolean canCross2(int[] stones) {
        // dp[i][k]: true: able to jump to index i, with k distance last jump
        // dp[i][k] = true --> dp[i + k + 1][k + 1], dp[i + k - 1][k - 1] also true
        if (stones == null) {
            return false;
        }
        
        int len = stones.length;
        int max = stones[len - 1];
        if (max > (len * (len + 1) / 2)) {
            return false;
        }
        if (len == 2) {
            if (stones[0] == 0 && stones[1] == 1) {
                return true;
            } else {
                return false;
            }
        }
        boolean[][] dp = new boolean[max + 1][max];
        dp[0][0] = true;
        dp[1][1] = true;
        for (int i = 1; i < len; i++) {
            int pos = stones[i];
            for (int k = 1; k < max; k++) {
                if (dp[pos][k]) {
                    if (k - 1 > 0 && (pos + k - 1 <= max)) {
                        dp[pos + k - 1][k - 1] = true;
                    }
                    if (pos + k + 1 <= max) {
                        dp[pos + k + 1][k + 1] = true;
                    }
                    if (pos + k <= max) {
                        dp[pos + k][k] = true;
                    }
                }
            }
        }
        for (int i = 1; i < max; i++) {
            if (dp[max][i]) {
                return true;
            }
        }
        return false;

    }
}
