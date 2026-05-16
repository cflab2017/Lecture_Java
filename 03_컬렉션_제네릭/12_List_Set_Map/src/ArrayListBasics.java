import java.util.ArrayList;
import java.util.List;

public class ArrayListBasics {
    public static void main(String[] args) {
        List<String> langs = new ArrayList<>();
        langs.add("Java");
        langs.add("Spring");
        langs.add("Kotlin");
        langs.remove("Kotlin");

        for (String s : langs) System.out.println(s);
        System.out.println("size=" + langs.size());
    }
}
