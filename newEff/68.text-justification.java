/*
 * [68] Text Justification
 *
 * https://leetcode.com/problems/text-justification/description/
 *
 * algorithms
 * Hard (21.17%)
 * Total Accepted:    79.9K
 * Total Submissions: 376.3K
 * Testcase Example:  '["This", "is", "an", "example", "of", "text", "justification."]\n16'
 *
 * Given an array of words and a width maxWidth, format the text such that each
 * line has exactly maxWidth characters and is fully (left and right)
 * justified.
 * 
 * You should pack your words in a greedy approach; that is, pack as many words
 * as you can in each line. Pad extra spaces ' ' when necessary so that each
 * line has exactly maxWidth characters.
 * 
 * Extra spaces between words should be distributed as evenly as possible. If
 * the number of spaces on a line do not divide evenly between words, the empty
 * slots on the left will be assigned more spaces than the slots on the right.
 * 
 * For the last line of text, it should be left justified and no extra space is
 * inserted between words.
 * 
 * Note:
 * 
 * 
 * A word is defined as a character sequence consisting of non-space characters
 * only.
 * Each word's length is guaranteed to be greater than 0 and not exceed
 * maxWidth.
 * The input array words contains at least one word.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * Output:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * Output:
 * [
 * "What   must   be",
 * "acknowledgment  ",
 * "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall
 * be",
 * because the last line must be left-justified instead of fully-justified.
 * ⁠            Note that the second line is also left-justified becase it
 * contains only one word.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input:
 * words =
 * ["Science","is","what","we","understand","well","enough","to","explain",
 * "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * Output:
 * [
 * "Science  is  what we",
 * ⁠ "understand      well",
 * "enough to explain to",
 * "a  computer.  Art is",
 * "everything  else  we",
 * "do                  "
 * ]
 * 
 * 
 */
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) return res;
        int index = 0;
        int count = 0;
         
        while ((index < words.length) && ((count + words[index].length()) <= maxWidth)) {
            res.add(words[index]);
            count += words[index].length();
            count += 1;
            index += 1;
        }
        String cur;
        if (index < words.length) {
            cur = padding(res, maxWidth);
        } else {
            cur = lastPadding(res, maxWidth);
        }
        res.clear();
        res.add(cur);
        List<String> next = fullJustify(Arrays.copyOfRange(words, index, words.length), maxWidth);
        res.addAll(next);
        return res;
    }

    public String lastPadding(List<String> res, int maxWidth) {
        if (res.size() == 0) return "";
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (String s : res) {
            sb.append(s);
            sb.append(" ");
            count += (s.length() + 1);
        }
        sb.setLength(sb.length() - 1);
        sb.append(xSpaces(maxWidth - count + 1));
        return sb.toString();
    }

    public String xSpaces(int x) {
        StringBuilder sb = new StringBuilder();
        while (x > 0) {
            sb.append(" ");
            x--;
        }
        return sb.toString();
    }

    public String padding(List<String> res, int maxWidth) {
        if (res.size() == 0) return "";
        int intervals = res.size() - 1;
        if (intervals == 0) {
            return res.get(0) + xSpaces(maxWidth - res.get(0).length());
        }
        int wordSize = 0;
        for (String s : res) {
            wordSize += s.length();
        }
        int avg = (maxWidth - wordSize) / intervals;
        int left = (maxWidth - wordSize) % intervals;
        StringBuilder sb = new StringBuilder();
        sb.append(res.get(0));
        sb.append(xSpaces(avg) + " ");
        left--;
        for (int i = 1; i < res.size() - 1; i++) {
            sb.append(res.get(i));
            sb.append(xSpaces(avg));
            if (left > 0) {
                sb.append(" ");
                left--;
            }
        }
        sb.append(res.get(res.size() - 1));
        return sb.toString();
    }
}
