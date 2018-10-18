/*
 * [319] Bulb Switcher
 *
 * https://leetcode.com/problems/bulb-switcher/description/
 *
 * algorithms
 * Medium (43.25%)
 * Total Accepted:    51.9K
 * Total Submissions: 119.9K
 * Testcase Example:  '3'
 *
 * There are n bulbs that are initially off. You first turn on all the bulbs.
 * Then, you turn off every second bulb. On the third round, you toggle every
 * third bulb (turning on if it's off or turning off if it's on). For the i-th
 * round, you toggle every i bulb. For the n-th round, you only toggle the last
 * bulb. Find how many bulbs are on after n rounds.
 * 
 * Example:
 * 
 * 
 * Input: 3
 * Output: 1 
 * Explanation: 
 * At first, the three bulbs are [off, off, off].
 * After first round, the three bulbs are [on, on, on].
 * After second round, the three bulbs are [on, off, on].
 * After third round, the three bulbs are [on, off, off]. 
 * 
 * So you should return 1, because there is only one bulb is on.
 * 
 * 
 */
class Solution {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
    public int bulbSwitch2(int n) {
        if (n <= 0) {
            return 0;
        }
        boolean[] isOn = new boolean[n];
        for (int i = 0; i < n; i++) {
            isOn[i] = true;
        }
        for (int i = 2; i <= n; i++) {
            int cur = i - 1;
            while (cur < n) {
                isOn[cur] = !isOn[cur];
                cur += i;
            }
        }
        int count = 0;
        for (boolean b : isOn) {
            if (b) {
                count++;
            }
        }
        return count;
    }
}
