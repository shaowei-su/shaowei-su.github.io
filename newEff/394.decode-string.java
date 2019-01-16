/*
 * @lc app=leetcode id=394 lang=java
 *
 * [394] Decode String
 *
 * https://leetcode.com/problems/decode-string/description/
 *
 * algorithms
 * Medium (43.32%)
 * Total Accepted:    81.3K
 * Total Submissions: 187.7K
 * Testcase Example:  '"3[a]2[bc]"'
 *
 * 
 * Given an encoded string, return it's decoded string.
 * 
 * 
 * The encoding rule is: k[encoded_string], where the encoded_string inside the
 * square brackets is being repeated exactly k times. Note that k is guaranteed
 * to be a positive integer.
 * 
 * 
 * You may assume that the input string is always valid; No extra white spaces,
 * square brackets are well-formed, etc.
 * 
 * Furthermore, you may assume that the original data does not contain any
 * digits and that digits are only for those repeat numbers, k. For example,
 * there won't be input like 3a or 2[4].
 * 
 * 
 * Examples:
 * 
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 * 
 * 
 */
class Solution {
    public String decodeString(String s) {
        char[] chars = s.toCharArray();
        Deque<Character> stack = new LinkedList<>();
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (index < chars.length) {
            if (chars[index] == '[') {
                stack.push('[');
            } else if (Character.isDigit(chars[index])) {
                stack.push(chars[index]);
            } else if (Character.isLetter(chars[index])) {
                stack.push(chars[index]);
            } else if (chars[index] == ']') {
                StringBuilder temp = new StringBuilder();
                while (stack.size() > 0 && stack.peek() != '[') {
                    temp.append(stack.pop());
                }
                stack.pop(); // [
                StringBuilder temp2 = new StringBuilder();
                while (stack.size() > 0 && Character.isDigit(stack.peek())) {
                    temp2.append(stack.pop());
                }
                String res = eval(temp.reverse().toString(), Integer.parseInt(temp2.reverse().toString()));
                for (char c : res.toCharArray()) {
                    stack.push(c);
                }
            }
            index++;
        }
        while (stack.size() > 0) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public String eval(String part, int repeat) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < repeat; i++) {
            sb.append(part);
        }
        return sb.toString();
    }
}
