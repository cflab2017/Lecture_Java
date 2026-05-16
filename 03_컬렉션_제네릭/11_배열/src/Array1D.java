public class Array1D {
    public static void main(String[] args) {
        int[] nums = new int[5];
        for (int i = 0; i < nums.length; i++) nums[i] = (i + 1) * 10;

        for (int n : nums) System.out.print(n + " ");
        System.out.println();
        System.out.println("길이=" + nums.length);
    }
}
