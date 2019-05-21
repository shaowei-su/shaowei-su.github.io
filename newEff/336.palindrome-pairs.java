/*
 * @lc app=leetcode id=336 lang=java
 *
 * [336] Palindrome Pairs
 *
 * https://leetcode.com/problems/palindrome-pairs/description/
 *
 * algorithms
 * Hard (29.33%)
 * Total Accepted:    59K
 * Total Submissions: 199.1K
 * Testcase Example:  '["abcd","dcba","lls","s","sssll"]'
 *
 * Given a list of unique words, find all pairs of distinct indices (i, j) in
 * the given list, so that the concatenation of the two words, i.e. words[i] +
 * words[j] is a palindrome.
 * 
 * Example 1:
 * 
 * 
 * 
 * Input: ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]] 
 * Explanation: The palindromes are
 * ["dcbaabcd","abcddcba","slls","llssssll"]
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: ["bat","tab","cat"]
 * Output: [[0,1],[1,0]] 
 * Explanation: The palindromes are ["battab","tabbat"]
 * 
 * 
 * 
 */
class Solution {

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length < 2) {
            return res;
        }
        Map<String, Integer> smap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            smap.put(words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("")) {
                for (int j = 0; j < words.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (isPan(words[j])) {
                        res.add(Arrays.asList(i, j));
                    }
                }
                continue;
            }
            for (int j = 0; j < words[i].length(); j++) {
                String left = words[i].substring(0, j);
                String right = words[i].substring(j);
                if (isPan(left)) {
                    String rev = new StringBuilder().append(right).reverse().toString();
                    if (smap.containsKey(rev) && smap.get(rev) != i) {
                        List<Integer> tmp = new ArrayList<>(Arrays.asList(smap.get(rev), i));
                        res.add(tmp);
                    }
                }
                if (isPan(right)) {
                    String rev = new StringBuilder().append(left).reverse().toString();
                    if (smap.containsKey(rev) && smap.get(rev) != i) {
                        List<Integer> tmp = new ArrayList<>(Arrays.asList(i, smap.get(rev)));
                        res.add(tmp);
                    }
                }
            }
        }



        return res;



    }








    public List<List<Integer>> palindromePairs3(String[] words) {
         List<List<Integer>> res = new ArrayList<>();
         if (words == null || words.length == 0) {
             return res;
         }
         Map<String, Integer> strInd = new HashMap<>();
         for (int i = 0; i < words.length; i++) {
             strInd.put(words[i], i);
         }
         for (int i = 0; i < words.length; i++) {
             String cur = words[i];
             for (int j = 0; j <= cur.length(); j++) {
                 String left = cur.substring(0, j);
                 String right = cur.substring(j);
                 if (isPan(left)) {
                     String rightRev = new StringBuilder(right).reverse().toString();
                     if (strInd.containsKey(rightRev) && strInd.get(rightRev) != i) {
                         List<Integer> temp = new ArrayList<>();
                         temp.add(strInd.get(rightRev));
                         temp.add(i);
                         res.add(temp);
                     }
                 }
                 if (isPan(right)) {
                     String leftRev = new StringBuilder(left).reverse().toString();
                     if (strInd.containsKey(leftRev) && strInd.get(leftRev) != i && right.length() > 0) {
                         List<Integer> temp = new ArrayList<>();
                         temp.add(i);
                         temp.add(strInd.get(leftRev));
                         res.add(temp);
                     }
                 }
             }
         }
         return res;

    }
    public List<List<Integer>> palindromePairs2(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) {
                    continue;
                }
                if (isPan(words[i] + words[j])) {
                    res.add(new ArrayList<Integer>(Arrays.asList(i, j)));
                }
            }
        }
        return res;
    }
    public boolean isPan(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
