import java.util.*;
/*
 * @lc app=leetcode id=460 lang=java
 *
 * [460] LFU Cache
 *
 * https://leetcode.com/problems/lfu-cache/description/
 *
 * algorithms
 * Hard (27.60%)
 * Total Accepted:    36.5K
 * Total Submissions: 129K
 * Testcase Example:  '["LFUCache","put","put","get","put","get","get","put","get","get","get"]\n[[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]'
 *
 * Design and implement a data structure for Least Frequently Used (LFU) cache.
 * It should support the following operations: get and put.
 * 
 * 
 * 
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reaches its capacity, it should invalidate the least
 * frequently used item before inserting a new item. For the purpose of this
 * problem, when there is a tie (i.e., two or more keys that have the same
 * frequency), the least recently used key would be evicted.
 * 
 * 
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * 
 * Example:
 * 
 * LFUCache cache = new LFUCache( 2 /* capacity 
 * 
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.get(3);       // returns 3.
 * cache.put(4, 4);    // evicts key 1.
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 * 
 * 
 */

class LFUCache {
    private int cap = 0;
    Map<Integer, Integer> kvMap;
    TreeMap<Integer, List<Integer>> freMap;
    Map<Integer, Integer> kFreMap;
    public LFUCache(int capacity) {
        this.cap = capacity;
        kvMap = new HashMap<>();
        freMap = new TreeMap<>();
        kFreMap = new HashMap<>();
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        cache.get(2);
        cache.get(3);
        cache.put(4, 4);
        cache.get(1);
        cache.get(3);
        cache.get(4);
    }
    
    public int get(int key) {
        int res = -1;
        if (kvMap.containsKey(key)) {
            res = kvMap.get(key);
            addFre(key);
        }
        return res;
    }
    
    public void put(int key, int value) {
        if (!kvMap.containsKey(key) && this.cap == kvMap.size()) {
            evict();
        }
        kvMap.put(key, value);
        addFre(key);
    }

    public void addFre(int key) {
        System.out.println("addFre for key: " + key);
        int freq = 0;
        if (kFreMap.containsKey(key)) {
            freq = kFreMap.get(key);
            System.out.println("add fre for key : " + key + " and freq = " + freq);
            System.out.println("freMap = "  +freMap);
            System.out.println("kFreMap = " + kFreMap);
            List<Integer> prevFreList = freMap.get(freq);
            prevFreList.remove(Integer.valueOf(key));
            if (prevFreList.size() == 0) {
                freMap.remove(freq);
            }
        }
        freq++;
        kFreMap.put(key, freq);
        List<Integer> sameFreList = freMap.computeIfAbsent(freq, l -> new ArrayList<>());
        if (sameFreList.size() == 0) {
            sameFreList.add(key);
        } else {
            sameFreList.add(0, key);
        }
    }

    public void evict() {
        Integer first = freMap.firstKey();
        List<Integer> sameFreList = freMap.get(first);
        Integer keyToRemove = sameFreList.get(sameFreList.size() - 1);
        sameFreList.remove(keyToRemove);
        if (sameFreList.size() == 0) {
            freMap.remove(first);
        }
        kvMap.remove(keyToRemove);
        kFreMap.remove(keyToRemove);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
