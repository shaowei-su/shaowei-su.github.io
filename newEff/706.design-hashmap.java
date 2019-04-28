/*
 * @lc app=leetcode id=706 lang=java
 *
 * [706] Design HashMap
 *
 * https://leetcode.com/problems/design-hashmap/description/
 *
 * algorithms
 * Easy (55.13%)
 * Total Accepted:    28.5K
 * Total Submissions: 51.2K
 * Testcase Example:  '["MyHashMap","put","put","get","get","put","get", "remove", "get"]\n[[],[1,1],[2,2],[1],[3],[2,1],[2],[2],[2]]'
 *
 * Design a HashMap without using any built-in hash table libraries.
 * 
 * To be specific, your design should include these functions:
 * 
 * 
 * put(key, value) : Insert a (key, value) pair into the HashMap. If the value
 * already exists in the HashMap, update the value.
 * get(key): Returns the value to which the specified key is mapped, or -1 if
 * this map contains no mapping for the key.
 * remove(key) : Remove the mapping for the value key if this map contains the
 * mapping for the key.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * MyHashMap hashMap = new MyHashMap();
 * hashMap.put(1, 1);          
 * hashMap.put(2, 2);         
 * hashMap.get(1);            // returns 1
 * hashMap.get(3);            // returns -1 (not found)
 * hashMap.put(2, 1);          // update the existing value
 * hashMap.get(2);            // returns 1 
 * hashMap.remove(2);          // remove the mapping for 2
 * hashMap.get(2);            // returns -1 (not found) 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * All keys and values will be in the range of [0, 1000000].
 * The number of operations will be in the range of [1, 10000].
 * Please do not use the built-in HashMap library.
 * 
 * 
 */
class MyHashMap {
    List<int[]>[] map;
    /** Initialize your data structure here. */
    public MyHashMap() {
       map = new ArrayList[100]; 
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
       int hash = key % 100;
       if (map[hash] == null) {
           map[hash] = new ArrayList<int[]>();
       }
       Iterator<int[]> iter = map[hash].iterator();
       while (iter.hasNext()) {
           if (iter.next()[0] == key) {
               iter.remove();
           }
       }
       map[hash].add(new int[] {key, value});
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hash = key % 100;
        if (map[hash] == null) {
            return -1;
        }
        Iterator<int[]> iter = map[hash].iterator();
        while (iter.hasNext()) {
            int[] cur = iter.next();
            if (cur[0] == key) {
                return cur[1];
            }
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash = key % 100;
        if (map[hash] == null) {
            return;
        }   
        Iterator<int[]> iter = map[hash].iterator();
        while (iter.hasNext()) {
            if (iter.next()[0] == key) {
                iter.remove();
            }   
        }  
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
