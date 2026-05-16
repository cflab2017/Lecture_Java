/** 두 정수의 사칙연산. */
public class Homework02 {
    public static void main(String[] args) {
        int a = 17;
        int b = 5;
        System.out.printf("%d + %d = %d%n", a, b, a + b);
        System.out.printf("%d - %d = %d%n", a, b, a - b);
        System.out.printf("%d * %d = %d%n", a, b, a * b);
        System.out.printf("%d / %d = %d%n", a, b, a / b);
        System.out.printf("%d %% %d = %d%n", a, b, a % b);
        System.out.printf("%d / %d (실수) = %.2f%n", a, b, (double) a / b);
    }
}
