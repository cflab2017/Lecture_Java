public class Loops {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 5; i++) sum += i;
        System.out.println("1+...+5 = " + sum);

        int j = 0;
        while (j < 3) {
            System.out.print(j + " ");
            j++;
        }
        System.out.println();

        int[] nums = {10, 20, 30};
        for (int n : nums) System.out.print(n + " ");
        System.out.println();
    }
}
