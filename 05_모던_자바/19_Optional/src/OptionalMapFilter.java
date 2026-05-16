import java.util.Optional;

public class OptionalMapFilter {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("Java");
        Optional<Integer> len = name.map(String::length);
        len.ifPresent(n -> System.out.println("길이=" + n));

        Optional<String> longName = Optional.of("hi")
            .filter(s -> s.length() > 3)
            .map(String::toUpperCase);
        System.out.println("긴 이름? " + longName);
    }
}
