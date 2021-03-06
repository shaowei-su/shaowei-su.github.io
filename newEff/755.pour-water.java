/*
 * @lc app=leetcode id=755 lang=java
 *
 * [755] Pour Water
 *
 * https://leetcode.com/problems/pour-water/description/
 *
 * algorithms
 * Medium (39.52%)
 * Total Accepted:    11.4K
 * Total Submissions: 28.5K
 * Testcase Example:  '[2,1,1,2,1,2,2]\n4\n3'
 *
 * 
 * We are given an elevation map, heights[i] representing the height of the
 * terrain at that index.  The width at each index is 1.  After V units of
 * water fall at index K, how much water is at each index?
 * 
 * Water first drops at index K and rests on top of the highest terrain or
 * water at that index.  Then, it flows according to the following rules:
 * If the droplet would eventually fall by moving left, then move left.
 * Otherwise, if the droplet would eventually fall by moving right, then move
 * right.
 * Otherwise, rise at it's current position.
 * Here, "eventually fall" means that the droplet will eventually be at a lower
 * level if it moves in that direction.
 * Also, "level" means the height of the terrain plus any water in that
 * column.
 * 
 * We can assume there's infinitely high terrain on the two sides out of bounds
 * of the array.  Also, there could not be partial water being spread out
 * evenly on more than 1 grid block - each unit of water has to be in exactly
 * one block.
 * 
 * 
 * Example 1:
 * 
 * Input: heights = [2,1,1,2,1,2,2], V = 4, K = 3
 * Output: [2,2,2,3,2,2,2]
 * Explanation:
 * #       #
 * #       #
 * ##  # ###
 * #########
 * ⁠0123456    
 * 
 * 
 * Example 2:
 * 
 * Input: heights = [1,2,3,4], V = 2, K = 2
 * Output: [2,3,3,4]
 * Explanation:
 * The last droplet settles at index 1, since moving further left would not
 * cause it to eventually fall to a lower height.
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: heights = [3,1,3], V = 5, K = 1
 * Output: [4,4,4]
 * 
 * 
 * 
 * Note:
 * heights will have length in [1, 100] and contain integers in [0, 99].
 * V will be in range [0, 2000].
 * K will be in range [0, heights.length - 1].
 * 
 */
class Solution {
    public int[] pourWater(int[] heights, int V, int K) {
        while (V-- > 0) {
            int index = K;
            while (index - 1 >= 0 && heights[index - 1] <= heights[index]) {
                index--;
            }
            int leftMost = index;
            if (heights[leftMost] < heights[K] && leftMost != 0) {
                heights[leftMost]++;
                continue;
            }
            index = K;
            while (index + 1 < heights.length && heights[index + 1] <= heights[index]) {
                index++;
            }
            int rightMost = index;
            if (heights[rightMost] < heights[K] && rightMost != heights.length - 1) {
                heights[rightMost]++;
                continue;
            }
            heights[K]++;
        }
        
        return Arrays.copyOfRange(heights, 0, heights.length);

    }
    public int[] pourWater2(int[] heights, int V, int K) {
       while (V > 0) {
           int leftMin = heights[K];
           int rightMin = heights[K];
           int leftIter = K - 1, rightIter = K + 1;
           int leftMinPos = K, rightMinPos = K;
           while (leftIter >= 0) {
               if (heights[leftIter] > heights[leftIter + 1]) {
                   break;
               }
               if (heights[leftIter] < leftMin) {
                   leftMin = heights[leftIter];
                   leftMinPos = leftIter;
               }
               leftIter--;
           }
           while (rightIter < heights.length) {
               if (heights[rightIter] > heights[rightIter - 1]) {
                   break;
               }
               if (heights[rightIter] < rightMin) {
                   rightMin = heights[rightIter];
                   rightMinPos =rightIter;
               }
               rightIter++;
           }
           
           if (leftMin < heights[K]) {
               heights[leftMinPos]++;
           } else if (rightMin < heights[K]) {
               heights[rightMinPos]++;
           } else {
               heights[K]++;
           }
           V--;
       }
       return Arrays.copyOfRange(heights, 0, heights.length);
    }
}
