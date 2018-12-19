/*
 * [461] Hamming Distance
 *
 * https://leetcode.com/problems/hamming-distance/description/
 *
 * algorithms
 * Easy (69.62%)
 * Total Accepted:    203.7K
 * Total Submissions: 292.6K
 * Testcase Example:  '1\n4'
 *
 * The Hamming distance between two integers is the number of positions at
 * which the corresponding bits are different.
 * 
 * Given two integers x and y, calculate the Hamming distance.
 * 
 * Note:
 * 0 ≤ x, y < 231.
 * 
 * 
 * Example:
 * 
 * Input: x = 1, y = 4
 * 
 * Output: 2
 * 
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * ⁠      ↑   ↑
 * 
 * The above arrows point to positions where the corresponding bits are
 * different.
 * 
 * 
 */
class Solution {
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if (((z >> i) & 1) == 1) {
                count++;
            }
        }
        return count;
    
    }
    public int hammingDistance2(int x, int y) {
        int count = 0;
        for (int i = 0; i < 31; i++) {
            int a = (x >> i) & 1;
            int b = (y >> i) & 1;
            if (a != b) {
                count++;
            }
        }
        return count;
    }
}
