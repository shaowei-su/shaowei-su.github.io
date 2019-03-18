import java.util.*;

class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int max = 0;
        int sum = 0;
        Arrays.sort(weights);
        int len = weights.length;
        max = weights[len - 1];
        sum = Arrays.stream(weights).sum();
        while (max + 1 < sum) {

            int mid = max + (sum - max) / 2;
            System.out.println("mid = " + mid);
            if (ableFit(weights, D, mid)) {
                sum = mid;
            } else {
                max = mid;
            }
        }
        if (ableFit(weights, D, max)) {
            return max;
        } else {
            return sum;
        }
    }
        public boolean ableFit(int[] weights, int D, int size) {
        int curSize = 0;
        int count = 1;
        for (int i = 0; i < weights.length; i++) {
            if (count > D) {
                return false;
            }
            if (curSize + weights[i] > size) {
                curSize = weights[i];
                count++;
            } else {
                curSize += weights[i];
            }
        }
        System.out.println("count == " + count);
        return true;
    }
    public boolean ableFit2(int[] weights, int D, int size) {
        int[] curSize = new int[D];
        for (int i = weights.length - 1; i >= 0; i--) {
            boolean fitIn = false;
            for (int j = 0; j < D; j++) {
                if (curSize[j] + weights[i] <= size) {
                    System.out.println("put " + " w = " + weights[i] + " into j = " + j);
                    curSize[j] += weights[i];
                    fitIn = true;
                    break;
                }
            }
            if (!fitIn) {
                return false;
            }
        }
        return true;
    }

	public static void main(String[]  args) {
		Solution sol = new Solution();
		System.out.println(sol.ableFit(new int[] {3, 2, 2, 4, 1, 4}, 3, 6));

	}
}
