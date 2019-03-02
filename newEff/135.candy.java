/*
 * @lc app=leetcode id=135 lang=java
 *
 * [135] Candy
 *
 * https://leetcode.com/problems/candy/description/
 *
 * algorithms
 * Hard (27.31%)
 * Total Accepted:    96.6K
 * Total Submissions: 348.9K
 * Testcase Example:  '[1,0,2]'
 *
 * There are N children standing in a line. Each child is assigned a rating
 * value.
 * 
 * You are giving candies to these children subjected to the following
 * requirements:
 * 
 * 
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * 
 * 
 * What is the minimum candies you must give?
 * 
 * Example 1:
 * 
 * 
 * Input: [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2,
 * 1, 2 candies respectively.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1,
 * 2, 1 candies respectively.
 * ⁠            The third child gets 1 candy because it satisfies the above two
 * conditions.
 * 
 * 
 */
class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int[] candies = new int[ratings.length];
        candies[0] = 1;
        int i = 1;
        while (i < ratings.length) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
                i++;
            } else if (ratings[i] == ratings[i - 1]) {
                candies[i] = 1;
                i++;
            } else {
                int cur = i;
                while (i + 1 < ratings.length && ratings[i] > ratings[i + 1]) {
                    i++;
                }
                candies[i] = 1;
                for (int j = i - 1; j >= cur; j--) {
                    candies[j] = candies[j + 1] + 1;
                }
                candies[cur - 1] = Math.max(candies[cur - 1], candies[cur] + 1);
                i++;
            }
        }
        return Arrays.stream(candies).sum();

    }
    public void propagate(int[] ratings, int[] candies, int i) {
        candies[i] = 1;
        int ind = i - 1;
        int cur = 1;
        while (ind >= 0) {
            if (ratings[ind] > ratings[ind + 1]) {
                candies[ind] = Math.max(candies[ind], candies[ind + 1] + 1);
            } else {
                candies[ind] = Math.max(candies[ind], 1);
            }
            ind--;
        }
        ind = i + 1;
        while (ind < candies.length) {
            if (ratings[ind] > ratings[ind - 1]) {
                candies[ind] = Math.max(candies[ind], candies[ind - 1] + 1);
            } else {
                candies[ind] = Math.max(candies[ind], 1);
            }
            ind++;
        }
        return;
    }
}
