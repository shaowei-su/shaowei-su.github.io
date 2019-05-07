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
}

