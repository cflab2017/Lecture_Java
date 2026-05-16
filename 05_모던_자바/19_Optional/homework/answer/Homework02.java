import java.util.OptionalDouble;
import java.util.stream.IntStream;

/** 안전한 평균. */
public class Homework02 {
    public static void main(String[] args) {
        for (int[] arr : new int[][]{ {1, 2, 3, 4}, {} }) {
            OptionalDouble avg = safeAvg(arr);
            if (avg.isPresent()) {
                System.out.println("평균=" + avg.getAsDouble());
            } else {
                System.out.println("평균 없음");
            }
        }
    }

    static OptionalDouble safeAvg(int[] arr) {
        return IntStream.of(arr).average();
    }
}
