import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Predicate 로 필터링. */
public class Homework01 {
    public static void main(String[] args) {
        List<Integer> nums = List.of(3, 7, 1, 9, 4, 6, 8);
        Predicate<Integer> even = n -> n % 2 == 0;
        Predicate<Integer> geFive = n -> n >= 5;

        System.out.println("짝수: " + filter(nums, even));
        System.out.println("5 이상: " + filter(nums, geFive));
        System.out.println("짝수 AND 5 이상: " + filter(nums, even.and(geFive)));
    }

    static List<Integer> filter(List<Integer> xs, Predicate<Integer> p) {
        return xs.stream().filter(p).collect(Collectors.toList());
    }
}
