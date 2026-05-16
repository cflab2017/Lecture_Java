import java.util.Map;
import java.util.Optional;

public class OptionalReturn {
    static Map<String, Integer> ages = Map.of("지수", 21, "민수", 25);

    static Optional<Integer> findAge(String name) {
        return Optional.ofNullable(ages.get(name));
    }

    public static void main(String[] args) {
        System.out.println(findAge("지수").orElse(-1));
        System.out.println(findAge("미상").orElse(-1));

        findAge("민수").ifPresent(a -> System.out.println("민수=" + a));
    }
}
