/*
 * [432] All O`one Data Structure
 *
 * https://leetcode.com/problems/all-oone-data-structure/description/
 *
 * algorithms
 * Hard (27.63%)
 * Total Accepted:    11.2K
 * Total Submissions: 40.4K
 * Testcase Example:  '["AllOne","getMaxKey","getMinKey"]\n[[],[],[]]'
 *
 * Implement a data structure supporting the following operations:
 * 
 * 
 * 
 * Inc(Key) - Inserts a new key  with value 1. Or increments an existing key by
 * 1. Key is guaranteed to be a non-empty string.
 * Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise
 * decrements an existing key by 1. If the key does not exist, this function
 * does nothing. Key is guaranteed to be a non-empty string.
 * GetMaxKey() - Returns one of the keys with maximal value. If no element
 * exists, return an empty string "".
 * GetMinKey() - Returns one of the keys with minimal value. If no element
 * exists, return an empty string "".
 * 
 * 
 * 
 * 
 * Challenge: Perform all these in O(1) time complexity.
 * 
 */
class AllOne {

    int globalMin = Integer.MAX_VALUE;
    int globalMax = Integer.MIN_VALUE;
    Map<Integer, Integer> kvMap;
    Map<Integer, Set<String>> freMap;
    /** Initialize your data structure here. */
    public AllOne() {
       kvMap = new HashMap<Integer, Integer>();
       freMap = new HashMap<Integer, Set<String>>();
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (kvMap.containsKey(key)) {
            Integer val = kvMap.get(key);
            kvMap.put(key, val + 1);
            freMap.get(val).remove(key);
            Set<String> nextSet = freMap.get(val + 1);
            if (nextSet == null) {
                nextSet = new HashSet<String>();
                freMap.put(val + 1, nextSet);
            }
            nextSet.add(key);
            globalMax = Math.max(val + 1, globalMax);
            globalMin = globalMin == val ? globalMax : globalMin;
        } else {
            kvMap.put(key, 1);
             Set<String> nextSet = freMap.get(1);
             if (nextSet == null) {
                 nextSet = new HashSet<String>();
                 freMap.put(1, nextSet);
             }   
             nextSet.add(key);
             globalMin = 1;
             globalMax = globalMax == Integer.MIN_VALUE : 1 : globalMax;
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (!kvMap.containsKey(key)) {
            return;
        } else {
            Integer val = kvMap.get(key);
            if (val == 1) {
                kvMap.remove(key);
                freMap.get(key).remove(key);
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
