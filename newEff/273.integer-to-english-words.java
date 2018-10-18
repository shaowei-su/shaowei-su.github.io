/*
 * [273] Integer to English Words
 *
 * https://leetcode.com/problems/integer-to-english-words/description/
 *
 * algorithms
 * Hard (23.14%)
 * Total Accepted:    70.8K
 * Total Submissions: 306K
 * Testcase Example:  '123'
 *
 * Convert a non-negative integer to its english words representation. Given
 * input is guaranteed to be less than 231 - 1.
 * 
 * Example 1:
 * 
 * 
 * Input: 123
 * Output: "One Hundred Twenty Three"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * 
 * Example 3:
 * 
 * 
 * Input: 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty
 * Seven"
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty
 * Seven Thousand Eight Hundred Ninety One"
 * 
 * 
 */
class Solution {
    public String numberToWords(int num) {
        if (num < 0) { 
            return "";
        }
        if (num == 0) {
            return "Zero";
        }
        Map<Integer, String> weightsMap = new HashMap<Integer, String>();
        weightsMap.put(0, "");
        weightsMap.put(1, "Thousand ");
        weightsMap.put(2, "Million ");
        weightsMap.put(3, "Billion ");

        StringBuilder sb = new StringBuilder();
        List<Integer> parts = spliter(num);
        for (int i = parts.size() - 1; i >= 0; i--) {
            if (parts.get(i) == 0) continue;
            String cur = threeDigitsToWords(parts.get(i));
            sb.append(cur);
            sb.append(weightsMap.get(i));
        }
        if (sb.charAt(sb.length() - 1) == ' ') {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
    public List<Integer> spliter(int num) {
        List<Integer> res = new ArrayList<>();
        while (num > 0) {
            res.add(num % 1000);
            num = num / 1000;
        }
        return res;
    }

    public String twoDigitsToWords(int num) {
         if (num == 0) {
             return "";
         }
         Map<Integer, String> eleMap = new HashMap<>();
         eleMap.put(10, "Ten ");
         eleMap.put(11, "Eleven ");
         eleMap.put(12, "Twelve ");
         eleMap.put(13, "Thirteen ");
         eleMap.put(14, "Fourteen ");
         eleMap.put(15, "Fifteen ");
         eleMap.put(16, "Sixteen ");
         eleMap.put(17, "Seventeen ");
         eleMap.put(18, "Eighteen ");
         eleMap.put(19, "Nineteen ");
         if (num >= 10 && num <= 19) {
             return eleMap.get(num);
         }
         Map<Integer, String> digitMap = new HashMap<Integer, String>();
         digitMap.put(1, "One ");
         digitMap.put(2, "Two ");
         digitMap.put(3, "Three ");
         digitMap.put(4, "Four ");
         digitMap.put(5, "Five ");
         digitMap.put(6, "Six ");
         digitMap.put(7, "Seven ");
         digitMap.put(8, "Eight ");
         digitMap.put(9, "Nine ");
         if (num < 10) {
             return digitMap.get(num);
         }
         Map<Integer, String> tensMap = new HashMap<>();
         tensMap.put(2, "Twenty ");
         tensMap.put(3, "Thirty ");
         tensMap.put(4, "Forty ");
         tensMap.put(5, "Fifty ");
         tensMap.put(6, "Sixty ");
         tensMap.put(7, "Seventy ");
         tensMap.put(8, "Eighty ");
         tensMap.put(9, "Ninety ");
         int tens = num / 10;
         num = num % 10;
         int digits = num;
         StringBuilder sb = new StringBuilder();
         sb.append(tensMap.get(tens));
         if (digits != 0) {

            sb.append(digitMap.get(digits));
         
         }    
        return sb.toString();
    }


    public String threeDigitsToWords(int num) {
        
        int hund = num / 100;
        if (hund == 0) {
            return twoDigitsToWords(num);
        }
        num = num % 100;
        Map<Integer, String> digitMap = new HashMap<Integer, String>();
        digitMap.put(1, "One ");
        digitMap.put(2, "Two ");
        digitMap.put(3, "Three ");
        digitMap.put(4, "Four ");
        digitMap.put(5, "Five ");
        digitMap.put(6, "Six ");
        digitMap.put(7, "Seven ");
        digitMap.put(8, "Eight ");
        digitMap.put(9, "Nine ");
        StringBuilder sb = new StringBuilder();
        if (hund != 0) {
            sb.append(digitMap.get(hund));
            sb.append("Hundred ");
        }
        sb.append(twoDigitsToWords(num));
        return sb.toString();
    }

}
