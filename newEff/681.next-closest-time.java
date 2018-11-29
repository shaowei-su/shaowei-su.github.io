/*
 * [681] Next Closest Time
 *
 * https://leetcode.com/problems/next-closest-time/description/
 *
 * algorithms
 * Medium (41.30%)
 * Total Accepted:    34.7K
 * Total Submissions: 84K
 * Testcase Example:  '"19:34"'
 *
 * Given a time represented in the format "HH:MM", form the next closest time
 * by reusing the current digits. There is no limit on how many times a digit
 * can be reused.
 * 
 * You may assume the given input string is always valid. For example, "01:34",
 * "12:09" are all valid. "1:34", "12:9" are all invalid.
 * 
 * Example 1:
 * 
 * Input: "19:34"
 * Output: "19:39"
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is
 * 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs
 * 23 hours and 59 minutes later.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is
 * 22:22. It may be assumed that the returned time is next day's time since it
 * is smaller than the input time numerically.
 * 
 * 
 */
class Solution {
    public String nextClosestTime(String time) {
        if (time == null || time.length() == 0) {
            return "";
        }
        int[] numArr = new int[] {Integer.parseInt(String.valueOf(time.charAt(0))), Integer.parseInt(String.valueOf(time.charAt(1))), Integer.parseInt(String.valueOf(time.charAt(3))), Integer.parseInt(String.valueOf(time.charAt(4)))};
        int timeNum = numArr[0] * 1000 + numArr[1] * 100 + numArr[2] * 10 + numArr[3];

        List<Integer> targets = new ArrayList<>();
        int[] cur = Arrays.copyOf(numArr, 4);
        dfs(targets, 0, cur, numArr, timeNum);


        if (targets.size() == 0) {
            return time;
        }
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        for (Integer i : targets) {
            a = Math.min(a, i);
            if (i > timeNum && i <= 2399) {
                b = Math.min(b, i);
            }
        }
        String aStr = convTime(a);
        String bStr = convTime(b);
        return b == Integer.MAX_VALUE ? aStr : bStr;
    }

    public String convTime(int time) {
        int hour = time / 100;
        int min = time % 100;
        return (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min);
    }

    public void dfs(List<Integer> targets, int pos, int[] cur, int[] numArr, int orig) {
        if (pos >= 4) {
            return;
        }
        int def = cur[pos];
        for (int num : numArr) {
            cur[pos] = num;
            int newCur = cur[0] * 1000 + cur[1] * 100 + cur[2] * 10 + cur[3];
            if (isValid(newCur, orig)) {
                targets.add(newCur);
            }
            dfs(targets, pos + 1, cur, numArr, orig);
            cur[pos] = def;
        }
    }

    public boolean isValid(int newCur, int orig) {
        if (newCur == orig) {
            return false;
        }
        return (newCur / 100 < 24) && ((newCur % 100) < 60);
    }
}
