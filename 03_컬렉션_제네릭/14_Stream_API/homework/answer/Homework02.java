import java.util.stream.IntStream;

/** 1~10 의 짝수 제곱 합. */
public class Homework02 {
    public static void main(String[] args) {
        int sum = IntStream.rangeClosed(1, 10)
            .filter(n -> n % 2 == 0)
            .map(n -> n * n)
            .sum();
        System.out.println("짝수 제곱 합 = " + sum);
    }
}
