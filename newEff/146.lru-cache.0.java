/*
 * [146] LRU Cache
 *
 * https://leetcode.com/problems/lru-cache/description/
 *
 * algorithms
 * Hard (21.66%)
 * Total Accepted:    210.8K
 * Total Submissions: 973.5K
 * Testcase Example:  '["LRUCache","put","put","get","put","get","put","get","get","get"]\n[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]'
 *
 * 
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 * 
 * 
 * 
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently
 * used item before inserting a new item.
 * 
 * 
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * 
 * Example:
 * 
 * LRUCache cache = new LRUCache( 2 /* capacity 
 * 
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4, 4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 * 
 * 
 */
class LRUCache {
    class DList {
        DList prev;
        DList next;
        int key;
        public DList(int key) {
            this.key = key;
            prev = null;
            next = null;
        }
    }

    DList head;
    DList tail;
    Map<Integer, DList> listMap;
    Map<Integer, Integer> kvMap;
    int cap;
    public LRUCache(int capacity) {
        kvMap = new HashMap<>();
        listMap = new HashMap<>();
        head = new DList(0);
        tail = new DList(0);
        head.next = tail;
        tail.prev = head;
        this.cap = capacity;
    }
    
    public int get(int key) {
        if (!kvMap.containsKey(key)) {
            return -1;
        } else {
            moveRec(key);
            return kvMap.get(key);
        }
    }
    
    public void put(int key, int value) {
        if (kvMap.size() >= cap && !kvMap.containsKey(key)) {
            evict();
        }
        kvMap.put(key, value);
        moveRec(key);
    }

    public void moveRec(int key) {
        DList cur;
        if (listMap.containsKey(key)) {
            cur = listMap.get(key);
            DList next = cur.next;
            DList prev = cur.prev;
            prev.next = next;
            next.prev = prev;
        } else {
            cur = new DList(key);
            listMap.put(key, cur);
        }
        DList second = tail.prev;
        tail.prev = cur;
        cur.prev = second;
        second.next = cur;
        cur.next = tail;
    }

    public void evict() {
        DList second = head.next;
        int key = second.key;
        head.next = second.next;
        second.next.prev = head;
        head.next = second.next;

        kvMap.remove(key);
        listMap.remove(key);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
