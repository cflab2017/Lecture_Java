import java.util.List;

public class StreamBasics {
    public static void main(String[] args) {
        List<String> langs = List.of("Java", "Kotlin", "Scala", "Groovy");
        langs.stream()
             .forEach(System.out::println);

        long count = langs.stream().count();
        System.out.println("개수=" + count);
    }
}
