import java.util.*;

public class Median {
    private long search(int[] nums, int k, long left, long right) {
        if (left >= right) {
            return left;
        }
        long res = left;
        long mid = left + (right - left) / 2;
        int count = 0;

        for (int num : nums) {
            if (num <= mid) {
                count++;
                res = Math.max(res, num);
            }
        }

        if (count == k) {
            return res;
        } else if (count < k) {
            return search(nums, k, mid + 1, right);
        } else {
            return search(nums, k, left, mid - 1);
        }
    }

    public double findMedian(int[] nums) {
        int len = nums.length;
        if (len % 2 == 1) {
            return (double) search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
        } else {
            return (double) (search(nums, len / 2, Integer.MIN_VALUE, Integer.MAX_VALUE) + search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE)) / 2;
        }
    }
    public double findMedian2(int[] nums) {
        int len = nums.length;
        if (len % 2 == 1) {
            return (double) findKth(nums, len / 2 + 1);
        } else {
            return (double) (findKth(nums, len / 2) + findKth(nums, len / 2 + 1)) / 2;
        }
    }

    private double findKth(int[] nums, int k) {
        long left = Integer.MIN_VALUE;
        long right = Integer.MAX_VALUE;
        while (left < right) {
            int count = 0;
            long res = left;
            long mid = left + (right - left) / 2;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                    res = Math.max(num, res);
                }
            }
            if (count == k) {
                return res;
            } else if (count < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Median m = new Median();
        int[] nums = new int[100];
        Random rand = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt(100);
        }
        System.out.println(m.findMedian(nums));
        System.out.println(m.findMedian2(nums)); 
        Arrays.sort(nums);
        System.out.println(nums[49]);
        System.out.println(nums[50]);
    }



}
