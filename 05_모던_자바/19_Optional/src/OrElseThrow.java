import java.util.Optional;

public class OrElseThrow {
    public static void main(String[] args) {
        Optional<String> ok = Optional.of("값");
        Optional<String> none = Optional.empty();

        System.out.println(ok.orElseThrow());

        try {
            none.orElseThrow(() -> new IllegalStateException("필수 값 누락"));
        } catch (IllegalStateException e) {
            System.out.println("예외: " + e.getMessage());
        }
    }
}
