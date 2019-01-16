/*
 * @lc app=leetcode id=383 lang=java
 *
 * [383] Ransom Note
 *
 * https://leetcode.com/problems/ransom-note/description/
 *
 * algorithms
 * Easy (48.98%)
 * Total Accepted:    99.7K
 * Total Submissions: 203.5K
 * Testcase Example:  '"a"\n"b"'
 *
 * 
 * Given an arbitrary ransom note string and another string containing letters
 * from all the magazines, write a function that will return true if the
 * ransom 
 * note can be constructed from the magazines ; otherwise, it will return
 * false. 
 * 
 * 
 * Each letter in the magazine string can only be used once in your ransom
 * note.
 * 
 * 
 * Note:
 * You may assume that both strings contain only lowercase letters.
 * 
 * 
 * 
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 * 
 * 
 */
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] map = new int[256];
        for (char c : magazine.toCharArray()) {
            map[c]++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (map[c] == 0) {
                return false;
            }
            map[c]--;
        }
        return true;
    }
}
