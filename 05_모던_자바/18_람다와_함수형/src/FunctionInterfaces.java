import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionInterfaces {
    public static void main(String[] args) {
        Function<Integer, Integer> square = n -> n * n;
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Consumer<String> printUp = s -> System.out.println(s.toUpperCase());
        Supplier<String> hello = () -> "Hello";

        System.out.println(square.apply(5));
        System.out.println(isEven.test(10));
        printUp.accept("java");
        System.out.println(hello.get());
    }
}
