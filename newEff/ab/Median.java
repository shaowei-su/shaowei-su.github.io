public class Median {
    private long search(int[] nums, int k, int left, int right) {
        if (left >= right) {
            return left;
        }
        int res = left;
        int mid = left + (right - left) / 2;
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
            return search(nums, k, Math.max(res + 1, mid), right);
        } else {
            return search(nums, k, left, res);
        }
    }

    ...



}
