import java.util.*;
public class Collaz {
    public static int findLongestStes(int limit) {
        if (limit < 1) {
            return 0;
        }
        Map<Integer, Integer> cache = new HashMap<>();
        int longest = 0;
        for (int i = 1; i <= limit; i++) {
            longest = Math.max(longest, findSteps(i, cache));
        }
        return longest;
    }
    public static int findSteps(int num, Map<Integer, Integer> cache) {
        if (num <= 1) {
            return 1;
        }
        if (cache.containsKey(num)) {
            return cache.get(num);
        }
        int result = 0;
        if (num % 2 == 0) {
            result = findSteps(num / 2, cache) + 1;
        } else {
            result = findSteps(3 * num + 1, cache) + 1;
        }
        cache.put(num, result);
        return result;
    }

    public int iterate(int limit) {
        Map<Integer, Integer> cache = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= limit; i++) {
            int step = 1;
            int cur = i;
            while (cur > 1) {
                if (cur % 2 == 1) {
                    cur = cur * 3 + 1;
                    step++;
                } else {
                    cur /= 2;
                    step++;
                }
            }
            max = Math.max(max, step);
        }
        return max;
    }


    public static void main(String[] args) {
        Collaz c = new Collaz();
        System.out.println(c.findLongestStes(7));
        System.out.println(c.iterate(7));
    }
}

