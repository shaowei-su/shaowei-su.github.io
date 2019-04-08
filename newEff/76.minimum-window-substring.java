/*
 * [76] Minimum Window Substring
 *
 * https://leetcode.com/problems/minimum-window-substring/description/
 *
 * algorithms
 * Hard (28.36%)
 * Total Accepted:    179.4K
 * Total Submissions: 631.2K
 * Testcase Example:  '"ADOBECODEBANC"\n"ABC"'
 *
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 * 
 * Example:
 * 
 * 
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * 
 * 
 * Note:
 * 
 * 
 * If there is no such window in S that covers all characters in T, return the
 * empty string "".
 * If there is such window, you are guaranteed that there will always be only
 * one unique minimum window in S.
 * 
 * 
 */
class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> targetCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetCount.put(c, targetCount.getOrDefault(c, 0) + 1);
        }
        int metCount = 0;
        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;
        String res = "";
        while (right < s.length()) {
            char curChar = s.charAt(right);
            if (targetCount.containsKey(curChar)) {
                int n = targetCount.get(curChar) - 1;
                if (n >= 0) {
                    metCount++;
                }
                targetCount.put(curChar, n);
            }
            while (metCount == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    res = s.substring(left, right + 1);
                }
                char leftChar = s.charAt(left);
                if (targetCount.containsKey(leftChar)) {
                    int n = targetCount.get(leftChar) + 1;
                    if (n > 0) {
                        metCount--;
                    }
                    targetCount.put(leftChar, n);
                }
                left++;
            }
            right++;
        }


        return res;




    }
    public String minWindow3(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int leftMin = 0;
        int minLen = Integer.MAX_VALUE;
        int targetCount = 0;
        while (right < s.length()) {
            char cur = s.charAt(right);
            if (charCount.containsKey(cur)) {
                int n = charCount.get(cur) - 1;
                charCount.put(cur, n);
                if (n >= 0) {
                    targetCount++;
                }
            }
            while (targetCount == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    leftMin = left;
                }
                char curLeft = s.charAt(left);
                if (charCount.containsKey(curLeft)) {
                    int n = charCount.get(curLeft) + 1;
                    if (n > 0) {
                        targetCount--;
                    }
                    charCount.put(curLeft, n);
                }
                left++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(leftMin, leftMin + minLen);
    }


    public String minWindow2(String s, String t) {
        if (s.length() < t.length()) return "";
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            Integer count = countMap.getOrDefault(c, 0);
            countMap.put(c, count + 1);
        }
        int start = 0;
        int end = 0;
        int leftMin = 0;
        int minLen = Integer.MAX_VALUE;
        int count = 0;
        while (end < s.length()) {
            if (countMap.containsKey(s.charAt(end))) {
                int n = countMap.get(s.charAt(end)) - 1;
                countMap.put(s.charAt(end), n);
                if (n >= 0) {
                    count++;
                }
            }
            while (count == t.length()) {
                if (end - start + 1 < minLen) {
                    minLen = end - start + 1;
                    leftMin = start;
                }
                if (countMap.containsKey(s.charAt(start))) {
                    int n = countMap.get(s.charAt(start)) + 1;
                    countMap.put(s.charAt(start), n);
                    if (n > 0) {
                        count--;
                    }
                }
                start++;
            }
            end++;
        }
        return (leftMin + minLen) > s.length() ? "" : s.substring(leftMin, leftMin + minLen);
    }
}
