public class Wiggle {

    public void wiggle(int[] nums) {
        for (int i =0; i < nums.length - 1; i++) {
            if ((i % 2 == 0 && nums[i] > nums[i + 1])
                    || (i % 2 == 1 && nums[i] < nums[i + 1])) {
                swap(nums, i, i + 1);
                    }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args){
        Wiggle w = new Wiggle();
        int[] nums = new int[] {1, 2, 3, 4, 5, 6};
        w.wiggle(nums);
        for (int i : nums) {
            System.out.println(i);
        }
    }
        
}
