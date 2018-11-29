/*
 * [692] Top K Frequent Words
 *
 * https://leetcode.com/problems/top-k-frequent-words/description/
 *
 * algorithms
 * Medium (43.20%)
 * Total Accepted:    39.3K
 * Total Submissions: 91.1K
 * Testcase Example:  '["i", "love", "leetcode", "i", "love", "coding"]\n2'
 *
 * Given a non-empty list of words, return the k most frequent elements.
 * Your answer should be sorted by frequency from highest to lowest. If two
 * words have the same frequency, then the word with the lower alphabetical
 * order comes first.
 * 
 * Example 1:
 * 
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * ⁠   Note that "i" comes before "love" due to a lower alphabetical order.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is",
 * "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent
 * words,
 * ⁠   with the number of occurrence being 4, 3, 2 and 1 respectively.
 * 
 * 
 * 
 * Note:
 * 
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 * 
 * 
 * 
 * Follow up:
 * 
 * Try to solve it in O(n log k) time and O(n) extra space.
 * 
 * 
 */
class Solution {
    class MyComparator implements Comparator<String> {
        private Map<String, Integer> countMap;
        public MyComparator(Map<String, Integer> countMap) {
            this.countMap = countMap;
        }
             @Override
             public int compare(String a, String b) {
                 if (countMap.get(a) == countMap.get(b)) {
                     return b.compareTo(a);
                 } else {
                     return countMap.get(a) - countMap.get(b);
                 }
             }
    }
    public List<String> topKFrequent(String[] words, int k) {
         List<String> res = new ArrayList<>();
         if (words == null || words.length == 0) {
             return res;
         }
         Map<String, Integer> countMap = new HashMap<>();
         for (String w : words) {
             countMap.put(w, countMap.getOrDefault(w, 0) + 1);
         }
         TreeSet<String> sortSet = new TreeSet<String>(new MyComparator(countMap));
         for (String w : words) {
             if (sortSet.contains(w)) {
                 continue;
             }
             if (sortSet.size() < k) {
                 sortSet.add(w);
             } else {
                 String first = sortSet.pollFirst();
                 if (countMap.get(first) == countMap.get(w)) {
                     if (first.compareTo(w) > 0) {
                         sortSet.add(w);
                     } else {
                         sortSet.add(first);
                     }
                 } else if (countMap.get(first) > countMap.get(w)) {
                     sortSet.add(first);
                 } else {
                     sortSet.add(w);
                 }
             }
         }

        while (sortSet.size() > 0) {
            res.add(sortSet.pollFirst());
        }
        Collections.reverse(res);
        return res;
    }



    public List<String> topKFrequent2(String[] words, int k) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        Map<String, Integer> countMap = new HashMap<>();
        for (String w : words) {
            countMap.put(w, countMap.getOrDefault(w, 0) + 1);
        }
        PriorityQueue<String> pq = new PriorityQueue<String>(k, new MyComparator(countMap));
        for (String w : words) {
           if (pq.size() < k) {
            pq.offer(w);
           } else {
               String top = pq.peek();
               if (countMap.get(w) == countMap.get(top)) {
                   if (w.compareTo(top) > 0) {
                       pq.poll();
                       pq.offer(w);
                   }
               } else if (countMap.get(w) > countMap.get(top)) {
                   pq.poll();
                   pq.offer(w);
               }
           }
        }
    
        while (pq.size() > 0) {
            res.add(pq.poll());
        }
        return res;
    }
}
