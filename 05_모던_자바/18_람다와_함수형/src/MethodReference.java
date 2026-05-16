import java.util.List;
import java.util.stream.Collectors;

public class MethodReference {
    public static void main(String[] args) {
        List<String> langs = List.of("java", "kotlin", "scala");
        List<String> upper = langs.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println(upper);

        langs.forEach(System.out::println);
    }
}
