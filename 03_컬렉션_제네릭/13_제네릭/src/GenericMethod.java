import java.util.List;

public class GenericMethod {
    static <T> T first(List<T> list) {
        return list.isEmpty() ? null : list.get(0);
    }

    public static void main(String[] args) {
        System.out.println(first(List.of(1, 2, 3)));
        System.out.println(first(List.of("a", "b")));
        Object none = first(List.of());
        System.out.println(none);
    }
}
