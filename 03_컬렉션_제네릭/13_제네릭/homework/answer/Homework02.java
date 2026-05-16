import java.util.List;

/** 제네릭 메서드 max. */
public class Homework02 {
    static <T extends Comparable<T>> T max(List<T> xs) {
        if (xs.isEmpty()) return null;
        T best = xs.get(0);
        for (T x : xs) if (x.compareTo(best) > 0) best = x;
        return best;
    }

    public static void main(String[] args) {
        System.out.println(max(List.of(3, 9, 1, 7)));
        System.out.println(max(List.of("ABC", "ZZZ", "Yellow")));
        System.out.println(max(List.<Integer>of()));
    }
}
