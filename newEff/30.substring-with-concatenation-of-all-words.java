/*
 * @lc app=leetcode id=30 lang=java
 *
 * [30] Substring with Concatenation of All Words
 *
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/
 *
 * algorithms
 * Hard (22.81%)
 * Total Accepted:    124.3K
 * Total Submissions: 535.8K
 * Testcase Example:  '"barfoothefoobarman"\n["foo","bar"]'
 *
 * You are given a string, s, and a list of words, words, that are all of the
 * same length. Find all starting indices of substring(s) in s that is a
 * concatenation of each word in words exactly once and without any intervening
 * characters.
 * 
 * Example 1:
 * 
 * 
 * Input:
 * ⁠ s = "barfoothefoobarman",
 * ⁠ words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar"
 * respectively.
 * The output order does not matter, returning [9,0] is fine too.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * ⁠ s = "wordgoodgoodgoodbestword",
 * ⁠ words = ["word","good","best","word"]
 * Output: []
 * 
 * 
 */
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
         List<Integer> res = new ArrayList<>();
         if (s == null || words == null || words.length == 0 || s.length() == 0) {
             return res;
         }
         Map<String, Integer> wcount_copy = new HashMap<>();
         int totalLen = 0;
         for (String w : words) {
             totalLen += w.length();
             wcount_copy.put(w, wcount_copy.getOrDefault(w, 0) + 1);
         }

         int left = 0;
         int right = 0;
         int len = words[0].length();
         for (int i = 0; i < len; i++) {
             left = i;
             right = i;
            Map<String, Integer> wcount = new HashMap<>(wcount_copy);
         while (right + len <= s.length()) {
             String cur = s.substring(right, right + len);
             if (wcount.containsKey(cur)) {
                 if (wcount.get(cur) == 1) {
                     wcount.remove(cur);
                 } else {
                     wcount.put(cur, wcount.get(cur) - 1);
                 }
                 if (wcount.size() == 0) {
                     res.add(left);
                     wcount.put(s.substring(left, left + len), 1);
                     left += len;
                 }
                 right += len;
             } else {
                 boolean met = false;
                 while (left < right) {
                     String tmp = s.substring(left, left + len);
                     wcount.put(tmp, wcount.getOrDefault(tmp, 0) + 1);
                     left += len;
                     if (tmp.equals(cur)) {
                         met = true;
                         break;
                     }
                 }
                 if (!met) {
                    right += len;
                    left = right;
                 }
             }
         }
         }


         return res;

    }
    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || words == null || words.length == 0 || s.length() == 0) {
            return res;
        }
        Map<String, Integer> wcount = new HashMap<>();
        int len = 0;
        for (String w : words) {
            len += w.length();
            wcount.put(w, wcount.getOrDefault(w, 0) + 1);
        }

        for (int i = 0; i <= s.length() - len; i++) {
            if (isValid(s.substring(i, i + len), wcount)) {
                res.add(i);
            }
        }
        return res;

    }

    public boolean isValid(String s, Map<String, Integer> wcountInput) {
        Map<String, Integer> wcount = new HashMap<>(wcountInput);
        int last = 0;
        int iter = 0;
        while (iter < s.length()) {
            String candi = s.substring(last, iter + 1);
            if (wcount.size() == 0) {
                return false;
            }
            if (wcount.containsKey(candi)) {
                if (wcount.get(candi) > 1) {
                    wcount.put(candi, wcount.get(candi) - 1);
                } else {
                    wcount.remove(candi);
                }
                last = iter + 1;
            }
            iter++;
        }
        return wcount.size() == 0;
    }
}
