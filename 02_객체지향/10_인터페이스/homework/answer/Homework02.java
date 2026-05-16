/** 함수형 인터페이스로 개수 세기. */
public class Homework02 {
    @FunctionalInterface
    interface IntFilter {
        boolean test(int n);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("짝수: " + countIf(arr, n -> n % 2 == 0));
        System.out.println("5 이상: " + countIf(arr, n -> n >= 5));
    }

    static int countIf(int[] arr, IntFilter f) {
        int c = 0;
        for (int n : arr) if (f.test(n)) c++;
        return c;
    }
}
