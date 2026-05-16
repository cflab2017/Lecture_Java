import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaBasics {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("hi");
        r.run();

        Comparator<String> byLen = (a, b) -> a.length() - b.length();
        List<String> words = Arrays.asList("ccc", "a", "bb");
        words.sort(byLen);
        System.out.println(words);
    }
}
