/** 안전한 정수 나눗셈. */
public class Homework01 {
    public static void main(String[] args) {
        int[][] cases = { {10, 2}, {10, 0}, {7, 3} };
        for (int[] c : cases) {
            System.out.println(c[0] + " / " + c[1] + " = " + safeDiv(c[0], c[1]));
        }
    }

    static int safeDiv(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            return 0;
        }
    }
}
