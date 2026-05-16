import java.util.HashMap;
import java.util.Map;

/** 단어 빈도 계산. */
public class Homework02 {
    public static void main(String[] args) {
        String[] words = {"apple", "banana", "apple", "cherry", "banana", "apple"};
        Map<String, Integer> count = new HashMap<>();
        for (String w : words) {
            count.put(w, count.getOrDefault(w, 0) + 1);
        }
        for (Map.Entry<String, Integer> e : count.entrySet()) {
            System.out.println(e.getKey() + "=" + e.getValue());
        }
    }
}
