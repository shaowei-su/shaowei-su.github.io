/*
 * [621] Task Scheduler
 *
 * https://leetcode.com/problems/task-scheduler/description/
 *
 * algorithms
 * Medium (43.31%)
 * Total Accepted:    61.3K
 * Total Submissions: 141.4K
 * Testcase Example:  '["A","A","A","B","B","B"]\n2'
 *
 * Given a char array representing tasks CPU need to do. It contains capital
 * letters A to Z where different letters represent different tasks.Tasks could
 * be done without original order. Each task could be done in one interval. For
 * each interval, CPU could finish one task or just be idle.
 * 
 * However, there is a non-negative cooling interval n that means between two
 * same tasks, there must be at least n intervals that CPU are doing different
 * tasks or just be idle.
 * 
 * You need to return the least number of intervals the CPU will take to finish
 * all the given tasks.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The number of tasks is in the range [1, 10000].
 * The integer n is in the range [0, 100].
 * 
 * 
 */
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] counter = new int[26];
        int maxLen = 0;
        int maxCount = 0;
        for (char c : tasks) {
            counter[c - 'A']++;
            if (counter[c - 'A'] == maxCount) {
                maxLen++;
            } else if (counter[c - 'A'] > maxCount) {
                maxCount = counter[c - 'A'];
                maxLen = 1;
            }
        }
        int partitionLen = n - maxLen + 1;
        int partitionCount = maxCount - 1;
        int emptySlots = partitionLen * partitionCount;
        int availableTask = tasks.length - maxLen * maxCount;
        int idles = Math.max(0, emptySlots - availableTask);
        return tasks.length + idles;

    }
    public int leastInterval2(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : tasks) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Task> pq = new PriorityQueue<Task>((a, b) -> (b.count - a.count));
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            pq.offer(new Task(entry.getKey(), entry.getValue()));
        }
        int count = 0;
        while (pq.size() > 0) {
            Map<Character, Integer> miniMap = new HashMap<>();
            int i = 0;
            List<Task> temp = new ArrayList<>();
            for (; i <= n && pq.size() > 0; i++) {
                Task cur = pq.poll();
                if (cur.count == 1) {
                    continue;
                }
                cur.count -= 1;
                temp.add(cur);
            }
            count += (temp.size() > 0 ? n + 1 : i);
            for (Task t : temp) {
                pq.offer(t);
            }
        }
        return count;
    }

    class Task {
        char name;
        int count;
        public Task(char name, int count) {
            this.name = name;
            this.count = count;
        }
    }
}
