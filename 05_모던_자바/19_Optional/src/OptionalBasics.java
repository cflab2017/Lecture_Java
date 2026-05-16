import java.util.Optional;

public class OptionalBasics {
    public static void main(String[] args) {
        Optional<String> hi = Optional.of("hi");
        Optional<String> empty = Optional.empty();

        System.out.println(hi.isPresent());
        System.out.println(empty.isPresent());

        System.out.println(hi.orElse("default"));
        System.out.println(empty.orElse("default"));

        hi.ifPresent(s -> System.out.println("값=" + s));
        empty.ifPresent(s -> System.out.println("실행 안 됨"));
    }
}
