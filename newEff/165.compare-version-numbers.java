/*
 * @lc app=leetcode id=165 lang=java
 *
 * [165] Compare Version Numbers
 *
 * https://leetcode.com/problems/compare-version-numbers/description/
 *
 * algorithms
 * Medium (22.22%)
 * Total Accepted:    126.1K
 * Total Submissions: 557.7K
 * Testcase Example:  '"0.1"\n"1.1"'
 *
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1; if version1 < version2 return -1;otherwise
 * return 0.
 * 
 * You may assume that the version strings are non-empty and contain only
 * digits and the . character.
 * The . character does not represent a decimal point and is used to separate
 * number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three", it
 * is the fifth second-level revision of the second first-level revision.
 * You may assume the default revision number for each level of a version
 * number to be 0. For example, version number 3.4 has a revision number of 3
 * and 4 for its first and second level revision number. Its third and fourth
 * level revision number are both 0.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: version1 = "0.1", version2 = "1.1"
 * Output: -1
 * 
 * Example 2:
 * 
 * Input: version1 = "1.0.1", version2 = "1"
 * Output: 1
 * 
 * Example 3:
 * 
 * Input: version1 = "7.5.2.4", version2 = "7.5.3"
 * Output: -1
 * 
 * Example 4:
 * 
 * Input: version1 = "1.01", version2 = "1.001"
 * Output: 0
 * Explanation: Ignoring leading zeroes, both “01” and “001" represent the same
 * number “1”
 * 
 * Example 5:
 * 
 * Input: version1 = "1.0", version2 = "1.0.0"
 * Output: 0
 * Explanation: The first version number does not have a third level revision
 * number, which means its third level revision number is default to "0"
 * 
 * 
 * 
 * Note:
 * 
 * Version strings are composed of numeric strings separated by dots . and this
 * numeric strings may have leading zeroes. 
 * Version strings do not start or end with dots, and they will not be two
 * consecutive dots.
 * 
 */
class Solution {
    public int compareVersion(String version1, String version2) {
        if (version1 == null || version1.length() == 0) {
            return -1;
        }
        if (version2 == null || version2.length() == 0) {
            return 1;
        }
        String[] parts1 = version1.split("\\.");
        String[] parts2 = version2.split("\\.");
        int i = 0, j = 0;
        while (i < parts1.length && j < parts2.length) {
            /*
            if (i > 0 && Integer.parseInt(parts1[i]) == 0) {
                i++;
                continue;
            }
            if (j > 0 && Integer.parseInt(parts2[j]) == 0) {
                j++;
                continue;
            }
            */
            int l = Integer.parseInt(parts1[i]);
            int r = Integer.parseInt(parts2[j]);
            if (l == r) {
                i++;
                j++;
            } else if (l > r) {
                return 1;
            } else {
                return -1;
            }
        }
        while (i < parts1.length && Integer.parseInt(parts1[i]) == 0) {
            i++;
        }
        while (j < parts2.length && Integer.parseInt(parts2[j]) == 0) {
            j++;
        }
        if (i < parts1.length) {
            return 1;
        }
        if (j < parts2.length) {
            return -1;
        }
        return 0;
    }
}
