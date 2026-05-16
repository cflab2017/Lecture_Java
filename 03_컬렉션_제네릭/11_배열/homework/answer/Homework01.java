/** 배열의 합·최댓값·평균. */
public class Homework01 {
    public static void main(String[] args) {
        int[] arr = {3, 7, 1, 9, 4};
        int sum = 0;
        int max = arr[0];
        for (int n : arr) {
            sum += n;
            if (n > max) max = n;
        }
        double avg = (double) sum / arr.length;
        System.out.println("합=" + sum);
        System.out.println("최댓값=" + max);
        System.out.printf("평균=%.2f%n", avg);
    }
}
